package com.yinghuaicc.stars.service.cqrs.triangle;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.DayRentRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.DayRentResponseDTO;

public interface DayRentService {

    /**
     * 获取天溢租率
     * @param moonRentRequestDTO
     * @param pageParam
     * @return
     */
    ResultPageList<DayRentResponseDTO> findDayRentByDayRentCQRS(DayRentRequestDTO moonRentRequestDTO, PageParam pageParam);
}
