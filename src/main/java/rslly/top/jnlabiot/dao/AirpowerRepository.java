package rslly.top.jnlabiot.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import rslly.top.jnlabiot.model.AirpowerdataEntity;
import rslly.top.jnlabiot.model.ClientMessage;

import java.util.List;

public interface AirpowerRepository extends JpaRepository<AirpowerdataEntity, Long> {
   List<AirpowerdataEntity> findByTimeBetween(Long time, Long time2);
   List<AirpowerdataEntity> findByTime(Long time);
}
