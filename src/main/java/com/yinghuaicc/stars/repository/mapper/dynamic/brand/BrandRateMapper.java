package com.yinghuaicc.stars.repository.mapper.dynamic.brand;

import com.yinghuaicc.stars.repository.model.dynamic.brand.BrandRate;
import com.yinghuaicc.stars.service.dynamic.brand.dto.response.BrandRateListResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 */
@Repository
public interface BrandRateMapper {

    /**
     * 新增
     * @param brandRate
     */
    @Insert("insert into yhcc_brand_rate(id,project_id,passenger_flow,sales_volume,effect_time,create_time,create_user,building_id,floor_id,contract_id,form_id,species_id)" +
            " values(#{id},#{projectId},#{passengerFlow},#{salesVolume},#{effectTime},#{createTime},#{createUser},#{buildingId},#{floorId},#{contractId},#{formId},#{speciesId})")
    void saveBrandRate(BrandRate brandRate);

    /**
     * 新增删除
     * @param brandRate
     */
    @Delete("delete from yhcc_brand_rate where project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId} and form_id = #{formId} and species_id = #{speciesId} and contract_id = #{contractId} and effect_time = #{effectTime}")
    void deleteBrandRateById(BrandRate brandRate);



    @Select("<script>" +
            " select a.*,b.name as projectName,c.name as buildingName,d.name as floorName,f.name as brandName,g.name as formName,h.name as speciesName from yhcc_brand_rate a " +
            " LEFT JOIN yhcc_project b on b.id = a.project_id  " +
            " LEFT JOIN yhcc_building c on c.id = a.building_id " +
            " LEFT JOIN yhcc_floor d on d.id = a.floor_id" +
            " LEFT JOIN yhcc_contract e on e.contract_id = a.contract_id" +
            " LEFT JOIN yhcc_brand f on f.id = e.brand_id" +
            " LEFT JOIN yhcc_business_form g on g.id = a.form_id" +
            " LEFT JOIN yhcc_business_species h on h.id = a.species_id" +
            " where " +
            " 1=1 " +
            " <if test='projectId != null'> AND a.project_id = #{projectId} </if> " +
            " <if test='buildingId != null'> AND a.building_id = #{buildingId} </if>" +
            " <if test='floorId != null'> AND a.floor_id = #{floorId} </if> " +
            " <if test='formId != null'> AND a.form_id = #{formId} </if> " +
            " <if test='speciesId != null'> AND a.species_id = #{speciesId} </if>" +
            " <if test='contractId != null'> AND a.contract_id = #{contractId} </if> " +
            " <if test='effectTime != null'> AND a.effect_time = #{effectTime} </if>" +
            " group by a.id " +
            "</script>")
    List<BrandRateListResponse> getBrandRateList(BrandRate brandRate);


    /**
     * 详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_brand_rate where id = #{values}")
    BrandRate getBrandRateById(String id);


    /**
     * 删除
     * @param id
     */
    @Delete("delete from yhcc_brand_rate where id = #{values}")
    void deleteBrandRate(String id);


    /**
     * 更新删除
     * @param brandRate
     */
    @Delete("delete from yhcc_brand_rate where id != #{id} and effect_time = #{effectTime} and project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId} and form_id = #{formId} and species_id = #{speciesId} and contract_id = #{contractId} ")
    void deleteBrandRateByIdAndDate(BrandRate brandRate);


    /**
     * 更新
     * @param brandRate
     */
    @Update("update yhcc_brand_rate set passenger_flow = #{passengerFlow},sales_volume = #{salesVolume},effect_time = #{effectTime},modify_time=NOW(),modify_user=#{modifyUser} where id = #{id}")
    void updateBrandRate(BrandRate brandRate);


    /**
     * 业态客流量
     * @param brandRate
     * @return
     */
    @Select("select sum(passenger_flow) from yhcc_brand_rate where project_id = #{projectId} and form_id = #{formId} and effect_time >= #{createTime} and effect_time <= #{modifyTime} ")
    String getFormRateByIdSy(BrandRate brandRate);


    /**
     * 查看业态面积
     * @param brandRate
     * @return
     */
    @Select(" select sum(c.acreage) from yhcc_brand_rate a LEFT JOIN yhcc_contract b on b.contract_id = a.contract_id  LEFT JOIN yhcc_room c on c.id = b.room_id" +
            " where a.project_id = #{projectId} and a.form_id = #{formId} and b.effect_time >= #{createTime} and b.invalid_time <= #{modifyTime} group by a.contract_id ")
    String getFormAcreageById(BrandRate brandRate);


    /**
     * 业态下品牌所有销售额
     * @param brandRate
     * @return
     */
    @Select("select sum(sales_volume) from yhcc_brand_rate where project_id = #{projectId} and form_id = #{formId} and effect_time >= #{createTime} and effect_time <= #{modifyTime}")
    String getFormBrandById(BrandRate brandRate);



    /**
     * 品牌客流量
     * @param brandRate
     * @return
     */
    @Select("select sum(passenger_flow) from yhcc_brand_rate where project_id = #{projectId} and form_id = #{formId} and contract_id = #{contractId} and effect_time >= #{createTime} and effect_time <= #{modifyTime} ")
    String getBrandRateByIdSy(BrandRate brandRate);


    /**
     * 查看品牌面积
     * @param brandRate
     * @return
     */
    @Select(" select sum(c.acreage) from yhcc_brand_rate a LEFT JOIN yhcc_contract b on b.contract_id = a.contract_id  LEFT JOIN yhcc_room c on c.id = b.room_id" +
            " where a.project_id = #{projectId} and a.form_id = #{formId} and a.contract_id = #{contractId} ")
    String getBrandAcreageById(BrandRate brandRate);


    /**
     * 业态下品牌所有销售额
     * @param brandRate
     * @return
     */
    @Select("select sum(sales_volume) from yhcc_brand_rate where project_id = #{projectId} and form_id = #{formId} and contract_id = #{contractId} and effect_time >= #{createTime} and effect_time <= #{modifyTime}")
    String getBrandBrandById(BrandRate brandRate);




}
