package com.yinghuaicc.stars.service.region.dto.request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/9/18.
 */
public class ProjectRequestQxDTO {
    //区域
    private String areaId;

    //用户数据权限
    private List<String> projectIds = new ArrayList<String>();

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public List<String> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<String> projectIds) {
        this.projectIds = projectIds;
    }
}
