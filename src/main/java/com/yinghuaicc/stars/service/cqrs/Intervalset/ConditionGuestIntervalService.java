package com.yinghuaicc.stars.service.cqrs.Intervalset;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.request.ConditionGuestIntervalRequestDTO;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.response.ConditionGuestIntervalResponseDTO;

public interface ConditionGuestIntervalService {

    /**
     * 通过条件查询业态业种区间设置
     * @param conditionGuestIntervalRequestDTO
     * @param pageParam
     * @return
     */
    ResultPageList<ConditionGuestIntervalResponseDTO> findConditionGuestIntervalByConditionGuestIntervalCQRS(ConditionGuestIntervalRequestDTO conditionGuestIntervalRequestDTO, PageParam pageParam);
}
