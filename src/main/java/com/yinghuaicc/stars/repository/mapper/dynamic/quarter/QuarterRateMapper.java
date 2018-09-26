package com.yinghuaicc.stars.repository.mapper.dynamic.quarter;

import com.yinghuaicc.stars.repository.model.dynamic.quarter.QuarterRate;
import com.yinghuaicc.stars.repository.model.dynamic.quarter.QuarterRateSy;
import com.yinghuaicc.stars.service.dynamic.quarter.dto.response.ProjectQuarterRateResponse;
import com.yinghuaicc.stars.service.dynamic.quarter.dto.response.QuarterRateListResponse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/18.
 */
@Repository
public interface QuarterRateMapper {

    /**
     * 新增
     * @param quarterRate
     */
    @Insert("insert into yhcc_quarter_rate(id,project_id,building_id,floor_id,form_id,species_id,contract_id,market_val,brand_positioning_val,brand_img_val,rent_val,chain_val,customer_val,enterprise_val,quarter_val,effect_time,create_time,create_user)" +
            " values(#{id},#{projectId},#{buildingId},#{floorId},#{formId},#{speciesId},#{contractId},#{marketVal},#{brandPositioningVal},#{brandImgVal},#{rentVal},#{chainVal},#{customerVal},#{enterpriseVal},#{quarterVal},#{effectTime},#{createTime},#{createUser})")
    void saveQuarterRate(QuarterRate quarterRate);


    /**
     * 新增删除
     * @param quarterRate
     */
    @Delete("delete from yhcc_quarter_rate where project_id = #{projectId} and floor_id = #{floorId} and form_id = #{formId} and species_id = #{speciesId} and contract_id = #{contractId} and effect_time = #{effectTime}")
    void deletQuarterRateById(QuarterRate quarterRate);


    /**
     * 查询列表
     */
    @Select("<script>" +
            " select a.*,b.name as projectName,c.name as buildingName,d.name as floorName,f.name as brandName,g.name as formName,h.name as speciesName from yhcc_quarter_rate a " +
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
            " <if test='floorId != null and floorId != \"\"'> AND a.floor_id = #{floorId} </if> " +
            " <if test='formId != null and formId != \"\"'> AND a.form_id = #{formId} </if> " +
            " <if test='speciesId != null and speciesId != \"\"'> AND a.species_id = #{speciesId} </if>" +
            " <if test='contractId != null and contractId != \"\"'> AND a.contract_id = #{contractId} </if> " +
            " <if test='effectTime != null and effectTime != \"\"'> AND a.effect_time = #{effectTime} </if>" +
            " group by a.id" +
            "</script>")
    List<QuarterRateListResponse> getQuarterRateList(QuarterRate quarterRate);


    /**
     * 查看详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_quarter_rate where id = #{values}")
    QuarterRateListResponse getQuarterRateById(String id);

    /**
     * 删除
     */
    @Delete("delete from yhcc_quarter_rate where id = #{values}")
    void deleteQuarterRateById(String id);


    /**
     * 更新
     * @param quarterRate
     */
    @Update("update yhcc_quarter_rate set market_val = #{marketVal},brand_positioning_val = #{brandPositioningVal},brand_img_val = #{brandImgVal},rent_val = #{rentVal},chain_val = #{chainVal},customer_val = #{customerVal},enterprise_val = #{enterpriseVal},quarter_val = #{quarterVal},effect_time = #{effectTime},modify_time=NOW(),modify_user=#{modifyUser} where id = #{id}")
    void updateQuarterRate(QuarterRate quarterRate);


    /**
     * 更新删除
     * @param quarterRate
     */
    @Delete("delete from yhcc_quarter_rate where id != #{id} and effect_time = #{effectTime} and project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId} and form_id = #{formId} and species_id = #{speciesId} and contract_id = #{contractId} ")
    void deleteQuarterRate(QuarterRate quarterRate);


    /**
     * 首页项目查看品牌面积适配值
     * @param quarterRate
     * @return
     */
    @Select("" +
            " select a.contract_id as id ,(sum(b.acreage)/count(a.contract_id)) as acreage from yhcc_contract a LEFT JOIN yhcc_room b on b.id = a.room_id " +
            " where a.project_id = #{projectId} " +
            " and a.effect_time <= #{createTime}" +
            " and a.invalid_time  >= #{modifyTime} " +
            " " +
            "")
    List<ProjectQuarterRateResponse> getProjectQuarterRate(QuarterRateSy quarterRate);


    /**
     * 首页楼层查看品牌面积适配值
     * @param quarterRate
     * @return
     */
    @Select("" +
            "  select a.contract_id as id ,(sum(c.acreage)/count(b.id)) as acreage from yhcc_brand_rate a left join yhcc_contract b on b.contract_id = a.contract_id LEFT JOIN yhcc_room c on c.id = b.room_id  " +
            "  where a.project_id = #{projectId} and a.floor_id = #{floorId} " +
            " and b.effect_time <= #{createTime} and b.invalid_time >= #{createTime}" +
            "  GROUP BY a.contract_id  " +
            "")
    List<ProjectQuarterRateResponse> getFloorQuarterRate(QuarterRateSy quarterRate);


    @Select("" +
            "  select a.contract_id as id ,(sum(c.acreage)/count(b.id)) as acreage from yhcc_brand_rate a left join yhcc_contract b on b.contract_id = a.contract_id LEFT JOIN yhcc_room c on c.id = b.room_id  " +
            "  where a.project_id = #{projectId} and a.floor_id = #{floorId} " +
            " and b.effect_time <= #{createTime} and b.effect_time >= #{modifyTime}" +
            "  GROUP BY a.contract_id  " +
            "")
    List<ProjectQuarterRateResponse> getFloorQuarterRates(QuarterRateSy quarterRate);

    /**
     * 业态查看品牌总面积
     */
    @Select(" select a.contract_id as id ,(sum(b.acreage)/count(a.contract_id)) as acreage from yhcc_contract a LEFT JOIN yhcc_room b on b.id = a.room_id LEFT JOIN yhcc_brand c on c.id = a.brand_id " +
            " where a.project_id = #{projectId} and c.business_form_id = #{formId} " +
            " and a.effect_time <= #{createTime} and a.invalid_time >= #{createTime} " +
            " GROUP BY a.contract_id")
    List<ProjectQuarterRateResponse> getFormQuarterRate(QuarterRateSy quarterRate);

    @Select(" select a.contract_id as id ,(sum(b.acreage)/count(a.contract_id)) as acreage from yhcc_contract a LEFT JOIN yhcc_room b on b.id = a.room_id LEFT JOIN yhcc_brand c on c.id = a.brand_id " +
            " where a.project_id = #{projectId} and c.business_form_id = #{formId} " +
            " and a.effect_time <= #{createTime} and a.effect_time >= #{modifyTime} " +
            " GROUP BY a.contract_id")
    List<ProjectQuarterRateResponse> getFormQuarterRates(QuarterRateSy quarterRate);

    /**
     * 查看品牌时间差适配值
     * @param quarterRate
     * @return
     */
    @Select(" select a.quarter_val from yhcc_quarter_rate a where " +
            " a.contract_id = #{contractId} " +
            " and a.effect_time >= #{createTime} and a.effect_time <= #{modifyTime} " +
            " ORDER BY a.effect_time desc limit 0,1 ")
    String getQuarterContractId(QuarterRateSy quarterRate);


    /**
     * 查看品牌最大适配值
     * @param quarterRate
     * @return
     */
    @Select(" select a.quarter_val from yhcc_quarter_rate a where " +
            " a.contract_id = #{contractId}  " +
            " and a.effect_time  <= #{modifyTime} " +
            " ORDER BY a.effect_time desc limit 0,1 ")
    String getBigQuarterContractId(QuarterRateSy quarterRate);


    /**
     * 查看品牌最小适配值
     * @param quarterRate
     * @return
     */
    @Select(" select a.quarter_val from yhcc_quarter_rate a where " +
            " a.contract_id = #{contractId}  " +
            " and a.effect_time  >= #{modifyTime} " +
            " ORDER BY a.effect_time limit 0,1 ")
    String getSmalQuarterContractId(QuarterRateSy quarterRate);


    /**
     * 查看楼层面积
     */
    @Select("select acreage from yhcc_floor where project_id = #{projectId} and id = #{floorId} ")
    String getFloorQuarter(QuarterRateSy quarterRate);


}
