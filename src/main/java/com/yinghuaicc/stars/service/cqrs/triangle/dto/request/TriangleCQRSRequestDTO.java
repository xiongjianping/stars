package com.yinghuaicc.stars.service.cqrs.triangle.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 三角理论参数类
 */
public class TriangleCQRSRequestDTO {
    private String id;
    //签约id
    private String contractId;
    private BigDecimal rent;
    private BigDecimal guest;
    private BigDecimal fitted;
    private String area;
    private String projectId;
    private String projectName;
    private String floorId;
    private String floorName;
    private String conditionId;
    //业态名称
    private String conditionName;
    //业种id
    private String majoId;
    private String majoName;
    //品牌id
    private String brandId;
    private String brandName;
    private String roomId;
    //铺位名称
    private String roomName;
    //签约时间
    private LocalDateTime contractTime;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private String createUser;
    private  String modifyUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public BigDecimal getGuest() {
        return guest;
    }

    public void setGuest(BigDecimal guest) {
        this.guest = guest;
    }

    public BigDecimal getFitted() {
        return fitted;
    }

    public void setFitted(BigDecimal fitted) {
        this.fitted = fitted;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

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

    public LocalDateTime getContractTime() {
        return contractTime;
    }

    public void setContractTime(LocalDateTime contractTime) {
        this.contractTime = contractTime;
    }
}
