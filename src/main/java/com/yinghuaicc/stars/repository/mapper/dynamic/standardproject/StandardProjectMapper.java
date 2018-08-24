package com.yinghuaicc.stars.repository.mapper.dynamic.standardproject;

import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardProject;
import com.yinghuaicc.stars.service.dynamic.standardproject.dto.response.StandardProjectListResponse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
@Repository
public interface StandardProjectMapper {
    /**
     * 新增
     *
     * @param standardProject
     */
    @Insert("insert into yhcc_project_standard(id,project_id,renting_rate_val,effect_time,create_time,create_user)" +
            " values(#{id},#{projectId},#{rentingRateVal},#{effectTime},#{createTime},#{createUser})")
    void saveStandardProject(StandardProject standardProject);


    /**
     * 查看列表
     */
    @Select("<script>" +
            " select a.*,b.name as projectName from yhcc_project_standard a" +
            " LEFT JOIN yhcc_project b on a.project_id = b.id where " +
            " 1=1 " +
            " <if test='projectId != null'> AND a.project_id = #{projectId} </if>" +
            " <if test='effectTime != null'> AND a.effect_time = #{effectTime} </if> " +
            "  " +
            " </script>")
    List<StandardProjectListResponse> getStandardProjectList(StandardProject standardProject);


    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    @Select("select * from yhcc_project_standard where id = #{values}")
    StandardProject getStandardProjectById(String id);


    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from yhcc_project_standard where id = #{values}")
    void deleteStandardProject(String id);


    /**
     * 新增删除
     *
     * @param standardProject
     */
    @Delete("delete from yhcc_project_standard where project_id = #{projectId} and effect_time = #{effectTime}")
    void deleteStandardProjectById(StandardProject standardProject);


    /**
     * 更新删除
     * @param standardProject
     */
    @Delete("delete from yhcc_project_standard where id != #{id} and effect_time = #{effectTime} and project_id = #{projectId}")
    void deleteStandardProjectByIdAndDate(StandardProject standardProject);


    /**
     * 修改
     * @param standardProject
     */
    @Update("update yhcc_project_standard set renting_rate_val = #{rentingRateVal},sales_volume = #{salesVolume},effect_time = #{effectTime},modify_time=NOW(),modify_user=#{modifyUser} where id = #{id}")
    void updatStandardProject(StandardProject standardProject);



    /**
     * 查看项目级：  项目+时间区间 查询项目级溢租率取最小。
     * @param standardProject
     * @return
     */
    @Select(" select a.renting_rate_val from yhcc_project_standard a where " +
            " a.project_id = #{projectId} " +
            " and a.effect_time >= #{createTime} and a.effect_time <= #{modifyTime} " +
            " ORDER BY a.effect_time limit 0,1 ")
    String getStandardProjectId(StandardProject standardProject);


}