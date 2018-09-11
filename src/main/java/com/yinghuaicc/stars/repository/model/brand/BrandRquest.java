package com.yinghuaicc.stars.repository.model.brand;

/**
 * @Author:Fly
 * @Date:Create in 2018/6/26 下午4:22
 * @Description: 品牌
 * @Modified:
 */
public class BrandRquest {

    private String fromId;
    private String projectId;
    private String speciesId;

    public String getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(String speciesId) {
        this.speciesId = speciesId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
