package com.yinghuaicc.stars.service.dynamic.quarter.dto.response;

import java.time.LocalDateTime;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 */
public class QuarterRateListResponse {

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

    private String marketVal;//市场地位
    private String brandPositioningVal;//品牌定位
    private String brandImgVal; //品牌形象
    private String rentVal;//租费收缴率
    private String chainVal; //连锁跟进度
    private String customerVal;//客服投诉率
    private String enterpriseVal; //企划配合度
    private String quarterVal; //适配值

    private String effectTime; //生效时间
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private String createUser;
    private String modifyUser;

    public String getMarketVal() {
        return marketVal;
    }

    public void setMarketVal(String marketVal) {
        this.marketVal = marketVal;
    }

    public String getBrandPositioningVal() {
        return brandPositioningVal;
    }

    public void setBrandPositioningVal(String brandPositioningVal) {
        this.brandPositioningVal = brandPositioningVal;
    }

    public String getBrandImgVal() {
        return brandImgVal;
    }

    public void setBrandImgVal(String brandImgVal) {
        this.brandImgVal = brandImgVal;
    }

    public String getRentVal() {
        return rentVal;
    }

    public void setRentVal(String rentVal) {
        this.rentVal = rentVal;
    }

    public String getChainVal() {
        return chainVal;
    }

    public void setChainVal(String chainVal) {
        this.chainVal = chainVal;
    }

    public String getCustomerVal() {
        return customerVal;
    }

    public void setCustomerVal(String customerVal) {
        this.customerVal = customerVal;
    }

    public String getEnterpriseVal() {
        return enterpriseVal;
    }

    public void setEnterpriseVal(String enterpriseVal) {
        this.enterpriseVal = enterpriseVal;
    }

    public String getQuarterVal() {
        return quarterVal;
    }

    public void setQuarterVal(String quarterVal) {
        this.quarterVal = quarterVal;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
