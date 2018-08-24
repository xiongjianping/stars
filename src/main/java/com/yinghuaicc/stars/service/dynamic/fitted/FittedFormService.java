package com.yinghuaicc.stars.service.dynamic.fitted;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedForm;
import com.yinghuaicc.stars.service.dynamic.fitted.dto.response.FittedFormListResponse;

import java.math.BigDecimal;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
public interface FittedFormService {
    
    void saveFittedForm(FittedForm fittedForm );

    ResultPageList<FittedFormListResponse> getFittedFormList(FittedForm fittedForm , PageParam pageParam);

    FittedForm getFittedFormById(String id);

    void updateFittedForm(FittedForm fittedForm );

    void deleteFittedForm(String id);

    BigDecimal getFittedForm(FittedForm fittedForm);
}
