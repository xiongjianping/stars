package com.yinghuaicc.stars.repository.mapper.dynamic.standardkxd;

import com.yinghuaicc.stars.repository.model.dynamic.standardkxd.StandardGuest;
import com.yinghuaicc.stars.repository.model.dynamic.standardkxd.StandardGuestSy;
import com.yinghuaicc.stars.service.dynamic.standardKxd.dto.StandardGuestListResponse;
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
public interface StandardGuestMapper {


    /**
     * 新增
     * @param standardGuest
     */
    @Insert("insert into yhcc_standard_guest(id,project_id,building_id,floor_id,form_id,species_id,contract_id,interest_val,price_val,effect_time,create_time,create_user,standard_val)" +
            " values(#{id},#{projectId},#{buildingId},#{floorId},#{formId},#{speciesId},#{contractId},#{interestVal},#{priceVal},#{effectTime},#{createTime},#{createUser},#{standardVal})")
    void saveStandardGuest(StandardGuest standardGuest);


    /**
     * 新增删除
     * @param standardGuest
     */
    @Delete("delete from yhcc_standard_guest where project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId} and form_id = #{formId} and species_id = #{speciesId} and contract_id = #{contractId} and effect_time = #{effectTime}")
    void deletStandardGuestById(StandardGuest standardGuest);


    /**
     * 查询列表
     */
    @Select("<script>" +
            " select a.*,b.name as projectName,c.name as buildingName,d.name as floorName,f.name as brandName,g.name as formName,h.name as speciesName from yhcc_standard_guest a " +
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
    List<StandardGuestListResponse> getStandardGuestList(StandardGuest standardGuest);


    /**
     * 查看详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_standard_guest where id = #{values}")
    StandardGuest getStandardGuestById(String id);

    /**
     * 删除
     */
    @Delete("delete from yhcc_standard_guest where id = #{values}")
    void deleteStandardGuestById(String id);


    /**
     * 更新
     * @param standardGuest
     */
    @Update("update yhcc_standard_guest set interest_val = #{interestVal},price_val = #{priceVal},standard_val = #{standardVal},effect_time = #{effectTime},modify_time=NOW(),modify_user=#{modifyUser} where id = #{id}")
    void updateStandardGuest(StandardGuest standardGuest);


    /**
     * 更新删除
     * @param standardGuest
     */
    @Delete("delete from yhcc_standard_guest where id != #{id} and effect_time = #{effectTime} and project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId} and form_id = #{formId} and species_id = #{speciesId} and contract_id = #{contractId} ")
    void deleteStandardGuest(StandardGuest standardGuest);



    /**
     * 查看品牌面积
     * @param standardGuest
     * @return
     */
    @Select(" select sum(c.acreage) from yhcc_brand_rate a LEFT JOIN yhcc_contract b on b.contract_id = a.contract_id  LEFT JOIN yhcc_room c on c.id = b.room_id" +
            " where a.project_id = #{projectId} and a.contract_id = #{contractId} ")
    String getStandardGuestAcreageById(StandardGuest standardGuest);


    /**
     * 查询项目列表
     */
    @Select("<script> " +
            " select a.*,b.name as projectName,c.name as buildingName,d.name as floorName,f.name as brandName,g.name as formName,h.name as speciesName from yhcc_standard_guest a " +
            " LEFT JOIN yhcc_project b on b.id = a.project_id  " +
            " LEFT JOIN yhcc_building c on c.id = a.building_id " +
            " LEFT JOIN yhcc_floor d on d.id = a.floor_id" +
            " LEFT JOIN yhcc_contract e on e.contract_id = a.contract_id" +
            " LEFT JOIN yhcc_brand f on f.id = e.brand_id" +
            " LEFT JOIN yhcc_business_form g on g.id = a.form_id" +
            " LEFT JOIN yhcc_business_species h on h.id = a.species_id" +
            " where " +
            " str_to_date(a.effect_time,'%Y-%m-%d') >= str_to_date(#{createTime},'%Y-%m-%d') " +
            " and str_to_date(a.effect_time,'%Y-%m-%d')  >= str_to_date(#{modifyTime},'%Y-%m-%d') " +
            " <if test='projectId != null'> AND a.project_id = #{projectId} </if> " +
            " <if test='buildingId != null'> AND a.building_id = #{buildingId} </if>" +
            " <if test='floorId != null'> AND a.floor_id = #{floorId} </if> " +
            " <if test='formId != null'> AND a.form_id = #{formId} </if> " +
            " <if test='speciesId != null'> AND a.species_id = #{speciesId} </if>" +
            " <if test='contractId != null'> AND a.contract_id = #{contractId} </if> " +
            " group by a.contract_id order by a.effect_time " +
            "</script>")
    List<StandardGuestListResponse> getStandardProjectGuestList(StandardGuestSy standardGuest);


    /**
     * 根据项目和签约 查看最小日期数据
     * @param standardGuest
     * @return
     */
    @Select(" select a.* from yhcc_standard_guest a where a.project_id = #{projectId} and contract_id = #{contractId} and a.effect_time <= #{modifyTime} " +
            " ORDER BY a.effect_time LIMIT 0,1 ")
    StandardGuest getStandardProjectGuestListByProject(StandardGuest standardGuest);

    /**
     * 根据项目和签约 查看最小日期数据
     * @param standardGuest
     * @return
     */
    @Select(" select a.* from yhcc_standard_guest a where a.project_id = #{projectId} and a.floor_id = #{floorId} and contract_id = #{contractId} and a.effect_time >= #{createTime} and a.effect_time <= #{modifyTime} " +
            " ORDER BY a.effect_time LIMIT 0,1 ")
    StandardGuest getStandardFloorGuestListByFloor(StandardGuest standardGuest);


    /**
     * 根据项目和业态 查看最小日期数据
     * @param standardGuest
     * @return
     */
    @Select(" select a.* from yhcc_standard_guest a where a.project_id = #{projectId} and a.from_id = #{fromId} and contract_id = #{contractId} and a.effect_time >= #{createTime} and a.effect_time <= #{modifyTime} " +
            " ORDER BY a.effect_time LIMIT 0,1 ")
    StandardGuest getStandardFromGuestListByFloor(StandardGuest standardGuest);



    /**
     * 根据项目和签约 查看最小日期数据
     * @param standardGuest
     * @return
     */
    @Select(" select a.* from yhcc_standard_guest a where a.project_id = #{projectId} and contract_id = #{contractId} and a.effect_time >= #{modifyTime} " +
            " ORDER BY a.effect_time LIMIT 0,1 ")
    StandardGuest getStandardBrandGuestListByFloor(StandardGuestSy standardGuest);


}
