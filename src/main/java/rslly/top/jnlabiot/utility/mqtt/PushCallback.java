package rslly.top.jnlabiot.utility.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rslly.top.jnlabiot.model.AirpowerdataEntity;
import rslly.top.jnlabiot.model.ClientHardwareId;
import rslly.top.jnlabiot.model.ClientMessage;
import rslly.top.jnlabiot.service.AirpowerdataServiceImpl;
import rslly.top.jnlabiot.service.ClientHardwareIdServiceImpl;
import rslly.top.jnlabiot.service.ClientMessageServiceImpl;
import rslly.top.jnlabiot.service.serviceinterface.AirpowerdataService;
import rslly.top.jnlabiot.utility.WebSocket;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class PushCallback implements MqttCallback {

    @Resource
    private ClientMessageServiceImpl clientMessageService;
    private static PushCallback impMqttCallback1;
    @Resource
    private ClientHardwareIdServiceImpl clientHardwareIdService;
    private static PushCallback impMqttCallback2;
    @Resource
    private AirpowerdataServiceImpl airpowerdataService;
    private static PushCallback impMqttCallback3;

    @PostConstruct
    public void init() {
        impMqttCallback1 = this;
        impMqttCallback1.clientMessageService = this.clientMessageService;    //改成你们对应的Dao层
        impMqttCallback2 = this;
        impMqttCallback2.clientHardwareIdService = this.clientHardwareIdService;
        impMqttCallback3 = this;
        impMqttCallback3.airpowerdataService = this.airpowerdataService;
    }

    private ClientMessage insert(ClientMessage text) {
        return clientMessageService.insertUser(text);
    }
    private AirpowerdataEntity insert2(AirpowerdataEntity msg){
        return  airpowerdataService.insertUser(msg);
    }
    private List<ClientHardwareId> findByHardWareID(Integer hardWareID) {
        return clientHardwareIdService.findByHardWareID(hardWareID);
    }

    private List<ClientHardwareId> findAllByHardWareIDAndStatusAndTypeEquals(Integer hardWareID, Boolean status, String type){
        return clientHardwareIdService.findAllByHardWareIDAndStatusAndTypeEquals(hardWareID,status,type);
    }


    public void connectionLost(Throwable cause) {

        // 连接丢失后，一般在这里面进行重连

        System.out.println("连接断开，可以做重连");
        ClientMQTT client = ClientMQTT.getInstance();
        cause.printStackTrace();
        try {
            client.reConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }


    private void sendMessage(String message) {
        try {
            WebSocket ws = new WebSocket();
            JSONObject jo = new JSONObject();
            jo.put("message", message);
            jo.put("To", "123");
            ws.onMessage(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isFieldNull(Object obj) throws Exception {
        Class stuCla = (Class) obj.getClass();// 得到类对象
        Field[] fs = stuCla.getDeclaredFields();//得到属性集合
        boolean flag = false;
        for (Field f : fs) {//遍历属性
            f.setAccessible(true); // 设置属性是可以访问的(私有的也可以)
            Object val = f.get(obj);// 得到此属性的值
            if (val == null) {//只要有1个属性为空
                flag = true;
                System.out.println(f.getName());
                break;
            }
        }
        return flag;
    }
    boolean msg;
    private boolean isJson(String str) {
        this.msg=true;
        try {
            JSON.parseObject(str, ClientMessage.class);
        } catch (Exception e) {
             this.msg=false;
        }
        return this.msg;
    }


    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + message.getQos());

        //System.out.println("接收消息内容 : " + new String(message.getPayload()));

        if (topic.equals("jn_message")&&isJson(new String(message.getPayload()))) {
            ClientMessage clientMessage = JSON.parseObject(new String(message.getPayload()), ClientMessage.class);

            System.out.println(clientMessage.getHardwareid());

            clientMessage.setId(UUID.randomUUID().toString());
            clientMessage.setTime(System.currentTimeMillis()/1000);
            if (!isFieldNull(clientMessage)) {
                //clientMessageService.insertUser(text);
                System.out.println(impMqttCallback2.findByHardWareID(clientMessage.getHardwareid()));
                List<ClientHardwareId> msg = impMqttCallback2.findAllByHardWareIDAndStatusAndTypeEquals(clientMessage.getHardwareid(),true,"product");
                if (!msg.isEmpty()) {
                    impMqttCallback1.insert(clientMessage);
                }
                sendMessage(JSON.toJSONString(clientMessage));
            }
            //sendMessage(new String(message.getPayload()));
            //clientMessageService.insertUser(text);
            //insert(text);
        }
        if (topic.equals("jn_message/power")&&isJson(new String(message.getPayload()))) {
            AirpowerdataEntity airpowerdataEntity = JSON.parseObject(new String(message.getPayload()), AirpowerdataEntity.class);

            System.out.println(airpowerdataEntity.getHardwareid());

            airpowerdataEntity.setId(UUID.randomUUID().toString());
            if (!isFieldNull(airpowerdataEntity)) {
                //clientMessageService.insertUser(text);
                System.out.println(impMqttCallback2.findByHardWareID(airpowerdataEntity.getHardwareid()));
                List<ClientHardwareId> msg = impMqttCallback2.findAllByHardWareIDAndStatusAndTypeEquals(airpowerdataEntity.getHardwareid(),true,"product");
                if (!msg.isEmpty()) {
                    impMqttCallback3.insert2(airpowerdataEntity);
                }
                sendMessage(JSON.toJSONString(airpowerdataEntity));
            }
            //sendMessage(new String(message.getPayload()));
            //clientMessageService.insertUser(text);
            //insert(text);
        }


    }


}
