package com.yinghuaicc.stars.repository.mapper.dynamic.fitted;

import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedProject;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedProjectSy;
import com.yinghuaicc.stars.service.dynamic.fitted.dto.response.FittedProjectListResponse;
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
public interface FittedProjectMapper {
    /**
     * 新增
     *
     * @param fittedProject
     */
    @Insert("insert into yhcc_project_fitted(id,project_id,fitted_val,effect_time,create_time,create_user)" +
            " values(#{id},#{projectId},#{fittedVal},#{effectTime},#{createTime},#{createUser})")
    void saveFittedProject(FittedProject fittedProject);


    /**
     * 查看列表
     */
    @Select("<script>" +
            " select a.*,b.name as projectName from yhcc_project_fitted a" +
            " LEFT JOIN yhcc_project b on a.project_id = b.id where " +
            " 1=1 " +
            " <if test='projectId != null and projectId != \"\"'> AND a.project_id = #{projectId} </if>" +
            " <if test='effectTime != null and effectTime != \"\"'> AND a.effect_time = #{effectTime} </if> " +
            "  " +
            " </script>")
    List<FittedProjectListResponse> getFittedProjectList(FittedProject fittedProject);


    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    @Select("select * from yhcc_project_fitted where id = #{values}")
    FittedProject getFittedProjectById(String id);


    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from yhcc_project_fitted where id = #{values}")
    void deleteFittedProject(String id);


    /**
     * 新增删除
     *
     * @param fittedProject
     */
    @Delete("delete from yhcc_project_fitted where project_id = #{projectId} and effect_time = #{effectTime}")
    void deleteFittedProjectById(FittedProject fittedProject);


    /**
     * 更新删除
     * @param fittedProject
     */
    @Delete("delete from yhcc_project_fitted where id != #{id} and effect_time = #{effectTime} and project_id = #{projectId}")
    void deleteFittedProjectByIdAndDate(FittedProject fittedProject);


    /**
     * 修改
     * @param fittedProject
     */
    @Update("update yhcc_project_fitted set fitted_val = #{fittedVal},effect_time = #{effectTime},modify_time=NOW(),modify_user=#{modifyUser} where id = #{id}")
    void updatFittedProject(FittedProject fittedProject);



    /**
     * 查看项目级：  项目+时间区间 查询项目级适配值取最小。
     * @param fittedProject
     * @return
     */
    @Select(" select fitted_val from yhcc_project_fitted  where " +
            " project_id = #{projectId} " +
            " and effect_time >= #{modifyTime} " +
            " ORDER BY effect_time desc limit 0,1 ")
    String getFittedProjectId(FittedProjectSy fittedProject);
}