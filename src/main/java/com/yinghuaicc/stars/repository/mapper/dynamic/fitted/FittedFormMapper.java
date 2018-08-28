package com.yinghuaicc.stars.repository.mapper.dynamic.fitted;

import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedForm;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedFormSy;
import com.yinghuaicc.stars.service.dynamic.fitted.dto.response.FittedFormListResponse;
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
public interface FittedFormMapper {

    /**
     * 新增
     * @param fittedForm 
     */
    @Insert("insert into yhcc_form_fitted(id,project_id,fitted_val,effect_time,create_time,create_user,building_id,floor_id,form_id,species_id)" +
            " values(#{id},#{projectId},#{fittedVal},#{effectTime},#{createTime},#{createUser},#{buildingId},#{floorId},#{formId},#{speciesId})")
    void saveFittedForm(FittedForm fittedForm);

    /**
     * 新增删除
     * @param fittedForm 
     */
    @Delete("delete from yhcc_form_fitted where project_id = #{projectId} and form_id = #{formId} and effect_time = #{effectTime}")
    void deleteFittedFormById(FittedForm fittedForm);



    @Select("<script>" +
            " select a.*,b.name as projectName,c.name as buildingName,d.name as floorName,g.name as formName,h.name as speciesName from yhcc_form_fitted a " +
            " LEFT JOIN yhcc_project b on b.id = a.project_id  " +
            " LEFT JOIN yhcc_building c on c.id = a.building_id " +
            " LEFT JOIN yhcc_floor d on d.id = a.floor_id" +
            " LEFT JOIN yhcc_business_form g on g.id = a.form_id" +
            " LEFT JOIN yhcc_business_species h on h.id = a.species_id" +
            " where " +
            " 1=1 " +
            " <if test='projectId != null and projectId != \"\"'> AND a.project_id = #{projectId} </if> " +
            " <if test='buildingId != null and buildingId != \"\"'> AND a.building_id = #{buildingId} </if>" +
            " <if test='floorId != null and floorId != \"\"'> AND a.floor_id = #{floorId} </if> " +
            " <if test='formId != null and formId != \"\"'> AND a.form_id = #{formId} </if> " +
            " <if test='speciesId != null and speciesId != \"\"'> AND a.species_id = #{speciesId} </if>" +
            " <if test='effectTime != null and effectTime != \"\"'> AND a.effect_time = #{effectTime} </if>" +
            " group by a.id " +
            "</script>")
    List<FittedFormListResponse> getFittedFormList(FittedForm fittedForm);

    /**
     * 详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_form_fitted where id = #{values}")
    FittedForm getFittedFormById(String id);


    /**
     * 删除
     * @param id
     */
    @Delete("delete from yhcc_form_fitted where id = #{values}")
    void deleteFittedForm(String id);


    /**
     * 更新删除
     * @param fittedForm 
     */
    @Delete("delete from yhcc_form_fitted where id != #{id} and effect_time = #{effectTime} and project_id = #{projectId}  and form_id = #{formId} ")
    void deleteFittedFormByIdAndDate(FittedForm fittedForm);


    /**
     * 更新
     * @param fittedForm 
     */
    @Update("update yhcc_form_fitted set fitted_val = #{fittedVal},effect_time = #{effectTime},modify_time=NOW(),modify_user=#{modifyUser} where id = #{id}")
    void updateFittedForm(FittedForm fittedForm);




    /**
     * 查看业态级：  项目+时间区间 查询业态级适配值取最小。
     * @param fittedForm
     * @return
     */
    @Select(" select fitted_val from yhcc_form_fitted  where " +
            " project_id = #{projectId}  and form_id = #{formId} " +
            " and effect_time <= #{modifyTime} " +
            " ORDER BY effect_time limit 0,1 ")
    String getFittedFormId(FittedFormSy fittedForm);
}
