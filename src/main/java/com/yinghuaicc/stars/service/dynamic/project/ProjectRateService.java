package com.yinghuaicc.stars.service.dynamic.project;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.dynamic.project.ProjectRate;
import com.yinghuaicc.stars.repository.model.dynamic.project.ProjectRateSy;
import com.yinghuaicc.stars.service.dynamic.project.dto.response.ProjectRateListResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 */
public interface ProjectRateService {

    void saveProjectRate(ProjectRate projectRate);

    ResultPageList<ProjectRateListResponse> getProjectRateList(ProjectRate projectRate, PageParam pageParam);

    ProjectRate getProjectRateById(String id);

    void updateProjectRate(ProjectRate projectRate);

    void deleteProjectRate(String id);

    BigDecimal getSyProjectCount(ProjectRateSy projectRate);

    List<String> getSyWtProjectCount(ProjectRateSy projectRate);
}
