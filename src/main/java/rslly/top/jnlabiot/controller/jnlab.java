package rslly.top.jnlabiot.controller;

import io.lettuce.core.dynamic.annotation.Param;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rslly.top.jnlabiot.config.guest;
import rslly.top.jnlabiot.model.*;
import rslly.top.jnlabiot.service.*;
import rslly.top.jnlabiot.service.serviceinterface.AirpowerdataService;
import rslly.top.jnlabiot.service.serviceinterface.AiruserService;
import rslly.top.jnlabiot.service.tool.Tool;
import rslly.top.jnlabiot.utility.JWTUtils;
import rslly.top.jnlabiot.utility.RedisUtil;
import rslly.top.jnlabiot.utility.mqtt.ClientMQTT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping(value = "/api/v2")
public class jnlab {
    @Autowired
    ClientHardwareIdServiceImpl clientHardwareIdService;

    @Autowired
    ClientMessageServiceImpl clientMessageService;
    @Autowired
    AirpowerdataServiceImpl airpowerdataService;

    @Autowired
    AirControlImpl airControlService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Tool tool;

    @Autowired
    AiruserServiceImpl airuserService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public model getToken(@RequestBody Login login) throws Exception {
        List<AiruserEntity> l1=airuserService.findByUsername(login.getUsername());
        if (!l1.isEmpty() && login.getPassword().equals(l1.get(0).getPasswd())){
            Map map = new HashMap<String, String>();
            map.put("username",login.getUsername() );
            map.put("authority",l1.get(0).getAuthority());
            String msg= JWTUtils.createJWT(map,"{\"user\":\""+login.getUsername()+"\"}",1000*60*60);
            return new model(200,msg,"ok");
        }else{
            return new model(403,null,"error");
        }

    }
    @guest("123")
    @RequestMapping(value = "/addNewTool", method = RequestMethod.POST)
    public model addNewTool(@RequestBody AddNewTool addNewTool) {
       return tool.addNewTool(addNewTool);
    }

    @guest("12323fif")
    @RequestMapping(value = "/forbiddenTool", method = RequestMethod.GET)
    public model forbiddenTool(@Param("hardwareid") Integer hardwareid, @Param("status") Boolean status) {
        return tool.forbiddenTool(hardwareid,status);
    }
    @RequestMapping(value = "/equipmentList", method = RequestMethod.GET)
    public model equipmentList() {
        return tool.equipmentList();
    }
    @RequestMapping(value = "/deleteEquipment",method = RequestMethod.GET)
    public model deleteEquipment(@Param("hardwareid")Integer hardwareid){
        return tool.deleteEquipment(hardwareid);
    }
    //******************************************************************************

    @RequestMapping(value = "/searchAirDataByTime", method = RequestMethod.GET)
    public model selectAirDataByTime(@Param("time") Long time) {
        return new model(200, clientMessageService.findByTime(time), "ok");
    }

    @RequestMapping(value = "/searchAirDataByTimeBetween", method = RequestMethod.GET)
    public model selectAirDataByTimeBetween(@Param("time") Long time, @Param("time2") Long time2) {
        return new model(200, clientMessageService.findByTimeBetween(time, time2), "ok");
    }
    @RequestMapping(value = "/searchAirpowerDataByTime",method = RequestMethod.GET)
    public model searchAirpowerDataByTime(@Param("time") Long time){
        return new model(200, airpowerdataService.findByTime(time), "ok");
    }
    @RequestMapping(value = "/searchAirpowerByTimeBetween",method = RequestMethod.GET)
    public model searchAirpowerByTimeBetween(@Param("time") Long time, @Param("time2") Long time2){
        return new model(200, airpowerdataService.findByTimeBetween(time,time2), "ok");
    }

    @RequestMapping(value = "/controlList",method = RequestMethod.GET)
    public model controlList(){return new model(200, airControlService.findAll(), "ok");}

    @RequestMapping(value = "/deleteControl",method = RequestMethod.GET)
    public model deleteControl(@Param("behaviour")String behaviour){
        if  (!airControlService.findByBehaviour(behaviour).isEmpty()){
            airControlService.deleteByBehaviour(behaviour);
            if (redisUtil.get(behaviour+"topic")!=null){
                redisUtil.del(behaviour+"topic");
                redisUtil.del(behaviour+"msg");
            }
            return new model (200,null,"ok");
        }else return new model(404,null,"behaviou do not exits");

    }
    @RequestMapping(value = "/addNewControl", method = RequestMethod.POST)
    public model addNewControl(@RequestBody AddNewToolControl addNewToolControl) {
        AircontrolEntity aircontrolEntity = new AircontrolEntity();
        model returnmsg;
        aircontrolEntity.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(addNewToolControl, aircontrolEntity);
        if (clientHardwareIdService.findByHardWareID(aircontrolEntity.getHardwareid()).isEmpty()) {

            returnmsg = new model(403, "you should register the hardware in our system", "this hardwareid not exits");
        } else {
            if (airControlService.findByBehaviour(aircontrolEntity.getBehaviour()).isEmpty()) {
                airControlService.insert(aircontrolEntity);
                if (redisUtil.get(aircontrolEntity.getBehaviour()+"topic")!=null){
                    redisUtil.del(aircontrolEntity.getBehaviour()+"topic");
                    redisUtil.del(aircontrolEntity.getBehaviour()+"msg");
                }
                returnmsg = new model(200, addNewToolControl, "ok");
            } else returnmsg = new model(403, addNewToolControl, "behaviour already exits ");
        }
        return returnmsg;
    }
   @RequestMapping(value = "/updateControl",method = RequestMethod.POST)
   public model updateControl(@RequestBody AddNewToolControl upadateToolControl){
        AircontrolEntity aircontrolEntity = new AircontrolEntity();
        model returnmsg;
        List<AircontrolEntity> msg = airControlService.findByBehaviour(upadateToolControl.getBehaviour());
        if (msg.isEmpty())returnmsg = new model(403, "null", "the behaviour not exits");
        else{
            aircontrolEntity.setId(msg.get(0).getId());
            BeanUtils.copyProperties(upadateToolControl, aircontrolEntity);
            airControlService.insert(aircontrolEntity);
            if (redisUtil.get(aircontrolEntity.getBehaviour()+"topic")!=null){
                redisUtil.del(aircontrolEntity.getBehaviour()+"topic");
                redisUtil.del(aircontrolEntity.getBehaviour()+"msg");
            }
            returnmsg = new model(200, upadateToolControl, "ok");
        }
        return returnmsg;
   }



    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public model publish(@Param("behaviour") String behaviour) throws MqttException {
        ClientMQTT clientMQTT = ClientMQTT.getInstance();
        model returnmsg;
        if (redisUtil.get(behaviour+"topic")!=null){
            String topic = (String) redisUtil.get(behaviour+"topic");
            String message = (String) redisUtil.get(behaviour+"msg");
            clientMQTT.publish(topic,message);
            returnmsg = new model(200,redisUtil.getExpire(behaviour+"topic"), "using cache:cache reply msg and topic "+message+" "+topic);
        }else {
            List<AircontrolEntity> msg = airControlService.findByBehaviour(behaviour);
            List<ClientHardwareId> hardwareIds = clientHardwareIdService.findAllByHardWareIDAndStatusAndTypeEquals
                    (msg.get(0).getHardwareid(),true,"any");
            if (!msg.isEmpty()) {
                if(!hardwareIds.isEmpty()) {
                    redisUtil.set(behaviour + "topic", msg.get(0).getTopics(), 60);
                    redisUtil.set(behaviour + "msg", msg.get(0).getMsg(), 60);
                    clientMQTT.publish(msg.get(0).getTopics(), msg.get(0).getMsg());
                    returnmsg = new model(200, null,
                            "using mysql:mysql reply msg and topic " + msg.get(0).getMsg() + " " + msg.get(0).getTopics());
                }else returnmsg = new model(403,null,"the hardware be forbiddened");
            } else returnmsg = new model(403, "null", "the behaviour not exits");
        }
        return returnmsg;
    }

}
