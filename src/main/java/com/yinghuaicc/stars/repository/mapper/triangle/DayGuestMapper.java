package com.yinghuaicc.stars.repository.mapper.triangle;

import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.DayGuestRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.DayGuestResponseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 每日客销度
 */
@Repository
public interface DayGuestMapper {


    @Select("<script>select * from yhcc_day_guest " +
            " <where> " +
            "<bind name='search.guestVerssionId' value='search.guestVerssionId' /> " +
            "<if test='search.guestVerssionId != null'>AND guest_verssion_id = #{search.guestVerssionId}</if> " +
            "</where> " +
            "</script>")
    List<DayGuestResponseDTO> findDayGuestByDayGuestCQRS (@Param("search")DayGuestRequestDTO dayGuestRequestDTO);

    /**
     * 通过签约id与时间查询客销度
     * @param contractId
     * @param time
     * @return
     */
    @Select("select * from yhcc_day_guest where contract_id = #{contractId} and create_time =#{createTime}")
    DayGuestResponseDTO findDayGuestByDayGuestByContractId(String contractId, LocalDateTime time);
}
