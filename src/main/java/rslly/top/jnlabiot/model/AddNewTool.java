package rslly.top.jnlabiot.model;


public class AddNewTool {


    private String name;

    private Integer hardWareID;


    private Boolean status;

    private String type;

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
}
