package com.yinghuaicc.stars.service.cqrs.triangle;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.QuarterFittedRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.QuarterFittedResponseDTO;
import org.apache.ibatis.annotations.Param;


/**
 * 季度适配值
 */
public interface QuarterFittedService {


    /**
     * 通过适配值版本id查询季度适配值列表
     * @param quarterFittedRequestDTO
     * @return
     */
    ResultPageList<QuarterFittedResponseDTO> findQuarterFittedCQRS(@Param("search") QuarterFittedRequestDTO quarterFittedRequestDTO, PageParam pageParam);
}
