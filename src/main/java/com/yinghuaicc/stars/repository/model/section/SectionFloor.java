package com.yinghuaicc.stars.repository.model.section;

import java.time.LocalDateTime;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/23.
 */
public class SectionFloor {
    private String id;
    private String projectId; //项目ID
    private String buildingId; //楼栋ID
    private String floorId; //楼层ID

    private String excellentVal; //优秀值
    private String goodVal;//良好值
    private String promoteVal;//提升值
    private String reasonableVal;//合理值
    private String lossVal;//亏损值
    private String excellentPgeVal;//优秀百分比
    private String goodPgeVal;//良好百分比
    private String promotePgeVal;//提升百分比
    private String reasonablePgeVal;//合理百分比

    private String effectTime; //生效时间
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private String createUser;
    private String modifyUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getExcellentVal() {
        return excellentVal;
    }

    public void setExcellentVal(String excellentVal) {
        this.excellentVal = excellentVal;
    }

    public String getGoodVal() {
        return goodVal;
    }

    public void setGoodVal(String goodVal) {
        this.goodVal = goodVal;
    }

    public String getPromoteVal() {
        return promoteVal;
    }

    public void setPromoteVal(String promoteVal) {
        this.promoteVal = promoteVal;
    }

    public String getReasonableVal() {
        return reasonableVal;
    }

    public void setReasonableVal(String reasonableVal) {
        this.reasonableVal = reasonableVal;
    }

    public String getLossVal() {
        return lossVal;
    }

    public void setLossVal(String lossVal) {
        this.lossVal = lossVal;
    }

    public String getExcellentPgeVal() {
        return excellentPgeVal;
    }

    public void setExcellentPgeVal(String excellentPgeVal) {
        this.excellentPgeVal = excellentPgeVal;
    }

    public String getGoodPgeVal() {
        return goodPgeVal;
    }

    public void setGoodPgeVal(String goodPgeVal) {
        this.goodPgeVal = goodPgeVal;
    }

    public String getPromotePgeVal() {
        return promotePgeVal;
    }

    public void setPromotePgeVal(String promotePgeVal) {
        this.promotePgeVal = promotePgeVal;
    }

    public String getReasonablePgeVal() {
        return reasonablePgeVal;
    }

    public void setReasonablePgeVal(String reasonablePgeVal) {
        this.reasonablePgeVal = reasonablePgeVal;
    }

    public String getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(String effectTime) {
        this.effectTime = effectTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
}
