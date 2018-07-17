package com.yinghuaicc.stars.service.cqrs.contract.dto.request;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/16 下午7:35
 * @Description: 签约
 * @Modified:
 */
public class ContractCQRSListRequestDTO {

    //区域id
    private String areaId = "";

    //项目id
    private String projectId = "";

    //层id
    private String floorId = "";

    //业态id
    private String businessFormId = "";

    //品牌id
    private String brandId = "";

    //铺位名称
    private String name = "";

    public String getAreaId() {
        return areaId;
    }

    public ContractCQRSListRequestDTO setAreaId(String areaId) {
        this.areaId = areaId;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public ContractCQRSListRequestDTO setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getFloorId() {
        return floorId;
    }

    public ContractCQRSListRequestDTO setFloorId(String floorId) {
        this.floorId = floorId;
        return this;
    }

    public String getBusinessFormId() {
        return businessFormId;
    }

    public ContractCQRSListRequestDTO setBusinessFormId(String businessFormId) {
        this.businessFormId = businessFormId;
        return this;
    }

    public String getBrandId() {
        return brandId;
    }

    public ContractCQRSListRequestDTO setBrandId(String brandId) {
        this.brandId = brandId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ContractCQRSListRequestDTO setName(String name) {
        this.name = name;
        return this;
    }
}
