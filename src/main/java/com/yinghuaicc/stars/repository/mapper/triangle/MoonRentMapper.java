package com.yinghuaicc.stars.repository.mapper.triangle;

import com.yinghuaicc.stars.repository.model.triangle.MoonRent;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.MoonRentRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.MoonRentResponseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 月溢租率
 */
@Repository
public interface MoonRentMapper {


    @Select("<script>select * from yhcc_moon_rent " +
            " <where> " +
            "<bind name='search.rentVerssionId' value='search.rentVerssionId' /> " +
            "<if test='search.rentVerssionId != null'>AND rent_verssion_id = #{search.rentVerssionId}</if> " +
            "</where> " +
            "</script>")
    List<MoonRentResponseDTO> findMoonRentByMoonRentCQRS(@Param("search")MoonRentRequestDTO moonRentRequestDTO);
}
