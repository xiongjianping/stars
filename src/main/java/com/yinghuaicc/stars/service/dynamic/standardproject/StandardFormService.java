package com.yinghuaicc.stars.service.dynamic.standardproject;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardForm;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardFormSy;
import com.yinghuaicc.stars.service.dynamic.standardproject.dto.response.StandardFormListResponse;

import java.math.BigDecimal;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
public interface StandardFormService {
    
    void saveStandardForm(StandardForm standardForm);

    ResultPageList<StandardFormListResponse> getStandardFormList(StandardForm standardForm, PageParam pageParam);

    StandardForm getStandardFormById(String id);

    void updateStandardForm(StandardForm standardForm);

    void deleteStandardForm(String id);

    BigDecimal getSyFormCount(StandardFormSy standardForm);
}
