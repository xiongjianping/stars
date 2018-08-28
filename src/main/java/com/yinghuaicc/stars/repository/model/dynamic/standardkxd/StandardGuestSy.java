package com.yinghuaicc.stars.repository.model.dynamic.standardkxd;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
public class StandardGuestSy {

    private String id;
    private String projectId; //项目ID
    private String buildingId; //楼栋ID
    private String floorId; //楼层ID
    private String formId;//业态
    private String speciesId;//业种
    private String contractId;//签约ID

    private String interestVal;//毛利率
    private String priceVal; //客单价
    private String standardVal; //结果值

    private String effectTime; //生效时间
    private String createTime;
    private String modifyTime;
    private String createUser;
    private String modifyUser;
    private String businessFormId; //业态

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

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(String speciesId) {
        this.speciesId = speciesId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getInterestVal() {
        return interestVal;
    }

    public void setInterestVal(String interestVal) {
        this.interestVal = interestVal;
    }

    public String getPriceVal() {
        return priceVal;
    }

    public void setPriceVal(String priceVal) {
        this.priceVal = priceVal;
    }

    public String getStandardVal() {
        return standardVal;
    }

    public void setStandardVal(String standardVal) {
        this.standardVal = standardVal;
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

    public String getBusinessFormId() {
        return businessFormId;
    }

    public void setBusinessFormId(String businessFormId) {
        this.businessFormId = businessFormId;
    }
}
