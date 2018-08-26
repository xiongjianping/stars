package com.yinghuaicc.stars.repository.mapper.dynamic.standardproject;

import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardBrand;
import com.yinghuaicc.stars.service.dynamic.standardproject.dto.response.StandardBrandListResponse;
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
public interface StandardBrandMapper {


    /**
     * 新增
     * @param standardBrand
     */
    @Insert("insert into yhcc_brand_standard(id,project_id,renting_rate_val,effect_time,create_time,create_user,building_id,floor_id,contract_id,form_id,species_id)" +
            " values(#{id},#{projectId},#{rentingRateVal},#{effectTime},#{createTime},#{createUser},#{buildingId},#{floorId},#{contractId},#{formId},#{speciesId})")
    void saveStandardBrand(StandardBrand standardBrand);

    /**
     * 新增删除
     * @param standardBrand
     */
    @Delete("delete from yhcc_brand_standard where project_id = #{projectId} and form_id = #{formId} and species_id = #{speciesId}  and effect_time = #{effectTime}")
    void deleteStandardBrandById(StandardBrand standardBrand);



    @Select("<script>" +
            " select a.*,b.name as projectName,c.name as buildingName,d.name as floorName,f.name as brandName,g.name as formName,h.name as speciesName from yhcc_brand_standard a " +
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
    List<StandardBrandListResponse> getStandardBrandList(StandardBrand standardBrand);


    /**
     * 详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_brand_standard where id = #{values}")
    StandardBrand getStandardBrandById(String id);


    /**
     * 删除
     * @param id
     */
    @Delete("delete from yhcc_brand_standard where id = #{values}")
    void deleteStandardBrand(String id);


    /**
     * 更新删除
     * @param standardBrand
     */
    @Delete("delete from yhcc_brand_standard where id != #{id} and effect_time = #{effectTime} and project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId} and form_id = #{formId} and species_id = #{speciesId}  ")
    void deleteStandardBrandByIdAndDate(StandardBrand standardBrand);


    /**
     * 更新
     * @param standardBrand
     */
    @Update("update yhcc_brand_standard set renting_rate_val = #{rentingRateVal},effect_time = #{effectTime},modify_time=NOW(),modify_user=#{modifyUser} where id = #{id}")
    void updateStandardBrand(StandardBrand standardBrand);


    /**
     * 查看品牌级：  项目+业态+时间区间 查询项目级溢租率取最小。
     * @param standardBrand
     * @return
     */
    @Select(" select a.renting_rate_val from yhcc_floor_standard a where " +
            " a.project_id = #{projectId}  and a.building_id = #{buildingId} and a.floor_id = #{floorId} " +
            "  and a.species_id = #{speciesId} and a.contract_id = #{contractId} " +
            " and a.effect_time >= #{createTime} and a.effect_time <= #{modifyTime} " +
            " ORDER BY a.effect_time limit 0,1 ")
    String getStandardBrandId(StandardBrand standardBrand);

}
