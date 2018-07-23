package com.yinghuaicc.stars.service.cqrs.Intervalset;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.request.ProjectGuestIntervalRequestDTO;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.response.ProjectGuestIntervalResponseDTO;

public interface ProjectGuestIntervalService {

    /**
     * 通过条件查询项目客销度列表
     * @param projectGuestIntervalRequestDTO
     * @param pageParam
     * @return
     */
    ResultPageList<ProjectGuestIntervalResponseDTO> findProjectGuestIntervalByProjectGuestIntervalCQRS(ProjectGuestIntervalRequestDTO projectGuestIntervalRequestDTO , PageParam pageParam);
}
