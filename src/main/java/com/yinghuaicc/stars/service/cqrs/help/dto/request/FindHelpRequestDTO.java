package com.yinghuaicc.stars.service.cqrs.help.dto.request;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/20 下午8:06
 * @Description: 层帮扶计划
 * @Modified:
 */
public class FindHelpRequestDTO {

    private String projectId; //项目ID
    private String floorId; //楼层ID
    private String formId;//业态
    private String speciesId;//业种
    private Integer type; // 1:易租率、2：客销度、3：适配值
    private Integer state; // 1:yx 2 lh 3ts 4hl 5ks

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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
}
