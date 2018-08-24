package com.yinghuaicc.stars.service.dynamic.rentingRate.dto.request;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 */
public class getRentingRateListRequest {
    private String projectId; //项目ID
    private String buildingId; //楼栋
    private String floorId; //楼层
    private String businessFormId; //业态
    private String brandName; //品牌
    private String effectTime;//生效时间
    private String contractId;//签约ID

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
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

    public String getBusinessFormId() {
        return businessFormId;
    }

    public void setBusinessFormId(String businessFormId) {
        this.businessFormId = businessFormId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(String effectTime) {
        this.effectTime = effectTime;
    }
}
