package rslly.top.jnlabiot.service.tool;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import rslly.top.jnlabiot.model.AddNewTool;
import rslly.top.jnlabiot.model.ClientHardwareId;
import rslly.top.jnlabiot.model.model;
import rslly.top.jnlabiot.service.AirControlImpl;
import rslly.top.jnlabiot.service.ClientHardwareIdServiceImpl;

import java.util.List;
import java.util.UUID;

@Service
public class Tool {
    @Autowired
    ClientHardwareIdServiceImpl clientHardwareIdService;
    @Autowired
    AirControlImpl airControlService;
    public model addNewTool( AddNewTool addNewTool) {
        ClientHardwareId msg = new ClientHardwareId();
        model returnmsg;
        msg.setId(UUID.randomUUID().toString());
        msg.setTime(System.currentTimeMillis() / 1000);
        BeanUtils.copyProperties(addNewTool, msg);
        if (clientHardwareIdService.findByHardWareID(addNewTool.getHardWareID()).isEmpty()) {
            clientHardwareIdService.insertUser(msg);
            returnmsg = new model(200, addNewTool, "ok");
        } else {
            returnmsg = new model(403, addNewTool, "already exits");
        }
        return returnmsg;
    }
    public model forbiddenTool(Integer hardwareid,  Boolean status) {
        model returnmsg;
        if (clientHardwareIdService.findByHardWareID(hardwareid).isEmpty()) {
            returnmsg = new model(403, "null", "this hardwareid not exits");
        } else {
            List<ClientHardwareId> msg = clientHardwareIdService.findByHardWareID(hardwareid);
            msg.get(0).setStatus(status);
            clientHardwareIdService.insertUser(msg.get(0));
            returnmsg = new model(200, msg, "ok");
        }
        return returnmsg;
    }
    public model equipmentList() {
        return new model(200, clientHardwareIdService.findAll(), "ok");
    }
    public model deleteEquipment(@Param("hardwareid")Integer hardwareid){
        if (!clientHardwareIdService.findByHardWareID(hardwareid).isEmpty()){
            clientHardwareIdService.deleteByHardWareID(hardwareid);
            airControlService.deleteByHardwareid(hardwareid);
            return new model (200,null,"ok");
        }else return new model(404,null,"hardwareid do not exits");

    }
}
