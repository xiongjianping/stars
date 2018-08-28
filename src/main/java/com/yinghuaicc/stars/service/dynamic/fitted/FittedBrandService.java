package com.yinghuaicc.stars.service.dynamic.fitted;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedBrand;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedBrandSy;
import com.yinghuaicc.stars.service.dynamic.fitted.dto.response.FittedBrandListResponse;

import java.math.BigDecimal;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
public interface FittedBrandService {

    void saveFittedBrand(FittedBrand fittedBrand );

    ResultPageList<FittedBrandListResponse> getFittedBrandList(FittedBrand fittedBrand , PageParam pageParam);

    FittedBrand getFittedBrandById(String id);

    void deleteFittedBrand(String id);

    void updateFittedBrand(FittedBrand fittedBrand);

    BigDecimal getFittedBrand(FittedBrandSy fittedBrand);

}
