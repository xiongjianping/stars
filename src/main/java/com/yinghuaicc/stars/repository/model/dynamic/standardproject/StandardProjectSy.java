package com.yinghuaicc.stars.repository.model.dynamic.standardproject;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
public class StandardProjectSy {
    private String id;
    private String projectId; //项目ID
    private String rentingRateVal;//溢租率
    private String effectTime; //生效时间
    private String createTime;
    private String modifyTime;
    private String createUser;
    private String modifyUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getRentingRateVal() {
        return rentingRateVal;
    }

    public void setRentingRateVal(String rentingRateVal) {
        this.rentingRateVal = rentingRateVal;
    }

    public String getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(String effectTime) {
        this.effectTime = effectTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
}
