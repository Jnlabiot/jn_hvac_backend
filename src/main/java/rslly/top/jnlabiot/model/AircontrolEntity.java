package rslly.top.jnlabiot.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "aircontrol")
public class AircontrolEntity {
    private String id;
    private String topics;
    private String msg;
    private Integer hardwareid;
    private String behaviour;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "topics")
    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    @Basic
    @Column(name = "msg")
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Basic
    @Column(name = "hardwareid")
    public Integer getHardwareid() {
        return hardwareid;
    }

    public void setHardwareid(Integer hardwareid) {
        this.hardwareid = hardwareid;
    }

    @Basic
    @Column(name = "behaviour")
    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AircontrolEntity that = (AircontrolEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(topics, that.topics)) return false;
        if (!Objects.equals(msg, that.msg)) return false;
        if (!Objects.equals(hardwareid, that.hardwareid)) return false;
        if (!Objects.equals(behaviour, that.behaviour)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (topics != null ? topics.hashCode() : 0);
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        result = 31 * result + (hardwareid != null ? hardwareid.hashCode() : 0);
        result = 31 * result + (behaviour != null ? behaviour.hashCode() : 0);
        return result;
    }
}
