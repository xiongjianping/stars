package com.yinghuaicc.stars.repository.mapper.dynamic.rentingRate;

import com.yinghuaicc.stars.repository.model.dynamic.brand.BrandRate;
import com.yinghuaicc.stars.repository.model.dynamic.rentingRate.RentingRate;
import com.yinghuaicc.stars.repository.model.dynamic.standardkxd.StandardGuest;
import com.yinghuaicc.stars.service.dynamic.rentingRate.dto.request.getRentingRateListRequest;
import com.yinghuaicc.stars.service.dynamic.rentingRate.dto.response.RentingRateDetailResponse;
import com.yinghuaicc.stars.service.dynamic.rentingRate.dto.response.RentingRateListResponse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/16.
 * 溢租率
 */
@Repository
public interface RentingRateMapper {

    /**
     * 新增
     * @param rentingRate
     */
    @Insert("insert into yhcc_renting_rate (id,rent,propertyfee,depreciation,agency_fee,labor_cost,contract_id,effect_time,create_time,create_user) " +
            " values(#{id},#{rent},#{propertyfee},#{depreciation},#{agencyFee},#{laborCost},#{contractId},#{effectTime},#{createTime},#{createUser})")
    void saveRentingRate(RentingRate rentingRate);


    /**
     * 查看列表
     */
    @Select(" <script> select " +
            " a.id as id," +
            " h.name as projectName," +
            " c.name as brandName," +
            " GROUP_CONCAT(' 楼层：',d.name,'  铺位号：',e.name,' ') as roomName," +
            " f.name as fromName," +
            " g.name as speciesName," +
            " sum(a.rent) as rent," +
            " sum(a.propertyfee) as propertyfee," +
            " sum(a.depreciation) as depreciation," +
            " sum(a.labor_cost) as laborCost," +
            " sum(a.agency_fee) as agencyFee," +
            " a.effect_time," +
            " a.create_time," +
            " a.create_user " +
            " from yhcc_renting_rate a" +
            " LEFT JOIN yhcc_contract b on a.contract_id = b.contract_id" +
            " LEFT JOIN yhcc_brand c on c.id = b.brand_id " +
            " LEFT JOIN yhcc_floor d on d.id = b.floor_id" +
            " LEFT JOIN yhcc_room e on e.id = b.room_id" +
            " LEFT JOIN yhcc_business_form f on f.id = c.business_form_id" +
            " LEFT JOIN yhcc_business_species g on g.id = c.business_species_id" +
            " LEFT JOIN yhcc_project h on h.id = b.project_id" +
            " LEFT JOIN yhcc_building i on i.id = d.building_id" +
            " WHERE 1=1 " +
            " <if test='projectId != null'> AND h.id = #{projectId} </if> " +
            " <if test='buildingId != null'> AND i.id = #{buildingId} </if> " +
            " <if test='floorId != null'> AND d.id = #{floorId} </if> " +
            " <if test='businessFormId != null'> AND f.id = #{businessFormId} </if> " +
            " <if test='brandName != null'> AND c.name like #{brandName} </if> " +
            " <if test='effectTime != null'> AND a.effect_time = #{effectTime} </if> " +
            " GROUP BY b.project_id " +
            " </script>")
    List<RentingRateListResponse> getRentingRateList(getRentingRateListRequest getRentingRateListRequest);


    /**
     * 查看详情
     */
    @Select(" select a.id," +
            " h.area_id as areaId," +
            " h.id as projectId," +
            " i.id as buildingId," +
            " d.id as floorId," +
            " f.id as formId," +
            " g.id as speciesId," +
            " c.id as brandId," +
            " a.rent," +
            " a.propertyfee," +
            " a.depreciation," +
            " a.labor_cost," +
            " a.agency_fee," +
            " a.effect_time" +
            " from yhcc_renting_rate a " +
            " LEFT JOIN yhcc_contract b on a.contract_id = b.contract_id " +
            " LEFT JOIN yhcc_brand c on c.id = b.brand_id " +
            " LEFT JOIN yhcc_floor d on d.id = b.floor_id" +
            " LEFT JOIN yhcc_room e on e.id = b.room_id" +
            " LEFT JOIN yhcc_business_form f on f.id = c.business_form_id" +
            " LEFT JOIN yhcc_business_species g on g.id = c.business_species_id" +
            " LEFT JOIN yhcc_project h on h.id = b.project_id" +
            " LEFT JOIN yhcc_building i on i.id = d.building_id" +
            " WHERE a.id = #{values}" +
            " GROUP BY b.project_id")
    RentingRateDetailResponse getRentingRateById(String id);


    /**
     * 更新
     */
    @Update(" update yhcc_renting_rate a set " +
            " a.rent = #{rent}," +
            " a.propertyfee = #{propertyfee}," +
            " a.depreciation = #{depreciation}," +
            " a.labor_cost = #{laborCost}," +
            " a.agency_fee = #{agencyFee}," +
            " a.effect_time = #{effectTime}," +
            " a.modify_user = #{modifyUser}," +
            " a.modify_time = NOW() " +
            " WHERE a.id = #{id}")
    void updateRentingRate(RentingRate rentingRate);

    /**
     * 删除
     */
    @Delete("delete from yhcc_renting_rate where id = #{values}")
    void deleteRentingRateById(String id);


    /**
     * 删除根据签约ID
     */
    @Delete("delete from yhcc_renting_rate where contract_id = #{contractId} and left(effect_time,'7') = #{effectTime}")
    void deleteRentingRateByIdTime(RentingRate rentingRate);

    /**
     * 删除根据签约ID
     */
    @Delete("delete from yhcc_renting_rate where contract_id = #{contractId} and id != #{id} and left(effect_time,'7') = #{effectTime}")
    void deleteRentingRateByIdDate(RentingRate rentingRate);



    /**
     * 标准三角形 客销度查询
     */
    @Select(" <script> select " +
            " sum(a.rent) as rent," +
            " sum(a.propertyfee) as propertyfee," +
            " sum(a.depreciation) as depreciation," +
            " sum(a.labor_cost) as laborCost," +
            " sum(a.agency_fee) as agencyFee " +
            " from yhcc_renting_rate a" +
            " LEFT JOIN yhcc_contract b on a.contract_id = b.contract_id" +
            " LEFT JOIN yhcc_brand c on c.id = b.brand_id " +
            " LEFT JOIN yhcc_floor d on d.id = b.floor_id" +
            " LEFT JOIN yhcc_room e on e.id = b.room_id" +
            " LEFT JOIN yhcc_business_form f on f.id = c.business_form_id" +
            " LEFT JOIN yhcc_business_species g on g.id = c.business_species_id" +
            " LEFT JOIN yhcc_project h on h.id = b.project_id" +
            " LEFT JOIN yhcc_building i on i.id = d.building_id" +
            " WHERE 1=1 " +
            " <if test='projectId != null'> AND h.id = #{projectId} </if> " +
            " <if test='buildingId != null'> AND i.id = #{buildingId} </if> " +
            " <if test='floorId != null'> AND d.id = #{floorId} </if> " +
            " <if test='businessFormId != null'> AND f.id = #{businessFormId} </if> " +
            " <if test='contractId != null'> AND a.contract_id = #{contractId} </if> " +
            " <if test='effectTime != null'> AND str_to_date(a.effect_time,'%Y-%m') = str_to_date(#{effectTime},'%Y-%m') </if> " +
            " GROUP BY b.project_id " +
            " </script>")
    RentingRateListResponse getRentingRateByKxd(getRentingRateListRequest getRentingRateListRequest);


    /**
     * 根据项目ID查看所有旗下品牌
     * @param standardGuest
     * @return
     */
    @Select(" select a.* from yhcc_renting_rate a LEFT JOIN yhcc_contract b on b.contract_id = a.contract_id " +
            " where b.project_id = #{projectId} and  date_format(a.effect_time,'%Y-%m') >= left(#{createTime},7) and date_format(a.effect_time,'%Y-%m') <= left(#{modifyTime},7)  " +
            " GROUP BY a.contract_id order by a.effect_time ")
    List<RentingRate> getSyProjectRentingRate(StandardGuest standardGuest);


    /**
     * 项目下品牌所有销售额
     * @param brandRate
     * @return
     */
    @Select("select sum(sales_volume) from yhcc_brand_rate where project_id = #{projectId} and contract_id = #{contractId} and effect_time >= #{createTime} and effect_time <= #{modifyTime}")
    String getProjectBrandById(BrandRate brandRate);


    /**
     * 根据项目ID,楼层ID查看所有旗下品牌
     * @param standardGuest
     * @return
     */
    @Select(" select a.* from yhcc_renting_rate a LEFT JOIN yhcc_contract b on b.contract_id = a.contract_id " +
            " where b.project_id = #{projectId} and b.floor_id = #{floorId} and  date_format(a.effect_time,'%Y-%m') >= left(#{createTime},7) and date_format(a.effect_time,'%Y-%m') <= left(#{modifyTime},7)  " +
            " GROUP BY a.contract_id order by a.effect_time ")
    List<RentingRate> getSyFloorRentingRate(StandardGuest standardGuest);


    /**
     * 楼层下品牌所有销售额
     * @param brandRate
     * @return
     */
    @Select("select sum(sales_volume) from yhcc_brand_rate where project_id = #{projectId} and floor_id = #{floorId} and contract_id = #{contractId} and effect_time >= #{createTime} and effect_time <= #{modifyTime}")
    String getFloorBrandById(BrandRate brandRate);


    /**
     * 根据项目ID,业态ID查看所有旗下品牌
     * @param standardGuest
     * @return
     */
    @Select(" select a.* from yhcc_renting_rate a LEFT JOIN yhcc_contract b on b.contract_id = a.contract_id " +
            " where b.project_id = #{projectId} and b.from_id = #{formId} and date_format(a.effect_time,'%Y-%m') >= left(#{createTime},7) and date_format(a.effect_time,'%Y-%m') <= left(#{modifyTime},7)  " +
            " GROUP BY a.contract_id order by a.effect_time ")
    List<RentingRate> getSyFormRentingRate(StandardGuest standardGuest);


    /**
     * 业态下品牌所有销售额
     * @param brandRate
     * @return
     */
    @Select("select sum(sales_volume) from yhcc_brand_rate where project_id = #{projectId} and b.from_id = #{formId} and contract_id = #{contractId} and effect_time >= #{createTime} and effect_time <= #{modifyTime}")
    String getFromBrandById(BrandRate brandRate);




    /**
     * 根据项目ID,签约ID查看所有旗下品牌
     * @param standardGuest
     * @return
     */
    @Select(" select a.* from yhcc_renting_rate a LEFT JOIN yhcc_contract b on b.contract_id = a.contract_id " +
            " where b.project_id = #{projectId} and b.contract_id = #{contractId} and date_format(a.effect_time,'%Y-%m') >= left(#{createTime},7) and date_format(a.effect_time,'%Y-%m') <= left(#{modifyTime},7)  " +
            " GROUP BY a.contract_id order by a.effect_time ")
    RentingRate getSyBrandRentingRate(StandardGuest standardGuest);


    /**
     * 品牌所有销售额
     * @param brandRate
     * @return
     */
    @Select("select sum(sales_volume) from yhcc_brand_rate where project_id = #{projectId}  and contract_id = #{contractId} and effect_time >= #{createTime} and effect_time <= #{modifyTime}")
    String getBrandById(BrandRate brandRate);


}
