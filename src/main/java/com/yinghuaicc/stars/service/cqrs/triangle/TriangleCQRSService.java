package com.yinghuaicc.stars.service.cqrs.triangle;


import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.BrandTriangleRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.BrandTriangleResponseDTO;


public interface TriangleCQRSService {

    /**
     * 通过品牌id查询品牌三角形
     * @param brandTriangleRequestDTO
     * @return
     */
   BrandTriangleResponseDTO findBrandTriangleByBrandId(BrandTriangleRequestDTO brandTriangleRequestDTO);

}
