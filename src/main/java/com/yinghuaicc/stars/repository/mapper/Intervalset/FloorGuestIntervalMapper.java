package com.yinghuaicc.stars.repository.mapper.Intervalset;

import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.request.FloorGuestIntervalRequestDTO;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.response.FloorGuestIntervalResponseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorGuestIntervalMapper {

    @Select("<script>select * from yhcc_floor_guest_interval " +
            " <where> " +
            "<bind name='search.guestVerssionId' value='search.guestVerssionId' /> " +
            "<if test='search.guestVerssionId != null'>AND guest_verssion_id = #{search.guestVerssionId}</if> " +
            "</where> " +
            "</script>")
    List<FloorGuestIntervalResponseDTO> findFloorGuestIntervalByFloorGuestIntervalCQRS (@Param("search")FloorGuestIntervalRequestDTO floorGuestIntervalRequestDTO);

}
