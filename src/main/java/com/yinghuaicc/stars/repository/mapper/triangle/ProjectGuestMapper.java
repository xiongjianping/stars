package com.yinghuaicc.stars.repository.mapper.triangle;

import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.ProjectGuestRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.ProjectGuestResponseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目客销度
 */
@Repository
public interface ProjectGuestMapper {
    @Select("<script>select * from yhcc_project_guest " +
            " <where> " +
            "<bind name='search.guestVerssionId' value='search.guestVerssionId' /> " +
            "<if test='search.guestVerssionId != null'>AND guest_verssion_id = #{search.guestVerssionId}</if> " +
            "</where> " +
            "</script>")
    List<ProjectGuestResponseDTO> findProjectGuestByProjectGuestCQRS (@Param("search")ProjectGuestRequestDTO projectGuestRequestDTO);
}