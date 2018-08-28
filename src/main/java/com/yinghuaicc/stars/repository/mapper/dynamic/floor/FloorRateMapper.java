package com.yinghuaicc.stars.repository.mapper.dynamic.floor;

import com.yinghuaicc.stars.repository.model.dynamic.floor.FloorRate;
import com.yinghuaicc.stars.repository.model.dynamic.floor.FloorRateSy;
import com.yinghuaicc.stars.service.dynamic.floor.dto.response.FloorRateListResponse;
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
public interface FloorRateMapper {

    /**
     * 新增
     * @param floorRate
     */
    @Insert("insert into yhcc_floor_rate(id,project_id,passenger_flow,sales_volume,effect_time,create_time,create_user,building_id,floor_id)" +
            " values(#{id},#{projectId},#{passengerFlow},#{salesVolume},#{effectTime},#{createTime},#{createUser},#{buildingId},#{floorId})")
    void saveFloorRate(FloorRate floorRate);


    /**
     *查看列表
     */
    @Select("<script>" +
            " select a.*,b.name as projectName,c.name as buildingName,d.name as floorName from yhcc_floor_rate a " +
            " LEFT JOIN yhcc_project b on b.id = a.project_id  " +
            " LEFT JOIN yhcc_building c on c.id = a.building_id " +
            " LEFT JOIN yhcc_floor d on d.id = a.floor_id" +
            " where " +
            " 1=1 " +
            " <if test='projectId != null and projectId != \"\"'> AND a.project_id = #{projectId} </if> " +
            " <if test='buildingId != null and buildingId != \"\"'> AND a.building_id = #{buildingId} </if>" +
            " <if test='floorId != null and floorId != \"\"'> AND a.floor_id = #{floorId} </if>" +
            " <if test='effectTime != null and effectTime != \"\"'> AND a.effect_time = #{effectTime} </if>  " +
            " </script>")
    List<FloorRateListResponse> getFloorRateList(FloorRate floorRate);

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_floor_rate where id = #{values}")
    FloorRate getFloorRateById(String id);


    /**
     * 修改
     * @param FloorRate
     */
    @Update("update yhcc_floor_rate set passenger_flow = #{passengerFlow},sales_volume = #{salesVolume},effect_time = #{effectTime},modify_time=NOW(),modify_user=#{modifyUser} where id = #{id}")
    void updateFloorRate(FloorRate FloorRate);


    /**
     * 删除
     * @param id
     */
    @Delete("delete from yhcc_floor_rate where id = #{values}")
    void deleteFloorRate(String id);


    /**
     * 新增删除
     * @param FloorRate
     */
    @Delete("delete from yhcc_floor_rate where project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId} and effect_time = #{effectTime}")
    void deleteFloorRateById(FloorRate FloorRate);


    /**
     * 更新删除
     * @param FloorRate
     */
    @Delete("delete from yhcc_floor_rate where id != #{id} and effect_time = #{effectTime} and project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId}")
    void deleteFloorRateByIdAndDate(FloorRate FloorRate);


    /**
     * 楼层客流量
     * @param floorRate
     * @return
     */
    @Select("select sum(passenger_flow) from yhcc_floor_rate where project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId} and effect_time >= #{createTime} and effect_time <= #{modifyTime} ")
    String getFloorRateByIdSy(FloorRateSy floorRate);




    /**
     * 查看楼层面积
     * @param id
     * @return
     */
    @Select("select acreage from yhcc_floor where id = #{values}")
    String getFloorAcreageById(String id);


    /**
     * 楼层下品牌所有销售额
     * @param floorRate
     * @return
     */
    @Select("select sum(sales_volume) from yhcc_brand_rate where project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId} and effect_time >= #{createTime} and effect_time <= #{modifyTime}")
    String getFloorBrandById(FloorRateSy floorRate);
}
