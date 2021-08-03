package rslly.top.jnlabiot.service;


import org.springframework.stereotype.Service;
import rslly.top.jnlabiot.dao.ClientMessageRepository;
import rslly.top.jnlabiot.model.ClientMessage;
import rslly.top.jnlabiot.service.serviceinterface.ClientMessageService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClientMessageServiceImpl implements ClientMessageService {
    @Resource
    private ClientMessageRepository clientMessageRepository;

    @Override
    public ClientMessage insertUser(ClientMessage msg) {
        return clientMessageRepository.save(msg);
    }

    @Override
    public List<ClientMessage> findByTime(Long time) {
        return clientMessageRepository.findByTime(time);
    }

    @Override
    public List<ClientMessage> findByTimeBetween(Long time, Long time2) {
        return clientMessageRepository.findByTimeBetween(time,time2);

    }
}
