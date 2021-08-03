package rslly.top.jnlabiot.service.serviceinterface;


import rslly.top.jnlabiot.model.ClientMessage;

import java.util.List;

public interface ClientMessageService {
    ClientMessage insertUser(ClientMessage msg);
    List<ClientMessage> findByTime(Long time);
    List<ClientMessage> findByTimeBetween(Long time, Long time2);
}
