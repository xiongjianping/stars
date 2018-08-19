package com.yinghuaicc.stars.repository.model.contract;

import java.time.LocalDateTime;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/15 上午11:11
 * @Description: 签约
 * @Modified:
 */
public class Contract {

    private String contractId; //签约ID

    //id
    private String id;

    //项目id
    private String projectId;

    //层id
    private String floorId;

    //铺位id
    private String roomId;

    //品牌id
    private String brandId;

    //状态(true:已签约，false：签约已解除)
    private boolean status;

    private String effectTime; //签约生效时间

    private String invalidTime; //签约失效时间

    //创建时间
    private LocalDateTime createTime;

    //修改时间
    private LocalDateTime modifyTime;

    //创建用户
    private String createUser;

    //修改用户
    private String modifyUser;

    public String getContractId() {
        return contractId;
    }

    public Contract setContractId(String contractId) {
        this.contractId = contractId;
        return this;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public Contract setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
        return this;
    }

    public String getEffectTime() {
        return effectTime;
    }

    public Contract setEffectTime(String effectTime) {
        this.effectTime = effectTime;
        return this;
    }

    public String getId() {
        return id;
    }

    public Contract setId(String id) {
        this.id = id;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public Contract setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getFloorId() {
        return floorId;
    }

    public Contract setFloorId(String floorId) {
        this.floorId = floorId;
        return this;
    }

    public String getRoomId() {
        return roomId;
    }

    public Contract setRoomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    public String getBrandId() {
        return brandId;
    }

    public Contract setBrandId(String brandId) {
        this.brandId = brandId;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public Contract setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Contract setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public Contract setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public Contract setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public Contract setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
        return this;
    }
}
