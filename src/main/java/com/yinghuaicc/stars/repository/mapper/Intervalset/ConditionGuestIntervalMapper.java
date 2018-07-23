package com.yinghuaicc.stars.repository.mapper.Intervalset;

import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.request.ConditionGuestIntervalRequestDTO;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.response.ConditionGuestIntervalResponseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConditionGuestIntervalMapper {

    @Select("<script>select * from yhcc_floor_guest_interval " +
            " <where> " +
            "<bind name='search.guestVerssionId' value='search.guestVerssionId' /> " +
            "<if test='search.guestVerssionId != null'>AND guest_verssion_id = #{search.guestVerssionId}</if> " +
            "</where> " +
            "</script>")
    List<ConditionGuestIntervalResponseDTO> findConditionGuestIntervalByConditionGuestIntervalCQRS (@Param("search")ConditionGuestIntervalRequestDTO conditionGuestIntervalRequestDTO);
}
