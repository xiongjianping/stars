package com.yinghuaicc.stars.service.cqrs.Intervalset.dto.request;

public class FloorGuestIntervalRequestDTO {
  /*  private String guestVerssionId;*/

    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
