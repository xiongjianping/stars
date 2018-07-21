package com.yinghuaicc.stars.service.cqrs.triangle;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.TriangleListCQRSRequestDTO;

public interface TriangleCQRSService {


    ResultPageList<TriangleListCQRSRequestDTO> findTriangleListCQRS(TriangleListCQRSRequestDTO employeeListCQRSRequestDTO, PageParam pageParam);
}
