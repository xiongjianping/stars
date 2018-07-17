package com.yinghuaicc.stars.service.cqrs.brand;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.service.cqrs.brand.dto.request.BrandCQRSListRequestDTO;
import com.yinghuaicc.stars.service.cqrs.brand.dto.response.BrandCQRSInfoResponseDTO;
import com.yinghuaicc.stars.service.cqrs.brand.dto.response.BrandCQRSListResponseDTO;


/**
 * @Author:Fly
 * @Date:Create in 2018/7/12 下午2:30
 * @Description: 品牌
 * @Modified:
 */
public interface BrandCQRSService {

    /**
     *@Author:Fly Created in 2018/7/12 下午3:29
     *@Description: 品牌列表
     */
    ResultPageList<BrandCQRSListResponseDTO> brandListCQRS(BrandCQRSListRequestDTO brandCQRSListRequestDTO, PageParam pageParam);

    /**
     *@Author:Fly Created in 2018/7/12 下午4:05
     *@Description: 品牌详情
     */
    BrandCQRSInfoResponseDTO brandInfoCQRS(String id);
}
