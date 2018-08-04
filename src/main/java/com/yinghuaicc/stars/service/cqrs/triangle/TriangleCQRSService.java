package com.yinghuaicc.stars.service.cqrs.triangle;


import com.yinghuaicc.stars.repository.model.triangle.TriangleCQRS;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.BrandTriangleRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.TriangleCQRSRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.AllSalePassengerFlowResponseDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.BrandTriangleResponseDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.TriangleCQRSResponseDTO;

import java.util.List;


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


    /**
     * 统计全国区域下客流量、销售额
     * @return
     */
    List<AllSalePassengerFlowResponseDTO> findSalePassengerFlowAll();

    /**
     * 通过条件查询三角理论列表
     * @return
     */
    List<TriangleCQRSResponseDTO> findTriangleCQRSByCQRS(TriangleCQRSRequestDTO triangleCQRSRequestDTO);

}
