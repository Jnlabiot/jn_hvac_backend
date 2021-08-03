package rslly.top.jnlabiot.service.serviceinterface;

import rslly.top.jnlabiot.model.AircontrolEntity;
import rslly.top.jnlabiot.model.ClientHardwareId;

import java.util.List;

public interface AircontrolService {
    List<AircontrolEntity> findAllByHardwareid(Integer hardwareid);
    List<AircontrolEntity> findByBehaviour(String behaviour);
    AircontrolEntity insert(AircontrolEntity aircontrolEntity);
    List<AircontrolEntity> findAll();
    void deleteByBehaviour(String behaviour);
    void deleteByHardwareid(Integer hardwareid);
}
