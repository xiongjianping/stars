package com.yinghuaicc.stars.repository.mapper.dynamic.fitted;

import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedFloor;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedFloorSy;
import com.yinghuaicc.stars.service.dynamic.fitted.dto.response.FittedFloorListResponse;
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
public interface FittedFloorMapper {

    /**
     * 新增
     * @param fittedFloor
     */
    @Insert("insert into yhcc_floor_fitted(id,project_id,fitted_val,effect_time,create_time,create_user,building_id,floor_id)" +
            " values(#{id},#{projectId},#{fittedVal},#{effectTime},#{createTime},#{createUser},#{buildingId},#{floorId})")
    void saveFittedFloor(FittedFloor fittedFloor);

    /**
     *查看列表
     */
    @Select("<script>" +
            " select a.*,b.name as projectName,c.name as buildingName,d.name as floorName from yhcc_floor_fitted a " +
            " LEFT JOIN yhcc_project b on b.id = a.project_id  " +
            " LEFT JOIN yhcc_building c on c.id = a.building_id " +
            " LEFT JOIN yhcc_floor d on d.id = a.floor_id" +
            " where " +
            " 1=1 " +
            " <if test='projectId != null and projectId != \"\"'> AND a.project_id = #{projectId} </if> " +
            " <if test='floorId != null and floorId != \"\"'> AND a.floor_id = #{floorId} </if>" +
            " <if test='effectTime != null and effectTime != \"\"'> AND a.effect_time = #{effectTime} </if>  " +
            " </script>")
    List<FittedFloorListResponse> getFittedFloorList(FittedFloor fittedFloor);

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_floor_fitted where id = #{values}")
    FittedFloor getFittedFloorById(String id);


    /**
     * 修改
     * @param fittedFloor
     */
    @Update("update yhcc_floor_fitted set fitted_val = #{fittedVal},effect_time = #{effectTime},modify_time=NOW(),modify_user=#{modifyUser} where id = #{id}")
    void updateFittedFloor(FittedFloor fittedFloor);


    /**
     * 删除
     * @param id
     */
    @Delete("delete from yhcc_floor_fitted where id = #{values}")
    void deleteFittedFloor(String id);


    /**
     * 新增删除
     * @param fittedFloor
     */
    @Delete("delete from yhcc_floor_fitted where project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId} and effect_time = #{effectTime}")
    void deleteFittedFloorById(FittedFloor fittedFloor);


    /**
     * 更新删除
     * @param fittedFloor
     */
    @Delete("delete from yhcc_floor_fitted where id != #{id} and effect_time = #{effectTime} and project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId}")
    void deleteFittedFloorByIdAndDate(FittedFloor fittedFloor);


    /**
     * 查楼层级：  项目+时间区间 查询楼层级适配值取最小。
     * @param fittedFloor
     * @return
     */
    @Select(" select fitted_val from yhcc_floor_fitted  where " +
            " project_id = #{projectId} and floor_id = #{floorId} " +
            " and effect_time <= #{modifyTime} " +
            " ORDER BY effect_time desc limit 0,1 ")
    String getFittedFloorId(FittedFloorSy fittedFloor);

    @Select(" select fitted_val from yhcc_floor_fitted  where " +
            " project_id = #{projectId} and floor_id = #{floorId} " +
            " and effect_time >= #{modifyTime} " +
            " ORDER BY effect_time limit 0,1 ")
    String getFittedFloorIds(FittedFloorSy fittedFloor);
}
