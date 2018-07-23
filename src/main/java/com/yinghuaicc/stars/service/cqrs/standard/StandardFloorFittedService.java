package com.yinghuaicc.stars.service.cqrs.standard;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.service.cqrs.standard.dto.request.StandardFloorFittedRequestDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.response.StandardFloorFittedResponseDTO;

public interface StandardFloorFittedService {


    /**
     * 通过项目id 查询标准三角形楼层适配值
     * @param standardFloorFittedRequestDTO
     * @param pageParam
     * @return
     */
    ResultPageList<StandardFloorFittedResponseDTO> findStandardFloorFittedByStandardProjectFittedCQRS(StandardFloorFittedRequestDTO standardFloorFittedRequestDTO, PageParam pageParam);

}
