package rslly.top.jnlabiot.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

@Entity
@Table(name = "airpowerdata", schema = "labservice")
public class AirpowerdataEntity {
    private String id;
    private Integer hardwareid;
    private long time;
    private String ua;
    private String ia;
    private String pa;
    private String kwh;

    @Id
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "hardwareid", nullable = false, length = 255)
    public Integer getHardwareid() {
        return hardwareid;
    }

    public void setHardwareid(Integer hardwareid) {
        this.hardwareid = hardwareid;
    }

    @Basic
    @Column(name = "time", nullable = false)
    @JSONField(name = "time")
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Basic
    @Column(name = "UA", nullable = false, length = 255)
    @JSONField(name="UA")
    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    @Basic
    @Column(name = "IA", nullable = false, length = 255)
    @JSONField(name = "IA")
    public String getIa() {
        return ia;
    }

    public void setIa(String ia) {
        this.ia = ia;
    }

    @Basic
    @Column(name = "PA", nullable = false, length = 255)
    @JSONField(name = "PA")
    public String getPa() {
        return pa;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    @Basic
    @Column(name = "KWH", nullable = false, length = 255)
    @JSONField(name ="KWH")
    public String getKwh() {
        return kwh;
    }

    public void setKwh(String kwh) {
        this.kwh = kwh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AirpowerdataEntity that = (AirpowerdataEntity) o;

        if (time != that.time) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (hardwareid != null ? !hardwareid.equals(that.hardwareid) : that.hardwareid != null) return false;
        if (ua != null ? !ua.equals(that.ua) : that.ua != null) return false;
        if (ia != null ? !ia.equals(that.ia) : that.ia != null) return false;
        if (pa != null ? !pa.equals(that.pa) : that.pa != null) return false;
        if (kwh != null ? !kwh.equals(that.kwh) : that.kwh != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (hardwareid != null ? hardwareid.hashCode() : 0);
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (ua != null ? ua.hashCode() : 0);
        result = 31 * result + (ia != null ? ia.hashCode() : 0);
        result = 31 * result + (pa != null ? pa.hashCode() : 0);
        result = 31 * result + (kwh != null ? kwh.hashCode() : 0);
        return result;
    }
}
