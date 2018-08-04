package com.yinghuaicc.stars.repository.mapper.triangle;

import com.yinghuaicc.stars.repository.model.triangle.Triangle;
import com.yinghuaicc.stars.repository.model.triangle.TriangleCQRS;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.TriangleCQRSRequestDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TriangleMapper {

    @Select("select * from yhcc_triangle where id = #{triangleId}")
    List<Triangle> findTriangleByTriangleId(String triangleId);

    /**
     * 通过条件查询三角理论
     * @param triangleCQRSRequestDTO
     * @return
     */
    @Select("<script>select * from yhcc_trianglecqrs " +
            " <where> " +
            "<bind name='search.projectId' value='search.projectId' /> " +
            "<bind name='search.floorId' value='search.floorId ' /> " +
            "<bind name='search.brandName' value=\"'%' + search.brandName + '%'\" /> " +
            "<if test='search.projectId != null'>AND project_id = #{search.projectId}</if> " +
            "<if test='search.floorId != null'>AND floor_Id = #{search.floorId}</if> " +
            "<if test='search.brandName != null and search.brandName !=\"\" '>AND brand_name like #{search.brandName}</if> " +
            "</where> " +
            "</script>")
    List<Triangle> findTriangleCQRSByCQRS(@Param("search") TriangleCQRSRequestDTO triangleCQRSRequestDTO);


    /**
     * 通过签约id查询yhcc_trianglecqrs
     */
    @Select("select * from yhcc_trianglecqrs where contract_id = #{contractId}")
    TriangleCQRS findTriangleCQRSByContractId(String contractId);

    /**
     * 保存三角理论
     * @param triangleCQRS
     */
    @Insert("insert into yhcc_trianglecqrs values(#{id},#{rent},#{guest},#{fitted},#{contractId},#{area}," +
            "#{projectId},#{projectName},#{floorId},#{floorName},#{brandId},#{brandName},#{conditionId},#{conditionName}," +
            "#{majoId},#{majoName},#{roomId},#{roomName},#{createTime},#{modifyTime},#{createUser},#{modifyUser})")
    void saveTriangleCQRS(TriangleCQRS triangleCQRS);

    /**
     * 修改三角理论
     * @param triangleCQRS
     */
    @Insert("update yhcc_trianglecqrs set rent=#{rent},guest=#{guest},fitted=#{fitted},contract_id=#{contractId},area=#{area}," +
            "project_id=#{projectId},project_name= #{projectName},floor_id = #{floorId},floor_name = #{floorName},brand_id = #{brandId}," +
            "brand_name = #{brandName},condition_id  =#{conditionId},condition_name = #{conditionName}," +
            "majo_id = #{majoId},majo_name = #{majoName},room_id = #{roomId},room_name = #{roomName}," +
            "create_time = #{createTime},modify_time = #{modifyTime},create_user = #{createUser},modify_user = #{modifyUser} where id = #{id}")
    void editTriangleCQRS(TriangleCQRS triangleCQRS);



}
