package com.yinghuaicc.stars.repository.mapper.standard;

import com.yinghuaicc.stars.service.cqrs.standard.dto.request.StandardProjectRentRequestDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.response.StandardProjectRentResponseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandardProjectRentMapper {




    @Select("<script>select * from yhcc_standard_project_rent " +
            " <where> " +
            "<bind name='search.projectId' value='search.projectId' /> " +
            "<if test='search.projectId != null'>AND project_id = #{search.projectId}</if> " +
            "</where> " +
            "</script>")
    List<StandardProjectRentResponseDTO> findStandardProjectRentByStandardProjectRentCQRS (@Param("search")StandardProjectRentRequestDTO standardProjectRentRequestDTO);

}
