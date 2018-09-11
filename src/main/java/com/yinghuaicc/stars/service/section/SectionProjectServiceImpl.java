package com.yinghuaicc.stars.service.section;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.section.SectionProjectMapper;
import com.yinghuaicc.stars.repository.model.section.SectionForm;
import com.yinghuaicc.stars.repository.model.section.SectionProject;
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
public class SectionProjectServiceImpl implements SectionProjectService{

    @Autowired
    SectionProjectMapper sectionProjectMapper;


    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;


    @Autowired
    ExceptionUtil exceptionUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSectionProject(SectionProject sectionProject) {
        sectionProjectMapper.deleteSaveSection(sectionProject);
        sectionProject.setId(UuidUtil.randomUUID());
        sectionProject.setCreateTime(LocalDateTime.now());
        sectionProject.setCreateUser(aopResourceEmployeeBean.getName());
        sectionProjectMapper.saveSectionProject(sectionProject);
    }

    @Override
    public SectionProject getSectionProjectById(String id) {
        return sectionProjectMapper.getSectionProjectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSectionProject(String id) {
        sectionProjectMapper.deleteSectionProject(id);
    }

    @Override
    public ResultPageList<SectionBrandResponse> getSectionProjectList(SectionBrandRequest sectionBrandRequest, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<SectionBrandResponse> result = sectionProjectMapper.getSectionProjectList(sectionBrandRequest);
        return new ResultPageList<SectionBrandResponse>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }

    @Override
    public SectionProject getSectionProjectListById(SectionBrandSyRequest sectionBrandRequest) {
        SectionProject s = sectionProjectMapper.getSectionProjectListById(sectionBrandRequest);
        if(s == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_018");
        }
        return s;
    }
}
