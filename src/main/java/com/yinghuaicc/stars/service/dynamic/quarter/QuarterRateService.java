package com.yinghuaicc.stars.service.dynamic.quarter;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.dynamic.quarter.QuarterRate;
import com.yinghuaicc.stars.repository.model.dynamic.quarter.QuarterRateSy;
import com.yinghuaicc.stars.service.dynamic.quarter.dto.response.QuarterRateListResponse;

import java.math.BigDecimal;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 */
public interface QuarterRateService {

    void saveQuarterRate(QuarterRate quarterRate);

    ResultPageList<QuarterRateListResponse> getQuarterRateList(QuarterRate quarterRate, PageParam pageParam);

    QuarterRateListResponse getQuarterRateById(String id);

    void deleteQuarterRateById(String id);

    void updateQuarterRate(QuarterRate quarterRate);

    BigDecimal getBrandQuarterRate(QuarterRateSy quarterRate);

    BigDecimal getProjectQuarterRate(QuarterRateSy quarterRate);

    BigDecimal getFormQuarterRate(QuarterRateSy quarterRate);

    BigDecimal getFloorQuarterRate(QuarterRateSy quarterRate);
}
