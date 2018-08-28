package com.yinghuaicc.stars.service.dynamic.brand;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.dynamic.brand.BrandRate;
import com.yinghuaicc.stars.repository.model.dynamic.brand.BrandRateSy;
import com.yinghuaicc.stars.service.dynamic.brand.dto.response.BrandRateListResponse;

import java.math.BigDecimal;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 */
public interface BrandRateService {

    void saveBrandRate(BrandRate brandRate);

    ResultPageList<BrandRateListResponse> getBrandRateList(BrandRate FloorRate, PageParam pageParam);

    BrandRate getBrandRateById(String id);

    void deleteBrandRate(String id);

    void updateBrandRate(BrandRate brandRate);

    public BigDecimal getSyBrandRateCount(BrandRateSy brandRate);

    public BigDecimal getSyFormRateCount(BrandRateSy brandRate);
}
