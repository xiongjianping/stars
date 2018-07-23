package com.yinghuaicc.stars.repository.mapper.standard;

import com.yinghuaicc.stars.service.cqrs.standard.dto.request.StandardFloorRentRequestDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.response.StandardProjectRentResponseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandardFloorRentMapper {




    @Select("<script>select * from yhcc_standard_floor_rent " +
            " <where> " +
            "<bind name='search.projectId' value='search.projectId' /> " +
            "<bind name='search.floorId' value='search.floorId' /> " +
            "<bind name='search.floorName' value='search.floorName' /> " +
            "<if test='search.projectId != null'>AND project_id = #{search.projectId}</if> " +
            "<if test='search.floorId != null'>AND floor_id = #{search.floorId}</if> " +
            "<if test='search.floorName != null'>AND floor_name = #{search.floorName}</if> " +
            "</where> " +
            "</script>")
    List<StandardProjectRentResponseDTO> findStandardProjectRentByStandardProjectRentCQRS(@Param("search") StandardFloorRentRequestDTO standardFloorRentRequestDTO);

}
