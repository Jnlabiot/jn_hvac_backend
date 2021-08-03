package rslly.top.jnlabiot.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rslly.top.jnlabiot.dao.ClientHardwareIdRepository;
import rslly.top.jnlabiot.dao.ClientMessageRepository;
import rslly.top.jnlabiot.model.ClientHardwareId;
import rslly.top.jnlabiot.service.serviceinterface.ClientHardwareIdService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClientHardwareIdServiceImpl implements ClientHardwareIdService {
    @Resource
    private ClientHardwareIdRepository clientHardwareIdRepository;
    @Override
    public ClientHardwareId insertUser(ClientHardwareId msg) {
        return clientHardwareIdRepository.save(msg);
    }

    @Override
    public List<ClientHardwareId> findByHardWareID(Integer hardWareID) {
        return clientHardwareIdRepository.findByHardWareID(hardWareID);
    }

    @Override
    public List<ClientHardwareId> findAllByHardWareIDAndStatusAndTypeEquals(Integer hardWareID, Boolean status, String type) {
        return clientHardwareIdRepository.findAllByHardWareIDAndStatusAndTypeEquals(hardWareID,status,type);
    }

    @Override
    public List<ClientHardwareId> findAll() {
        return clientHardwareIdRepository.findAll();
    }

    @Transactional
    @Modifying
    @Override
    public void deleteByHardWareID(Integer hardwareid) {
       clientHardwareIdRepository.deleteByHardWareID(hardwareid);
    }


}
