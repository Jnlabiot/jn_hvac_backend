package rslly.top.jnlabiot.service.serviceinterface;


import rslly.top.jnlabiot.model.AirpowerdataEntity;
import rslly.top.jnlabiot.model.ClientMessage;

import java.util.List;

public interface AirpowerdataService {
    AirpowerdataEntity insertUser(AirpowerdataEntity msg);
    List<AirpowerdataEntity> findByTimeBetween(Long time, Long time2);
    List<AirpowerdataEntity> findByTime(Long time);
}
