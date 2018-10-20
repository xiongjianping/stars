package com.yinghuaicc.stars.repository.mapper.dynamic.project;

import com.yinghuaicc.stars.repository.model.dynamic.project.ProjectRate;
import com.yinghuaicc.stars.repository.model.dynamic.project.ProjectRateSy;
import com.yinghuaicc.stars.repository.model.dynamic.quarter.QuarterRateSy;
import com.yinghuaicc.stars.service.dynamic.project.dto.response.ProjectRateListResponse;
import com.yinghuaicc.stars.service.dynamic.quarter.dto.response.ProjectQuarterRateResponse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 */
@Repository
public interface ProjectRateMapper {

    /**
     * 新增
     * @param projectRate
     */
    @Insert("insert into yhcc_project_rate(id,project_id,passenger_flow,effect_time,create_time,create_user)" +
            " values(#{id},#{projectId},#{passengerFlow},#{effectTime},#{createTime},#{createUser})")
    void saveProjectRate(ProjectRate projectRate);


    /**
     *查看列表
     */
    @Select("<script>" +
            " select a.*,b.name as projectName from yhcc_project_rate a" +
            " LEFT JOIN yhcc_project b on a.project_id = b.id where " +
            " 1=1 " +
            " <if test='projectId != null and projectId != \"\"'> AND a.project_id = #{projectId} </if>" +
            " <if test='effectTime != null and effectTime != \"\"'> AND a.effect_time = #{effectTime} </if> " +
            "  " +
            " </script>")
    List<ProjectRateListResponse> getProjectRateList(ProjectRate projectRate);

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_project_rate where id = #{values}")
    ProjectRate getProjectRateById(String id);


    /**
     * 修改
     * @param projectRate
     */
    @Update("update yhcc_project_rate set passenger_flow = #{passengerFlow},sales_volume = #{salesVolume},effect_time = #{effectTime},modify_time=NOW(),modify_user=#{modifyUser} where id = #{id}")
    void updateProjectRate(ProjectRate projectRate);


    /**
     * 删除
     * @param id
     */
    @Delete("delete from yhcc_project_rate where id = #{values}")
    void deleteProjectRate(String id);


    /**
     * 新增删除
     * @param projectRate
     */
    @Delete("delete from yhcc_project_rate where project_id = #{projectId} and effect_time = #{effectTime}")
    void deleteProjectRateById(ProjectRate projectRate);


    /**
     * 更新删除
     * @param projectRate
     */
    @Delete("delete from yhcc_project_rate where id != #{id} and effect_time = #{effectTime} and project_id = #{projectId}")
    void deleteProjectRateByIdAndDate(ProjectRate projectRate);

    /**
     * 项目客流量
     * @param projectRate
     * @return
     */
    @Select("select sum(passenger_flow) from yhcc_project_rate where project_id = #{projectId} and effect_time >= #{createTime} and effect_time <= #{modifyTime} ")
    String getProjectRateByIdSy(ProjectRateSy projectRate);

    /**
     * 查看项目面积
     * @param id
     * @return
     */
    @Select("select acreage from yhcc_project where id = #{values}")
    String getProjectacreageById(String id);

    @Select("" +
            "  select b.contract_id as id,(sum(c.acreage)/count(b.id)) as acreage   from  yhcc_contract b  LEFT JOIN yhcc_room c on c.id = b.room_id  " +
            "  where b.project_id = #{projectId} " +
            " and b.effect_time <= #{createTime} and b.invalid_time >= #{createTime}" +
            "  GROUP BY b.contract_id  " +
            "")
    List<ProjectQuarterRateResponse> getFloorQuarterRate(QuarterRateSy quarterRate);


    @Select("" +
            "  select b.contract_id as id,(sum(c.acreage)/count(b.id)) as acreage  from yhcc_contract b  LEFT JOIN yhcc_room c on c.id = b.room_id  " +
            "  where b.project_id = #{projectId}  " +
            " and b.effect_time <= #{createTime} and b.effect_time >= #{modifyTime}" +
            "  GROUP BY b.contract_id  " +
            "")
    List<ProjectQuarterRateResponse> getFloorQuarterRates(QuarterRateSy quarterRate);

    /**
     * 项目下品牌所有销售额
     * @param projectRate
     * @return
     */
    @Select("select sum(sales_volume) from yhcc_brand_rate where project_id = #{projectId} and effect_time >= #{createTime} and effect_time <= #{modifyTime}")
    String getProjectBrandById(ProjectRateSy projectRate);

}
