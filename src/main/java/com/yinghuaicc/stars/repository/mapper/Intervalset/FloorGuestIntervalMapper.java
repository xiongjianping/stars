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


    /**
     * 通过项目id、楼层id 查询楼层级客销度区间
     * @param projectId
     * @param floorId
     * @return
     */
    @Select("select * from yhcc_floor_guest_interval where project_id = #{projectId} and floor_id = #{floorId}")
    FloorGuestIntervalResponseDTO findFloorGuestIntervalByProjectIdAndFloorId(@Param("projectId") String projectId,@Param("floorId") String floorId);
}
