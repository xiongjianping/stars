package com.yinghuaicc.stars.service.dynamic.rentingRate.dto.response;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 */
public class RentingRateListResponse {
    private String id;
    private String projectName;
    private String brandName;
    private String roomName;
    private String fromName;
    private String speciesName;
    private String rent;
    private String propertyfee;
    private String depreciation;
    private String laborCost;
    private String agencyFee;
    private String effectTime;
    private String createTime;
    private String createUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getPropertyfee() {
        return propertyfee;
    }

    public void setPropertyfee(String propertyfee) {
        this.propertyfee = propertyfee;
    }

    public String getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(String depreciation) {
        this.depreciation = depreciation;
    }

    public String getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(String laborCost) {
        this.laborCost = laborCost;
    }

    public String getAgencyFee() {
        return agencyFee;
    }

    public void setAgencyFee(String agencyFee) {
        this.agencyFee = agencyFee;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
