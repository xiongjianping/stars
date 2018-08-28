package com.yinghuaicc.stars.repository.mapper.dynamic.fitted;

import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedBrand;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedBrandSy;
import com.yinghuaicc.stars.service.dynamic.fitted.dto.response.FittedBrandListResponse;
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
public interface FittedBrandMapper {


    /**
     * 新增
     * @param fittedBrand
     */
    @Insert("insert into yhcc_brand_fitted(id,project_id,fitted_val,effect_time,create_time,create_user,building_id,floor_id,contract_id,form_id,species_id)" +
            " values(#{id},#{projectId},#{fittedVal},#{effectTime},#{createTime},#{createUser},#{buildingId},#{floorId},#{contractId},#{formId},#{speciesId})")
    void saveFittedBrand(FittedBrand fittedBrand);

    /**
     * 新增删除
     * @param fittedBrand
     */
    @Delete("delete from yhcc_brand_fitted where project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId} and form_id = #{formId} and species_id = #{speciesId} and effect_time = #{effectTime}")
    void deleteFittedBrandById(FittedBrand fittedBrand);



    @Select("<script>" +
            " select a.*,b.name as projectName,c.name as buildingName,d.name as floorName,f.name as brandName,g.name as formName,h.name as speciesName from yhcc_brand_fitted a " +
            " LEFT JOIN yhcc_project b on b.id = a.project_id  " +
            " LEFT JOIN yhcc_building c on c.id = a.building_id " +
            " LEFT JOIN yhcc_floor d on d.id = a.floor_id" +
            " LEFT JOIN yhcc_contract e on e.contract_id = a.contract_id" +
            " LEFT JOIN yhcc_brand f on f.id = e.brand_id" +
            " LEFT JOIN yhcc_business_form g on g.id = a.form_id" +
            " LEFT JOIN yhcc_business_species h on h.id = a.species_id" +
            " where " +
            " 1=1 " +
            " <if test='projectId != null and projectId != \"\"'> AND a.project_id = #{projectId} </if> " +
            " <if test='buildingId != null and buildingId != \"\"'> AND a.building_id = #{buildingId} </if>" +
            " <if test='floorId != null and floorId != \"\"'> AND a.floor_id = #{floorId} </if> " +
            " <if test='formId != null and formId != \"\"'> AND a.form_id = #{formId} </if> " +
            " <if test='speciesId != null and speciesId != \"\"'> AND a.species_id = #{speciesId} </if>" +
            " <if test='contractId != null and contractId != \"\"'> AND a.contract_id = #{contractId} </if> " +
            " <if test='effectTime != null and effectTime != \"\"'> AND a.effect_time = #{effectTime} </if>" +
            " group by a.id " +
            "</script>")
    List<FittedBrandListResponse> getFittedBrandList(FittedBrand fittedBrand);


    /**
     * 详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_brand_fitted where id = #{values}")
    FittedBrand getFittedBrandById(String id);


    /**
     * 删除
     * @param id
     */
    @Delete("delete from yhcc_brand_fitted where id = #{values}")
    void deleteFittedBrand(String id);


    /**
     * 更新删除
     * @param fittedBrand
     */
    @Delete("delete from yhcc_brand_fitted where id != #{id} and effect_time = #{effectTime} and project_id = #{projectId} and form_id = #{formId} and species_id = #{speciesId}  ")
    void deleteFittedBrandByIdAndDate(FittedBrand fittedBrand);


    /**
     * 更新
     * @param fittedBrand
     */
    @Update("update yhcc_brand_fitted set fitted_val = #{fittedVal},effect_time = #{effectTime},modify_time=NOW(),modify_user=#{modifyUser} where id = #{id}")
    void updateFittedBrand(FittedBrand fittedBrand);

    /**
     * 查楼层级：  项目+时间区间 查询楼层级适配值取最小。
     * @param fittedBrand
     * @return
     */
    @Select(" select fitted_val from yhcc_brand_fitted  where " +
            " project_id = #{projectId} and form_id = #{formId} and species_id = #{speciesId} " +
            " and effect_time >= #{createTime} and effect_time <= #{modifyTime} " +
            " ORDER BY effect_time limit 0,1 ")
    String getFittedBrandId(FittedBrandSy fittedBrand);


    //根据签约
    @Select(" select b.business_species_id from yhcc_contract a LEFT JOIN yhcc_brand b on b.id " +
            "   where a.contract_id = #{contractId} and a.project_id = #{projectId} and b.business_form_id = #{formId} limit 0,1 ")
    String getFittedBrandSpeciesId(FittedBrandSy fittedBrand);
}
