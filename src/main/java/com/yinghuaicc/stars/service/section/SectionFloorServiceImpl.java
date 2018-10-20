package com.yinghuaicc.stars.service.section;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.section.SectionFloorMapper;
import com.yinghuaicc.stars.repository.model.section.SectionFloor;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandRequest;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandSyRequest;
import com.yinghuaicc.stars.service.section.dto.response.SectionBrandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/24.
 */
@Service
public class SectionFloorServiceImpl implements SectionFloorService {

    @Autowired
    SectionFloorMapper sectionFloorMapper;


    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;

    @Autowired
    ExceptionUtil exceptionUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSectionFloor(SectionFloor sectionFloor) {
        if(sectionFloor.getProjectId() == null){
            throw exceptionUtil.throwCustomException("HELP_PROJEC_SAVE_001");
        }
        sectionFloorMapper.deleteSaveSection(sectionFloor);
        sectionFloor.setId(UuidUtil.randomUUID());
        sectionFloor.setCreateTime(LocalDateTime.now());
        sectionFloor.setCreateUser(aopResourceEmployeeBean.getName());
        sectionFloorMapper.saveSectionFloor(sectionFloor);
    }

    @Override
    public SectionFloor getSectionFloorById(String id) {
        return sectionFloorMapper.getSectionFloorById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSectionFloor(String id) {
        sectionFloorMapper.deleteSectionFloor(id);
    }

    @Override
    public ResultPageList<SectionBrandResponse> getSectionFloorList(SectionBrandRequest sectionBrandRequest, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<SectionBrandResponse> result = sectionFloorMapper.getSectionFloorList(sectionBrandRequest);
        return new ResultPageList<SectionBrandResponse>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }

    @Override
    public SectionFloor getSectionFloorListById(SectionBrandSyRequest sectionBrandRequest) {
        SectionFloor s = sectionFloorMapper.getSectionFloorListById(sectionBrandRequest);
        if(s == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_018");
        }
        return s;
    }

    @Override
    public String getWtSectionFloorListById(SectionBrandSyRequest sectionBrandRequest) {
        String a = "";
        SectionFloor s = sectionFloorMapper.getSectionFloorListById(sectionBrandRequest);
        if(s == null){
           a = "楼层未配置区间";
        }
        return a;
    }
}
