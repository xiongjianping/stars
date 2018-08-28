package com.yinghuaicc.stars.service.section;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.section.SectionBrand;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandRequest;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandSyRequest;
import com.yinghuaicc.stars.service.section.dto.response.SectionBrandResponse;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/24.
 */
public interface SectionBrandService {

    void saveSectionBrand(SectionBrand sectionBrand);

    SectionBrand getSectionBrandById(String id);

    void deleteSectionBrand(String id);

    ResultPageList<SectionBrandResponse> getSectionBrandList(SectionBrandRequest sectionBrandRequest, PageParam pageParam);

    SectionBrand getSectionBrandListById(SectionBrandSyRequest sectionBrandRequest);

}
