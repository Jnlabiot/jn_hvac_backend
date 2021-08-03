package rslly.top.jnlabiot.service;

import org.springframework.stereotype.Service;
import rslly.top.jnlabiot.dao.AiruserRepository;
import rslly.top.jnlabiot.model.AiruserEntity;
import rslly.top.jnlabiot.service.serviceinterface.AiruserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AiruserServiceImpl implements AiruserService {
    @Resource
    private AiruserRepository airuserRepository;
    @Override
    public List<AiruserEntity> findByUsername(String username) {
        return airuserRepository.findByUsername(username);
    }

    @Override
    public List<AiruserEntity> findByUsernameAndAuthority(String username, String authority) {
        return airuserRepository.findByUsernameAndAuthority(username,authority);
    }
}
