package com.yinghuaicc.stars.service.dynamic.standardproject;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardProject;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardProjectSy;
import com.yinghuaicc.stars.service.dynamic.standardproject.dto.response.StandardProjectListResponse;

import java.math.BigDecimal;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
public interface StandardProjectService {

    void saveStandardProject(StandardProject standardProject);

    ResultPageList<StandardProjectListResponse> getStandardProjectList(StandardProject standardProject, PageParam pageParam);

    StandardProject getStandardProjectById(String id);

    void deleteStandardProject(String id);

    void updatStandardProject(StandardProject standardProject);

    BigDecimal getSyProjectCount(StandardProjectSy standardProject);

}
