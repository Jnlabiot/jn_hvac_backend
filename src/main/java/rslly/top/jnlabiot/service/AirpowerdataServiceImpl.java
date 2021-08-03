package rslly.top.jnlabiot.service;


import org.springframework.stereotype.Service;
import rslly.top.jnlabiot.dao.AirpowerRepository;
import rslly.top.jnlabiot.dao.ClientMessageRepository;
import rslly.top.jnlabiot.model.AirpowerdataEntity;
import rslly.top.jnlabiot.model.ClientMessage;
import rslly.top.jnlabiot.service.serviceinterface.AirpowerdataService;
import rslly.top.jnlabiot.service.serviceinterface.ClientMessageService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AirpowerdataServiceImpl implements AirpowerdataService {
    @Resource
    private AirpowerRepository airpowerRepository;


    @Override
    public List<AirpowerdataEntity> findByTime(Long time) {
        return airpowerRepository.findByTime(time);
    }

   /* @Override
    public Long findBy5minutes() {
        Long time2=System.currentTimeMillis()/1000;
        Long time=time2-(60*5);
        List<AirpowerdataEntity> airpowerdataEntityList=airpowerRepository.findByTimeBetween(time,time2);

        return null;
    }*/

    @Override
    public AirpowerdataEntity insertUser(AirpowerdataEntity msg) {
        return airpowerRepository.save(msg);
    }

    @Override
    public List<AirpowerdataEntity> findByTimeBetween(Long time, Long time2) {
        return airpowerRepository.findByTimeBetween(time,time2);

    }
}
