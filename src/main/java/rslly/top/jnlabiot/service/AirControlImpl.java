package rslly.top.jnlabiot.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rslly.top.jnlabiot.dao.AircontrolRepository;
import rslly.top.jnlabiot.model.AircontrolEntity;
import rslly.top.jnlabiot.service.serviceinterface.AircontrolService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AirControlImpl implements AircontrolService {
    @Resource
    private AircontrolRepository aircontrolRepository;
    @Override
    public List<AircontrolEntity> findAllByHardwareid(Integer hardwareid) {
        return aircontrolRepository.findAllByHardwareid(hardwareid);
    }

    @Override
    public List<AircontrolEntity> findByBehaviour(String behaviour) {
        return aircontrolRepository.findByBehaviour(behaviour);
    }

    @Override
    public AircontrolEntity insert(AircontrolEntity aircontrolEntity) {
        return aircontrolRepository.save(aircontrolEntity);
    }

    @Override
    public List<AircontrolEntity> findAll() {
        return aircontrolRepository.findAll();
    }

    @Modifying
    @Transactional
    @Override
    public void deleteByBehaviour(String behaviour) {
        aircontrolRepository.deleteByBehaviour(behaviour);
    }

    @Modifying
    @Transactional
    @Override
    public void deleteByHardwareid(Integer hardwareid) {
         aircontrolRepository.deleteByHardwareid(hardwareid);
    }
}
