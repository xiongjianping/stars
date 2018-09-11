package com.yinghuaicc.stars.service.dynamic.quarter.dto.response;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/19.
 */
public class ProjectQuarterRateResponse {
    private String id;
    private String acreage;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }
}
