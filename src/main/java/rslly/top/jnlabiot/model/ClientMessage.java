package rslly.top.jnlabiot.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author ruan
 */

@Entity
@Table(name = "airstabledata")
@EntityListeners(AuditingEntityListener.class)
public class ClientMessage {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "hardwareid")
    @JSONField(name = "hardwareid")
    private Integer hardwareid;
    @Column(name = "time")
    @JSONField(name="time")
    private Long time;
    @Column(name="temperature")
    @JSONField(name="temperature")
    private String temperature;
    @Column(name = "humidity")
    private String humidity;
    @Column(name = "co2")
    @JSONField(name = "co2")
    private String co2;
    @Column(name = "waterspeed")
    @JSONField(name = "waterSpeed")
    private String waterspeed;
    @Column(name = "fanoutletwatertemperature")
    @JSONField(name = "fOwTem")
    private String fanOutletWaterTemperature;
    @Column(name = "fanintakewatertemperature")
    @JSONField(name="fIWTem")
    private String fanIntakeWaterTemperature;
    @Column(name = "coolingtoweroutletwatertemperature")
    @JSONField(name = "cTOWTem")
    private String coolingTowerOutletWaterTemperature;
    @Column(name = "coolingtowerintakewatertemperature")
    @JSONField(name="cTIWTem")
    private String coolingTowerIntakeWaterTemperature;
    /*@Column(name = "refrigeratingunitoutletwatertemperature")
    @JSONField(name = "rUOWTem")
    private String refrigeratingUnitOutletWaterTemperature;
    @Column(name="refrigeratingunitintakewatertemperature")
    @JSONField(name="rUIWTem")
    private String refrigeratingUnitIntakeWaterTemperature;
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getHardwareid() {
        return hardwareid;
    }

    public void setHardwareid(Integer hardwareid) {
        this.hardwareid = hardwareid;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public String getWaterspeed() {
        return waterspeed;
    }

    public void setWaterspeed(String waterspeed) {
        this.waterspeed = waterspeed;
    }


    public String getFanOutletWaterTemperature() {
        return fanOutletWaterTemperature;
    }

    public void setFanOutletWaterTemperature(String fanOutletWaterTemperature) {
        this.fanOutletWaterTemperature = fanOutletWaterTemperature;
    }

    public String getFanIntakeWaterTemperature() {
        return fanIntakeWaterTemperature;
    }

    public void setFanIntakeWaterTemperature(String fanIntakeWaterTemperature) {
        this.fanIntakeWaterTemperature = fanIntakeWaterTemperature;
    }

    public String getCoolingTowerOutletWaterTemperature() {
        return coolingTowerOutletWaterTemperature;
    }

    public void setCoolingTowerOutletWaterTemperature(String coolingTowerOutletWaterTemperature) {
        this.coolingTowerOutletWaterTemperature = coolingTowerOutletWaterTemperature;
    }

    public String getCoolingTowerIntakeWaterTemperature() {
        return coolingTowerIntakeWaterTemperature;
    }

    public void setCoolingTowerIntakeWaterTemperature(String coolingTowerIntakeWaterTemperature) {
        this.coolingTowerIntakeWaterTemperature = coolingTowerIntakeWaterTemperature;
    }


}
