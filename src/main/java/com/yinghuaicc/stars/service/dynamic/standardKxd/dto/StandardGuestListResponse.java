package com.yinghuaicc.stars.service.dynamic.standardKxd.dto;

import java.time.LocalDateTime;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 */
public class StandardGuestListResponse {

    private String id;
    private String projectId; //项目ID
    private String buildingId;
    private String floorId;
    private String formId;
    private String speciesId;
    private String brandId;
    private String contractId;
    private String projectName; //项目名称
    private String buildingName; //楼栋
    private String floorName; //楼层
    private String brandName;
    private String formName;
    private String speciesName;

    private String interestVal;//毛利率
    private String priceVal; //客单价
    private String standardVal; //结果值

    private String effectTime; //生效时间
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private String createUser;
    private String modifyUser;

    public String getStandardVal() {
        return standardVal;
    }

    public void setStandardVal(String standardVal) {
        this.standardVal = standardVal;
    }

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

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
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

    public String getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(String effectTime) {
        this.effectTime = effectTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
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
