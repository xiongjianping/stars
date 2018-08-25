package com.yinghuaicc.stars.repository.mapper.section;

import com.yinghuaicc.stars.repository.model.section.SectionFloor;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandRequest;
import com.yinghuaicc.stars.service.section.dto.response.SectionBrandResponse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/23.
 */
@Repository
public interface SectionFloorMapper {
    /**
     * 新增
     * @param sectionFloor
     */
    @Insert("insert into yhcc_section_floor(id,project_id,building_id,floor_id," +
            "excellent_val,good_val,promote_val,reasonable_val,loss_val,excellent_pge_val,good_pge_val,promote_pge_val,reasonable_pge_val," +
            "create_time,create_user)" +
            " values(#{id},#{projectId},#{buildingId},#{floorId}," +
            "#{excellentVal},#{goodVal},#{promoteVal},#{reasonableVal},#{lossVal},#{excellentPgeVal},#{goodPgeVal},#{promotePgeVal},#{reasonablePgeVal}," +
            "#{createTime},#{createUser})")
    void saveSectionFloor(SectionFloor sectionFloor);

    /**
     * 新增删除
     * @param sectionFloor
     */
    @Delete("delete from yhcc_section_floor where project_id = #{projectId} and building_id = #{buildingId} and floor_id = #{floorId}")
    void deleteSaveSection(SectionFloor sectionFloor);

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_section_floor where id #{values} ")
    SectionFloor getSectionFloorById(String id);


    /**
     * 删除
     * @param id
     */
    @Delete("delete from yhcc_section_floor where id = #{values}")
    void deleteSectionFloor(String id);

    /**
     * 查看列表
     * @param sectionBrandRequest
     * @return
     */
    @Select(" <script>" +
            " select a.*,b.name as projectName,c.name as buildingName,d.name as floorName from yhcc_section_floor a on yhcc_project b on b.id = a.project_id " +
            " left join yhcc_building c on c.id = a.building_id " +
            " left join yhcc_floor d on d.id = a.floor_id" +
            " where 1 = 1 " +
            " <if test='projectId != null'> AND a.project_id = #{projectId} </if> " +
            " <if test='buildingId != null'> AND a.building_id = #{buildingId} </if>" +
            " <if test='floorId != null'> AND a.floor_id = #{floorId} </if> " +
            " </script>")
    List<SectionBrandResponse> getSectionFloorList(SectionBrandRequest sectionBrandRequest);


    @Select(" " +
            " select a.* from yhcc_section_floor a  " +
            " where " +
            "   a.project_id = #{projectId} " +
            "  AND a.floor_id = #{floorId} " +
            " ")
    SectionFloor getSectionFloorListById(SectionBrandRequest sectionBrandRequest);


}
