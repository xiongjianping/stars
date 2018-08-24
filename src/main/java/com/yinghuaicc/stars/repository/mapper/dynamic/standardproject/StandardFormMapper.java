package com.yinghuaicc.stars.repository.mapper.dynamic.standardproject;

import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardForm;
import com.yinghuaicc.stars.service.dynamic.standardproject.dto.response.StandardFormListResponse;
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
public interface StandardFormMapper {

    /**
     * 新增
     * @param standardForm
     */
    @Insert("insert into yhcc_form_standard(id,project_id,renting_rate_val,effect_time,create_time,create_user,building_id,floor_id,form_id,species_id)" +
            " values(#{id},#{projectId},#{rentingRateVal},#{effectTime},#{createTime},#{createUser},#{buildingId},#{floorId},#{formId},#{speciesId})")
    void saveStandardForm(StandardForm standardForm);

    /**
     * 新增删除
     * @param standardForm
     */
    @Delete("delete from yhcc_form_standard where project_id = #{projectId} and form_id = #{formId} and effect_time = #{effectTime}")
    void deleteStandardFormById(StandardForm standardForm);



    @Select("<script>" +
            " select a.*,b.name as projectName,c.name as buildingName,d.name as floorName,g.name as formName,h.name as speciesName from yhcc_form_standard a " +
            " LEFT JOIN yhcc_project b on b.id = a.project_id  " +
            " LEFT JOIN yhcc_building c on c.id = a.building_id " +
            " LEFT JOIN yhcc_floor d on d.id = a.floor_id" +
            " LEFT JOIN yhcc_business_form g on g.id = a.form_id" +
            " LEFT JOIN yhcc_business_species h on h.id = a.species_id" +
            " where " +
            " 1=1 " +
            " <if test='projectId != null'> AND a.project_id = #{projectId} </if> " +
            " <if test='buildingId != null'> AND a.building_id = #{buildingId} </if>" +
            " <if test='floorId != null'> AND a.floor_id = #{floorId} </if> " +
            " <if test='formId != null'> AND a.form_id = #{formId} </if> " +
            " <if test='speciesId != null'> AND a.species_id = #{speciesId} </if>" +
            " <if test='effectTime != null'> AND a.effect_time = #{effectTime} </if>" +
            " group by a.id " +
            "</script>")
    List<StandardFormListResponse> getStandardFormList(StandardForm standardForm);

    /**
     * 详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_form_standard where id = #{values}")
    StandardForm getStandardFormById(String id);


    /**
     * 删除
     * @param id
     */
    @Delete("delete from yhcc_form_standard where id = #{values}")
    void deleteStandardForm(String id);


    /**
     * 更新删除
     * @param standardForm
     */
    @Delete("delete from yhcc_form_standard where id != #{id} and effect_time = #{effectTime} and project_id = #{projectId}  and form_id = #{formId} ")
    void deleteStandardFormByIdAndDate(StandardForm standardForm);


    /**
     * 更新
     * @param standardForm
     */
    @Update("update yhcc_form_standard set renting_rate_val = #{rentingRateVal},effect_time = #{effectTime},modify_time=NOW(),modify_user=#{modifyUser} where id = #{id}")
    void updateStandardForm(StandardForm standardForm);


    /**
     * 查看业态级：  项目+业态+时间区间 查询项目级溢租率取最小。
     * @param standardForm
     * @return
     */
    @Select(" select a.renting_rate_val from yhcc_form_standard a where " +
            " a.project_id = #{projectId}  and a.form_id = #{formId} " +
            " and a.effect_time >= #{createTime} and a.effect_time <= #{modifyTime} " +
            " ORDER BY a.effect_time limit 0,1 ")
    String getStandardFormId(StandardForm standardForm);

}
