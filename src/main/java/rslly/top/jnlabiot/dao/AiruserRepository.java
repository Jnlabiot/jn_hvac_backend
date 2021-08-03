package rslly.top.jnlabiot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rslly.top.jnlabiot.model.AiruserEntity;

import java.util.List;

public interface AiruserRepository extends JpaRepository<AiruserEntity, Long> {
    List<AiruserEntity> findByUsername(String username);
    List<AiruserEntity> findByUsernameAndAuthority(String username, String authority);

}
