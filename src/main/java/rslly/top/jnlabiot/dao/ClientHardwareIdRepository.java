package rslly.top.jnlabiot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rslly.top.jnlabiot.model.ClientHardwareId;

import java.util.List;


public interface ClientHardwareIdRepository extends JpaRepository<ClientHardwareId, Long> {

    List<ClientHardwareId> findByHardWareID(Integer hardWareID);

    List<ClientHardwareId> findAllByHardWareIDAndStatusAndTypeEquals(Integer hardWareID, Boolean status, String type);

    void  deleteByHardWareID(Integer hardWareID);
}
