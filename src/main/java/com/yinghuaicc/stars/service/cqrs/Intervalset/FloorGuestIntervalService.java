package com.yinghuaicc.stars.service.cqrs.Intervalset;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.request.FloorGuestIntervalRequestDTO;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.response.FloorGuestIntervalResponseDTO;

/**
 * 区间设置-楼层客销度
 */
public interface FloorGuestIntervalService {

    /**
     * 通过条件查询客销度楼层区间
     * @param floorGuestIntervalRequestDTO
     * @param pageParam
     * @return
     */
    ResultPageList<FloorGuestIntervalResponseDTO> findFloorGuestIntervalByFloorGuestIntervalCQRS(FloorGuestIntervalRequestDTO floorGuestIntervalRequestDTO , PageParam pageParam);
}
