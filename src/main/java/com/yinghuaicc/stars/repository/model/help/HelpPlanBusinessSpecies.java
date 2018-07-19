package com.yinghuaicc.stars.repository.model.help;

import java.time.LocalDateTime;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/19 下午8:07
 * @Description: 业种帮扶计划
 * @Modified:
 */
public class HelpPlanBusinessSpecies {

    //id
    private String id;

    //项目id
    private String projectId;

    //业态id
    private String businessFormId;

    //业种id
    private String businessSpeciesId;

    //优秀帮扶内容id
    private String yxHelpContextId;

    //良好帮扶内容id
    private String lhHelpContextId;

    //提升帮扶内容id
    private String tsHelpContextId;

    //合理帮扶内容id
    private String hlHelpContextId;

    //亏损帮扶内容id
    private String ksHelpContextId;

    //创建时间
    private LocalDateTime createTime;

    //修改时间
    private LocalDateTime modifyTime;

    //创建人
    private String createUser;

    //修改人
    private String modifyUser;

    public String getId() {
        return id;
    }

    public HelpPlanBusinessSpecies setId(String id) {
        this.id = id;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public HelpPlanBusinessSpecies setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getBusinessFormId() {
        return businessFormId;
    }

    public HelpPlanBusinessSpecies setBusinessFormId(String businessFormId) {
        this.businessFormId = businessFormId;
        return this;
    }

    public String getBusinessSpeciesId() {
        return businessSpeciesId;
    }

    public HelpPlanBusinessSpecies setBusinessSpeciesId(String businessSpeciesId) {
        this.businessSpeciesId = businessSpeciesId;
        return this;
    }

    public String getYxHelpContextId() {
        return yxHelpContextId;
    }

    public HelpPlanBusinessSpecies setYxHelpContextId(String yxHelpContextId) {
        this.yxHelpContextId = yxHelpContextId;
        return this;
    }

    public String getLhHelpContextId() {
        return lhHelpContextId;
    }

    public HelpPlanBusinessSpecies setLhHelpContextId(String lhHelpContextId) {
        this.lhHelpContextId = lhHelpContextId;
        return this;
    }

    public String getTsHelpContextId() {
        return tsHelpContextId;
    }

    public HelpPlanBusinessSpecies setTsHelpContextId(String tsHelpContextId) {
        this.tsHelpContextId = tsHelpContextId;
        return this;
    }

    public String getHlHelpContextId() {
        return hlHelpContextId;
    }

    public HelpPlanBusinessSpecies setHlHelpContextId(String hlHelpContextId) {
        this.hlHelpContextId = hlHelpContextId;
        return this;
    }

    public String getKsHelpContextId() {
        return ksHelpContextId;
    }

    public HelpPlanBusinessSpecies setKsHelpContextId(String ksHelpContextId) {
        this.ksHelpContextId = ksHelpContextId;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public HelpPlanBusinessSpecies setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public HelpPlanBusinessSpecies setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public HelpPlanBusinessSpecies setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public HelpPlanBusinessSpecies setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
        return this;
    }
}
