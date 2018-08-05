package com.yinghuaicc.stars.service.cqrs.standard;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.standard.StandardBrandSale;
import com.yinghuaicc.stars.service.cqrs.standard.dto.request.StandardBrandSaleRequestDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.request.StandardConditionFittedRequestDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.response.StandardBrandSaleResponseDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.response.StandardConditionFittedResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface StandardBrandSaleService {
    /**
     * 通过项目id 查询标准三角形品牌客销度
     * @param standardBrandSaleequestDTO
     * @param pageParam
     * @return
     */
    ResultPageList<StandardBrandSaleResponseDTO> findStandardBrandSaleByStandardBrandSaleCQRS(StandardBrandSaleRequestDTO standardBrandSaleequestDTO, PageParam pageParam);


    /**
     * 添加标准三角形品牌客销度
     * @param standardBrandSale
     */
    void saveStandardBrandSale(StandardBrandSale standardBrandSale);

    /**
     * 根据签约id查询标准品牌客销度
     * @param contractId
     * @param createTime
     * @return
     */
    List<StandardBrandSale> findStandardBrandSaleByContractId(String contractId, LocalDateTime createTime);
}
