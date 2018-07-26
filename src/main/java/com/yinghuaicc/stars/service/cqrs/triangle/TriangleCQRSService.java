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

    /**
     * 通过项目id查询项目三角形
     * @param brandTriangleRequestDTO
     * @return
     */
    BrandTriangleResponseDTO findBrandTriangleByProjectId(BrandTriangleRequestDTO brandTriangleRequestDTO);


    /**
     * 通过项目id，楼层id查询项目三角形
     * @param brandTriangleRequestDTO
     * @return
     */
    BrandTriangleResponseDTO findBrandTriangleByFloorId(BrandTriangleRequestDTO brandTriangleRequestDTO);


    /**
     * 业态级查询三角形
     * @param brandTriangleRequestDTO
     * @return
     */
    BrandTriangleResponseDTO findBrandTriangleByConditionId(BrandTriangleRequestDTO brandTriangleRequestDTO);


}
