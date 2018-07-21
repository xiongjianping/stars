package com.yinghuaicc.stars.service.cqrs.triangle;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.FittedVerssionRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.FittedVerssionResponseDTO;

public interface FittedVerssionService {

    /**
     * 适配值版本列表
     * @param fittedVerssionRequestDTO
     * @param pageParam
     * @return
     */
    ResultPageList<FittedVerssionResponseDTO> findFittedVerssionByFittedVerssionCQRS(FittedVerssionRequestDTO fittedVerssionRequestDTO, PageParam pageParam);
}
