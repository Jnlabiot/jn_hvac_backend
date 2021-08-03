package rslly.top.jnlabiot.model;

public class AddNewToolControl {
    private String topics;
    private String msg;
    private Integer hardwareid;
    private String behaviour;

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getHardwareid() {
        return hardwareid;
    }

    public void setHardwareid(Integer hardwareid) {
        this.hardwareid = hardwareid;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }
}
