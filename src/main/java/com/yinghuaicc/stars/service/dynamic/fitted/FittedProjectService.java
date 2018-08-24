package com.yinghuaicc.stars.service.dynamic.fitted;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedProject;
import com.yinghuaicc.stars.service.dynamic.fitted.dto.response.FittedProjectListResponse;

import java.math.BigDecimal;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
public interface FittedProjectService {

    void saveFittedProject(FittedProject fittedProject);

    ResultPageList<FittedProjectListResponse> getFittedProjectList(FittedProject fittedProject, PageParam pageParam);

    FittedProject getFittedProjectById(String id);

    void deleteFittedProject(String id);

    void updatFittedProject(FittedProject fittedProject);

    BigDecimal getFittedProject(FittedProject fittedProject);

}
