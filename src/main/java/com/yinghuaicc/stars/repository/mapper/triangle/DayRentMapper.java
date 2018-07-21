package com.yinghuaicc.stars.repository.mapper.triangle;

import com.yinghuaicc.stars.repository.model.triangle.DayRent;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.DayRentRequestDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 每日溢租率
 */
@Repository
public interface DayRentMapper {

    @Select("<script>select * from yhcc_day_rent " +
            " <where> " +
            "<bind name='search.contractId' value='search.contractId' /> " +
            "<bind name='search.createTime' value='search.createTime ' /> " +
            "<if test='search.contractId != null'>AND contract_id = #{search.contractId}</if> " +
            "<if test='search.createTime != null'>AND create_time &gt; #{search.createTime}</if> " +
            "</where> " +
            "</script>")
    List<DayRent> findDayRentByDayRentCQRS (@Param("search")DayRentRequestDTO dayRentRequestDTO);
}
