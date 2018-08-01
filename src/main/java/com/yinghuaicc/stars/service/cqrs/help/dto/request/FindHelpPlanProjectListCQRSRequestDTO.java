package com.yinghuaicc.stars.service.cqrs.help.dto.request;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/20 上午10:54
 * @Description: 项目帮扶计划查询
 * @Modified:
 */
public class FindHelpPlanProjectListCQRSRequestDTO {

    //区域id
    private String areaId;

    //项目id
    private String projectId;

    //帮扶类型(1:易租率、2：客销度、3：适配值)
    private Integer helpType;

    public String getAreaId() {
        return areaId;
    }

    public FindHelpPlanProjectListCQRSRequestDTO setAreaId(String areaId) {
        this.areaId = areaId;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public FindHelpPlanProjectListCQRSRequestDTO setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public Integer getHelpType() {
        return helpType;
    }

    public FindHelpPlanProjectListCQRSRequestDTO setHelpType(Integer helpType) {
        this.helpType = helpType;
        return this;
    }
}
