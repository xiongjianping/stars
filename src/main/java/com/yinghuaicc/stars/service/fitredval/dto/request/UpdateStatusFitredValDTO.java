package com.yinghuaicc.stars.service.fitredval.dto.request;


/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/1.
 * 适配值
 */
public class UpdateStatusFitredValDTO {
    private String id;
    private String status; //状态 1启用 2禁用 3删除

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
