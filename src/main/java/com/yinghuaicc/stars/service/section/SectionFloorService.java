package com.yinghuaicc.stars.service.section;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.section.SectionFloor;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandRequest;
import com.yinghuaicc.stars.service.section.dto.response.SectionBrandResponse;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/24.
 */
public interface SectionFloorService {

    void saveSectionFloor(SectionFloor sectionFloor);

    SectionFloor getSectionFloorById(String id);

    void deleteSectionFloor(String id);

    ResultPageList<SectionBrandResponse> getSectionFloorList(SectionBrandRequest sectionBrandRequest, PageParam pageParam);

    SectionFloor getSectionFloorListById(SectionBrandRequest sectionBrandRequest);
}
