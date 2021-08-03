package rslly.top.jnlabiot.service.serviceinterface;

import rslly.top.jnlabiot.model.ClientHardwareId;

import java.util.List;

public interface ClientHardwareIdService {
    ClientHardwareId insertUser(ClientHardwareId msg);
    List<ClientHardwareId> findByHardWareID(Integer hardWareID);
    List<ClientHardwareId> findAllByHardWareIDAndStatusAndTypeEquals(Integer hardWareID, Boolean status, String type);
    List<ClientHardwareId> findAll();
    void  deleteByHardWareID(Integer hardwareid);
}
