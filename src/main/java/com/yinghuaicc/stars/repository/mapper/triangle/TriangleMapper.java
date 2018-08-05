package com.yinghuaicc.stars.repository.mapper.triangle;

import com.yinghuaicc.stars.repository.model.triangle.Triangle;
import com.yinghuaicc.stars.repository.model.triangle.TriangleCQRS;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.TriangleCQRSRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.TimingByConditionResponseDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TriangleMapper {

    /**
     * TriangleTask中定时器专用
     * @param contractId
     * @param contractTime
     * @return
     */
    @Select("select * from yhcc_trianglecqrs where contract_id = #{contractId},contract_time = #{contractTime}")
    List<TriangleCQRS> findTriangleByTriangleId(String contractId,LocalDateTime contractTime);

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
     * TriangleTask中定时器专用
     * @param contractId
     * @param id
     * @return
     */
    @Select("select * from yhcc_trianglecqrs where contract_id = #{triangleId},id = #{id}")
    TriangleCQRS findTriangleCQRSByContractId(String contractId,String id);

    /**
     * 保存三角理论
     * @param triangleCQRS
     */
    @Insert("insert into yhcc_trianglecqrs values(#{id},#{rent},#{guest},#{fitted},#{contractId},#{area}," +
            "#{projectId},#{projectName},#{floorId},#{floorName},#{brandId},#{brandName},#{conditionId},#{conditionName}," +
            "#{majoId},#{majoName},#{roomId},#{roomName},#{contractTime},#{createTime},#{modifyTime},#{createUser},#{modifyUser})")
    void saveTriangleCQRS(TriangleCQRS triangleCQRS);

    /**
     * 修改三角理论
     * @param triangleCQRS
     */
    @Insert("update yhcc_trianglecqrs set rent=#{rent},guest=#{guest},fitted=#{fitted},contract_id=#{contractId},area=#{area}," +
            "project_id=#{projectId},project_name= #{projectName},floor_id = #{floorId},floor_name = #{floorName},brand_id = #{brandId}," +
            "brand_name = #{brandName},condition_id  =#{conditionId},condition_name = #{conditionName}," +
            "majo_id = #{majoId},majo_name = #{majoName},room_id = #{roomId},room_name = #{roomName},contract_time = #{contractTime}" +
            "create_time = #{createTime},modify_time = #{modifyTime},create_user = #{createUser},modify_user = #{modifyUser} where id = #{id}")
    void editTriangleCQRS(TriangleCQRS triangleCQRS);

    /**
     * 通过签约id查询项目、楼层、业态、业种、品牌、铺位等 id
     * @param conditionId
     * @return
     */
    @Select("SELECT p.`id` projectId ,p.`name` projectName,f.`id` floorId, " +
            "f.`name` floorName,r.`id` roomId,r.`name` roomName, b.`id` brandId,b.`name` brandName, " +
            "bf.`id` conditionId ,bf.`name` conditionName, bs.`id` majoId,bs.`name` majoName " +
            "FROM `yhcc_contract` c " +
            "LEFT JOIN `yhcc_project` p ON c.`project_id` = p.`id` " +
            "LEFT JOIN `yhcc_floor` f ON c.`floor_id` = f.`id` " +
            "LEFT JOIN `yhcc_room` r ON c.`room_id` = r.`id` " +
            "LEFT JOIN `yhcc_brand` b ON c.`brand_id` = b.`id` " +
            "LEFT JOIN  `yhcc_business_form` bf ON bf.`id` = b.`business_form_id` " +
            "LEFT JOIN `yhcc_business_species` bs ON bs.`id` = b.`business_species_id` " +
            "WHERE c.`id` = #{conditionId}")
    List<TimingByConditionResponseDTO> findByConditionId(String conditionId);

}
