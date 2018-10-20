package com.yinghuaicc.stars.service.section;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.dynamic.brand.Tj;
import com.yinghuaicc.stars.repository.model.dynamic.brand.TjDetail;
import com.yinghuaicc.stars.repository.model.dynamic.brand.TjList;
import com.yinghuaicc.stars.repository.model.section.SectionBrand;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandRequest;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandSyRequest;
import com.yinghuaicc.stars.service.section.dto.response.SectionBrandResponse;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/24.
 */
public interface SectionBrandService {

    void saveSectionBrand(SectionBrand sectionBrand);

    SectionBrand getSectionBrandById(String id);

    void deleteSectionBrand(String id);

    ResultPageList<SectionBrandResponse> getSectionBrandList(SectionBrandRequest sectionBrandRequest, PageParam pageParam);

    SectionBrand getSectionBrandListById(SectionBrandSyRequest sectionBrandRequest);

    String getWtSectionBrandListById(SectionBrandSyRequest sectionBrandRequest);

    List<TjList> getTjList(Tj tj);

    TjDetail getTjDetail(Tj tj);
}
