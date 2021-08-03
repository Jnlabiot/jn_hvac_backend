package rslly.top.jnlabiot.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import rslly.top.jnlabiot.model.ClientMessage;

import java.util.List;

public interface ClientMessageRepository extends JpaRepository<ClientMessage, Long> {
   List<ClientMessage> findByTimeBetween(Long time, Long time2);
   List<ClientMessage> findByTime(Long time);
}
