package com.yinghuaicc.stars.service.fitredval.dto.request;


import java.time.LocalDateTime;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/1.
 * 适配值
 */
public class AddFitredValDTO {
    private String id;
    private String fitredType; //类型 1，适合度，2配合度  新增需要
    private String name;//名称 新增需要
    private String val;//最大值 新增需要
    //创建时间
    private LocalDateTime createTime;

    //修改时间
    private LocalDateTime modifyTime;

    private String status; //状态 1启用 2禁用 3删除

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFitredType() {
        return fitredType;
    }

    public void setFitredType(String fitredType) {
        this.fitredType = fitredType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
