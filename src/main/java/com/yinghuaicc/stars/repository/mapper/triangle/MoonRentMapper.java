package com.yinghuaicc.stars.repository.mapper.triangle;

import com.yinghuaicc.stars.repository.model.triangle.MoonRent;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.MoonRentRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.MoonRentResponseDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 月溢租率
 */
@Repository
public interface MoonRentMapper {

    /**
     * 通过签约id查询月溢租率
     * @param moonRentRequestDTO
     * @return
     */
    @Select("<script>select * from yhcc_moon_rent " +
            " <where> " +
            "<bind name='search.contractId' value='search.contractId' /> " +
            "<if test='search.contractId != null'>AND contract_id = #{search.contractId}</if> " +
            "</where> " +
            "</script>")
    List<MoonRentResponseDTO> findMoonRentByMoonRentCQRS(@Param("search")MoonRentRequestDTO moonRentRequestDTO);


    /**
     * 新增月溢租率
     * @param moonRent
     */
    @Insert("insert into yhcc_moon_rent " +
            "values( #{id},#{contractId} ,#{rentVerssionId} ,#{rent},#{propertyFee},#{staffEmp},#{fitment}," +
            "#{agencyFee},#{profit},#{yearsmoon},#{createTime},#{modifyTime},#{createUser},#{modifyUser},#{status})")
    void saveMoonRent(MoonRent moonRent);

    @Update("update yhcc_moon_rent set  contract_id=#{contractId},rent_verssion_id=#{rentVerssionId}, rent=#{rent}, property_fee=#{propertyFee}, staff_emp=#{staffEmp}, " +
            "fitment=#{fitment}, agency_Fee=#{agencyFee}, profit=#{profit}, yearsmoon=#{yearsmoon}, create_Time=#{createTime}, modify_Time=#{modifyTime}, create_User=#{createUser}, modify_User=#{modifyUser}, status=#{status} where id = #{id}")
    void editMoonRent(MoonRent moonRent);
}
