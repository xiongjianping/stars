package com.yinghuaicc.stars.service.cqrs.help.dto.request;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/20 下午8:06
 * @Description: 层帮扶计划
 * @Modified:
 */
public class FindHelpPlanFloorListCQRSRequestDTO {

    //区域id
    private String areaId;

    //项目id
    private String projectId;

    //层id
    private String floorId;

    //帮扶类型(1:易租率、2：客销度、3：适配值)
    private Integer helpType;

    public String getAreaId() {
        return areaId;
    }

    public FindHelpPlanFloorListCQRSRequestDTO setAreaId(String areaId) {
        this.areaId = areaId;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public FindHelpPlanFloorListCQRSRequestDTO setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getFloorId() {
        return floorId;
    }

    public FindHelpPlanFloorListCQRSRequestDTO setFloorId(String floorId) {
        this.floorId = floorId;
        return this;
    }

    public Integer getHelpType() {
        return helpType;
    }

    public FindHelpPlanFloorListCQRSRequestDTO setHelpType(Integer helpType) {
        this.helpType = helpType;
        return this;
    }
}
