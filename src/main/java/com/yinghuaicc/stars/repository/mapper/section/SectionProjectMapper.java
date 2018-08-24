package com.yinghuaicc.stars.repository.mapper.section;

import com.yinghuaicc.stars.repository.model.section.SectionProject;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandRequest;
import com.yinghuaicc.stars.service.section.dto.response.SectionBrandResponse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/23.
 */
@Repository
public interface SectionProjectMapper {

    /**
     * 新增
     * @param sectionProject
     */
    @Insert("insert into yhcc_section_project(id,project_id," +
            "excellent_val,good_val,promote_val,reasonable_val,loss_val,excellent_pge_val,good_pge_val,promote_pge_val,reasonable_pge_val," +
            "effect_time,create_time,create_user)" +
            " values(#{id},#{projectId}," +
            "#{excellentVal},#{goodVal},#{promoteVal},#{reasonableVal},#{lossVal},#{excellentPgeVal},#{goodPgeVal},#{promotePgeVal},#{reasonablePgeVal}," +
            "#{effectTime},#{createTime},#{createUser})")
    void saveSectionProject(SectionProject sectionProject);

    /**
     * 新增删除
     * @param sectionProject
     */
    @Delete("delete from yhcc_section_project where project_id = #{projectId} and effect_time = #{effectTime}")
    void deleteSaveSection(SectionProject sectionProject);

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_section_project where id #{values} ")
    SectionProject getSectionProjectById(String id);


    /**
     * 删除
     * @param id
     */
    @Delete("delete from yhcc_section_project where id = #{values}")
    void deleteSectionProject(String id);


    /**
     * 查看列表
     * @param sectionBrandRequest
     * @return
     */
    @Select(" <script>" +
            " select a.*,b.name as projectName from yhcc_section_project a on yhcc_project b on b.id = a.project_id " +
            " where 1 = 1 " +
            " <if test='projectId != null'> AND a.project_id = #{projectId} </if> " +
            " <if test='effectTime != null'> AND a.effect_time = #{effectTime} </if>" +
            " </script>")
    List<SectionBrandResponse> getSectionProjectList(SectionBrandRequest sectionBrandRequest);
}
