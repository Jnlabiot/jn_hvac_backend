package rslly.top.jnlabiot.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "airhardware")
@EntityListeners(AuditingEntityListener.class)
public class ClientHardwareId {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "hardwareid")
    private Integer hardWareID;
    @Column(name="time")
    private Long time;
    @Column(name="status")
    private Boolean status;
    @Column(name = "type")
    private String type;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHardWareID() {
        return hardWareID;
    }

    public void setHardWareID(Integer hardWareID) {
        this.hardWareID = hardWareID;
    }
}
