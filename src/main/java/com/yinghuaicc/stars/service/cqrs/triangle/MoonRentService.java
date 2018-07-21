package com.yinghuaicc.stars.service.cqrs.triangle;


import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;

import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.MoonRentRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.MoonRentResponseDTO;

/**
 * 月溢租率接口
 */
public interface MoonRentService {

    /**
     * 获取月溢租率
     * @param moonRentRequestDTO
     * @param pageParam
     * @return
     */
    ResultPageList<MoonRentResponseDTO> findRentVerssionByRantVersionCQRS(MoonRentRequestDTO moonRentRequestDTO, PageParam pageParam);
}
