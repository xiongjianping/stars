package com.yinghuaicc.stars.repository.model.assessmodel;

import com.yinghuaicc.stars.repository.model.fitredval.FitredVal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/1.
 */
public class AssessModel {

    private String id;

    private String name;

    private String number;

    private String status;

    private String createName;

    private String updateName;

    //创建时间
    private LocalDateTime createTime;

    //修改时间
    private LocalDateTime modifyTime;

    private List<FitredVal> fitredVals;

    public List<FitredVal> getFitredVals() {
        return fitredVals;
    }

    public void setFitredVals(List<FitredVal> fitredVals) {
        this.fitredVals = fitredVals;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
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
}
