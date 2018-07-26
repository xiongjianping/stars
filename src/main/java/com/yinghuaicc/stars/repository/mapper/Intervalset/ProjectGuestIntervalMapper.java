package com.yinghuaicc.stars.repository.mapper.Intervalset;

import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.request.ProjectGuestIntervalRequestDTO;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.response.ProjectGuestIntervalResponseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  客销度项目区间设置
 */
@Repository
public interface ProjectGuestIntervalMapper {

    @Select("<script>select * from yhcc_project_guest_interval " +
            " <where> " +
            "<bind name='search.guestVerssionId' value='search.guestVerssionId' /> " +
            "<if test='search.guestVerssionId != null'>AND guest_verssion_id = #{search.guestVerssionId}</if> " +
            "</where> " +
            "</script>")
    List<ProjectGuestIntervalResponseDTO> findProjectGuestIntervalByProjectGuestIntervalCQRS (@Param("search")ProjectGuestIntervalRequestDTO projectGuestIntervalRequestDTO);

    /**
     * 通过项目id查询项目区间设置
     * @param projectId
     * @return
     */
    @Select("select * from yhcc_project_guest_interval where project_id = #{projectId}")
    ProjectGuestIntervalResponseDTO findProjectGuestIntervalByProjectId(@Param("projectId")String projectId);
}
