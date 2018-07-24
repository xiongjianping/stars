package com.yinghuaicc.stars.service.cqrs.triangle;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.DayRentRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.DayRentResponseDTO;

import java.time.LocalDateTime;

public interface DayRentService {

    /**
     * 获取天溢租率
     * @param moonRentRequestDTO
     * @param pageParam
     * @return
     */
    ResultPageList<DayRentResponseDTO> findDayRentByDayRentCQRS(DayRentRequestDTO moonRentRequestDTO, PageParam pageParam);


    /**
     * 通过签约id查询溢租率
     * @param contractId 与时间
     * @return
     */
    DayRentResponseDTO findDayRentResponseDTOByContractId(String contractId,LocalDateTime time);


}
