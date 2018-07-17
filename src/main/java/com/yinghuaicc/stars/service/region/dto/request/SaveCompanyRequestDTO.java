package com.yinghuaicc.stars.service.region.dto.request;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/4 下午4:38
 * @Description: 添加公司
 * @Modified:
 */
public class SaveCompanyRequestDTO {

    //公司名称
    @NotEmpty(message = "REGION_SAVE_COMPANY_001")
    private String name;

    //城市id
    @NotEmpty(message = "REGION_SAVE_COMPANY_002")
    private String cityId;

    //区域id
    @NotEmpty(message = "REGION_SAVE_COMPANY_003")
    private String areaId;

    //父id
    private String parentId;

    public String getName() {
        return name;
    }

    public SaveCompanyRequestDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCityId() {
        return cityId;
    }

    public SaveCompanyRequestDTO setCityId(String cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getAreaId() {
        return areaId;
    }

    public SaveCompanyRequestDTO setAreaId(String areaId) {
        this.areaId = areaId;
        return this;
    }

    public String getParentId() {
        return parentId;
    }

    public SaveCompanyRequestDTO setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }
}
