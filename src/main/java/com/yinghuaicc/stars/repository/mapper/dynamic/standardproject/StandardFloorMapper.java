package com.yinghuaicc.stars.repository.mapper.dynamic.standardproject;

import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardFloor;
import com.yinghuaicc.stars.service.dynamic.standardproject.dto.response.StandardFloorListResponse;
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
public interface StandardFloorMapper {

    /**
     * 新增
     * @param standardFloor
     */
    @Insert("insert into yhcc_floor_standard(id,project_id,renting_rate_val,effect_time,create_time,create_user,building_id,floor_id)" +
            " values(#{id},#{projectId},#{rentingRateVal},#{effectTime},#{createTime},#{createUser},#{buildingId},#{floorId})")
    void saveStandardFloor(StandardFloor standardFloor);

    /**
     *查看列表
     */
    @Select("<script>" +
            " select a.*,b.name as projectName,c.name as buildingName,d.name as floorName from yhcc_floor_standard a " +
            " LEFT JOIN yhcc_project b on b.id = a.project_id  " +
            " LEFT JOIN yhcc_building c on c.id = a.building_id " +
            " LEFT JOIN yhcc_floor d on d.id = a.floor_id" +
            " where " +
            " 1=1 " +
            " <if test='projectId != null'> AND a.project_id = #{projectId} </if> " +
            " <if test='buildingId != null'> AND a.building_id = #{buildingId} </if>" +
            " <if test='floorId != null'> AND a.floor_id = #{floorId} </if>" +
            " <if test='effectTime != null'> AND a.effect_time = #{effectTime} </if>  " +
            " </script>")
    List<StandardFloorListResponse> getStandardFloorList(StandardFloor standardFloor);

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_floor_standard where id = #{values}")
    StandardFloor getStandardFloorById(String id);


    /**
     * 修改
     * @param standardFloor
     */
    @Update("update yhcc_floor_standard set renting_rate_val = #{rentingRateVal},effect_time = #{effectTime},modify_time=NOW(),modify_user=#{modifyUser} where id = #{id}")
    void updateStandardFloor(StandardFloor standardFloor);


    /**
     * 删除
     * @param id
     */
    @Delete("delete from yhcc_floor_standard where id = #{values}")
    void deleteStandardFloor(String id);


    /**
     * 新增删除
     * @param standardFloor
     */
    @Delete("delete from yhcc_floor_standard where project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId} and effect_time = #{effectTime}")
    void deleteStandardFloorById(StandardFloor standardFloor);


    /**
     * 更新删除
     * @param standardFloor
     */
    @Delete("delete from yhcc_floor_standard where id != #{id} and effect_time = #{effectTime} and project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId}")
    void deleteStandardFloorByIdAndDate(StandardFloor standardFloor);

    /**
     * 查看楼层级：  项目+业态+时间区间 查询项目级溢租率取最小。
     * @param standardFloor
     * @return
     */
    @Select(" select a.renting_rate_val from yhcc_floor_standard a where " +
            " a.project_id = #{projectId}  and a.building_id = #{buildingId} and a.floor_id = #{floorId} " +
            " and a.effect_time >= #{createTime} and a.effect_time <= #{modifyTime} " +
            " ORDER BY a.effect_time limit 0,1 ")
    String getStandardFloorId(StandardFloor standardFloor);
}
