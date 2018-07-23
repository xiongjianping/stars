package com.yinghuaicc.stars.repository.mapper.triangle;

import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.FloorGuestRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.ProjectGuestRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.FloorGuestResponseDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.ProjectGuestResponseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorGuestMapper {

    @Select("<script>select * from yhcc_floor_guest " +
            " <where> " +
            "<bind name='search.guestVerssionId' value='search.guestVerssionId' /> " +
            "<if test='search.guestVerssionId != null'>AND guest_verssion_id = #{search.guestVerssionId}</if> " +
            "</where> " +
            "</script>")
    List<FloorGuestResponseDTO> findFloorGuestByFloorGuestCQRS (@Param("search")FloorGuestRequestDTO floorGuestRequestDTO);



}
