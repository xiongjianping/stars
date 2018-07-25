package com.yinghuaicc.stars.repository.mapper.triangle;

import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.QuarterFittedRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.QuarterFittedResponseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 季度适配值
 */
@Repository
public interface QuarterFittedMapper {

    /**
     * 通过版本id查询季度适配值
     * @param quarterFittedRequestDTO
     * @return
     */
    @Select("<script>" +
            "select * from yhcc_quarter_fitted" +
            " <where> " +
            "<bind name='search.fittedVerssionId' value='search.fittedVerssionId' /> " +
            "<if test='search.fittedVerssionId != null'>AND fitted_verssion_id = #{search.fittedVerssionId}</if> " +
            "</where> " +
            "</script>")
    List<QuarterFittedResponseDTO> findQuarterFittedCQRS( @Param("search") QuarterFittedRequestDTO quarterFittedRequestDTO);


    /**
     * 通过签约id 查询适配值
     * @param contractId
     * @param time
     * @return
     */
    @Select("<script>" +
            "select * from yhcc_quarter_fitted" +
            " <where> " +
            "<bind name='contractId' value='contractId' /> " +
            "<bind name='time' value='time' /> " +
            "<if test='contractId!= null'>AND contract_id = #{contractId}</if> " +
            "<if test='time != null'>AND startTime <= #{time}</if> " +
            "<if test='time != null'>AND endTime >= #{time}</if> " +
            "</where> " +
            "</script>")
    QuarterFittedResponseDTO findQuarterFittedResponseDTOByContractId(String contractId,LocalDateTime time );

}
