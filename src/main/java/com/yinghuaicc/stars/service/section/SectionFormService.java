package com.yinghuaicc.stars.service.section;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.section.SectionForm;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandRequest;
import com.yinghuaicc.stars.service.section.dto.response.SectionBrandResponse;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/24.
 */
public interface SectionFormService {
    void saveSectionForm(SectionForm sectionForm);

    SectionForm getSectionFormById(String id);

    void deleteSectionForm(String id);

    ResultPageList<SectionBrandResponse> getSectionFormList(SectionBrandRequest sectionBrandRequest, PageParam pageParam);

    SectionForm getSectionFormListById(SectionBrandRequest sectionBrandRequest);
}
