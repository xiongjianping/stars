package com.yinghuaicc.stars.repository.model.help;

import java.time.LocalDateTime;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/19 下午8:07
 * @Description: 楼层帮扶计划
 * @Modified:
 */
public class HelpPlanFloor {

    //id
    private String id;

    //项目id
    private String projectId;

    //楼层id
    private String floorId;

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

    public HelpPlanFloor setId(String id) {
        this.id = id;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public HelpPlanFloor setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getFloorId() {
        return floorId;
    }

    public HelpPlanFloor setFloorId(String floorId) {
        this.floorId = floorId;
        return this;
    }

    public String getYxHelpContextId() {
        return yxHelpContextId;
    }

    public HelpPlanFloor setYxHelpContextId(String yxHelpContextId) {
        this.yxHelpContextId = yxHelpContextId;
        return this;
    }

    public String getLhHelpContextId() {
        return lhHelpContextId;
    }

    public HelpPlanFloor setLhHelpContextId(String lhHelpContextId) {
        this.lhHelpContextId = lhHelpContextId;
        return this;
    }

    public String getTsHelpContextId() {
        return tsHelpContextId;
    }

    public HelpPlanFloor setTsHelpContextId(String tsHelpContextId) {
        this.tsHelpContextId = tsHelpContextId;
        return this;
    }

    public String getHlHelpContextId() {
        return hlHelpContextId;
    }

    public HelpPlanFloor setHlHelpContextId(String hlHelpContextId) {
        this.hlHelpContextId = hlHelpContextId;
        return this;
    }

    public String getKsHelpContextId() {
        return ksHelpContextId;
    }

    public HelpPlanFloor setKsHelpContextId(String ksHelpContextId) {
        this.ksHelpContextId = ksHelpContextId;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public HelpPlanFloor setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public HelpPlanFloor setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public HelpPlanFloor setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public HelpPlanFloor setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
        return this;
    }
}
