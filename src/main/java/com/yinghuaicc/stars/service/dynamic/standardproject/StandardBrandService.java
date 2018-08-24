package com.yinghuaicc.stars.service.dynamic.standardproject;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardBrand;
import com.yinghuaicc.stars.service.dynamic.standardproject.dto.response.StandardBrandListResponse;

import java.math.BigDecimal;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
public interface StandardBrandService {

    void saveStandardBrand(StandardBrand standardBrand);

    ResultPageList<StandardBrandListResponse> getStandardBrandList(StandardBrand standardBrand, PageParam pageParam);

    StandardBrand getStandardBrandById(String id);

    void deleteStandardBrand(String id);

    void updateStandardBrand(StandardBrand standardBrand);

    BigDecimal getSyBrandCount(StandardBrand standardBrand);
}
