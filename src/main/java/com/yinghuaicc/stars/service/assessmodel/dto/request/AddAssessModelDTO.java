package com.yinghuaicc.stars.service.assessmodel.dto.request;

import com.yinghuaicc.stars.repository.model.assessmodel.AssessFitredVal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/1.
 */
public class AddAssessModelDTO {

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

    //92aa5587becc47f3a34dbacb1e81b7ae
    private List<AssessFitredVal> addFitredvalList;

    public List<AssessFitredVal> getAddFitredvalList() {
        return addFitredvalList;
    }

    public void setAddFitredvalList(List<AssessFitredVal> addFitredvalList) {
        this.addFitredvalList = addFitredvalList;
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
