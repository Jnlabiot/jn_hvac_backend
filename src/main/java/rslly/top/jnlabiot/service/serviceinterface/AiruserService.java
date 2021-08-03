package rslly.top.jnlabiot.service.serviceinterface;

import rslly.top.jnlabiot.model.AiruserEntity;

import java.util.List;

public interface AiruserService {
    List<AiruserEntity> findByUsername(String username);
    List<AiruserEntity> findByUsernameAndAuthority(String username, String authority);
}
