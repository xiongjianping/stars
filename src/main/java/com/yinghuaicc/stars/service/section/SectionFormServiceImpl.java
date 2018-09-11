package com.yinghuaicc.stars.service.section;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.section.SectionFormMapper;
import com.yinghuaicc.stars.repository.model.section.SectionFloor;
import com.yinghuaicc.stars.repository.model.section.SectionForm;
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
public class SectionFormServiceImpl implements SectionFormService{

    @Autowired
    SectionFormMapper sectionFormMapper;


    @Autowired
    ExceptionUtil exceptionUtil;

    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSectionForm(SectionForm sectionForm) {
        sectionFormMapper.deleteSaveSection(sectionForm);
        sectionForm.setId(UuidUtil.randomUUID());
        sectionForm.setCreateTime(LocalDateTime.now());
        sectionForm.setCreateUser(aopResourceEmployeeBean.getName());
        sectionFormMapper.saveSectionForm(sectionForm);
    }

    @Override
    public SectionForm getSectionFormById(String id) {
        return sectionFormMapper.getSectionFormById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSectionForm(String id) {
        sectionFormMapper.deleteSectionForm(id);
    }

    @Override
    public ResultPageList<SectionBrandResponse> getSectionFormList(SectionBrandRequest sectionBrandRequest, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<SectionBrandResponse> result = sectionFormMapper.getSectionFormList(sectionBrandRequest);
        return new ResultPageList<SectionBrandResponse>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }

    @Override
    public SectionForm getSectionFormListById(SectionBrandSyRequest sectionBrandRequest) {
        SectionForm s = sectionFormMapper.getSectionFormListById(sectionBrandRequest);
        if(s == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_018");
        }
        return s;
    }
}
