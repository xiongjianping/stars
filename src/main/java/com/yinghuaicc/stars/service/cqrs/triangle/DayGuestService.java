package com.yinghuaicc.stars.service.cqrs.triangle;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.DayGuestRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.DayGuestResponseDTO;

public interface DayGuestService {
    /**
     * 根据版本获取每日客销度
     * @param dayGuestRequestDTO
     * @param pageParam
     * @return
     */
    ResultPageList<DayGuestResponseDTO> findDayGuestByDayGuestCQRS(DayGuestRequestDTO dayGuestRequestDTO, PageParam pageParam);
}
