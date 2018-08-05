package com.yinghuaicc.stars.service.cqrs.triangle.dto.response;

/**
 * 定时器通过签约id查询项目、楼层、业态、业种、品牌、铺位
 */
public class TimingByConditionResponseDTO {
    private String projectId;
    private String projectName;
    private String floorId;
    private String floorName;
    private String roomId;
    private String roomName;
    private String majoId;
    private String majoName;
    private String brandId;
    private String brandName;
    private String conditionId;
    private String conditionName;


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getMajoId() {
        return majoId;
    }

    public void setMajoId(String majoId) {
        this.majoId = majoId;
    }

    public String getMajoName() {
        return majoName;
    }

    public void setMajoName(String majoName) {
        this.majoName = majoName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }
}
