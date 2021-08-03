package rslly.top.jnlabiot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rslly.top.jnlabiot.model.AircontrolEntity;

import java.util.List;

public interface AircontrolRepository extends JpaRepository<AircontrolEntity, Long> {

    List<AircontrolEntity> findAllByHardwareid(Integer hardwareid);
    List<AircontrolEntity> findByBehaviour(String behaviour);
    void deleteByBehaviour(String behaviour);
    void deleteByHardwareid(Integer hardwareid);
}
