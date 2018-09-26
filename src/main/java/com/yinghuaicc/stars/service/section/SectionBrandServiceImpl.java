package com.yinghuaicc.stars.service.section;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.dynamic.fitted.FittedBrandMapper;
import com.yinghuaicc.stars.repository.mapper.section.SectionBrandMapper;
import com.yinghuaicc.stars.repository.model.section.SectionBrand;
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
public class SectionBrandServiceImpl implements SectionBrandService {

    @Autowired
    SectionBrandMapper sectionBrandMapper;


    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;

    @Autowired
    FittedBrandMapper fittedBrandMapper;

    @Autowired
    ExceptionUtil exceptionUtil;

    /**
     * 新增业态级别
     * @param sectionBrand
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSectionBrand(SectionBrand sectionBrand) {
        if(sectionBrand.getProjectId() == null){
            throw exceptionUtil.throwCustomException("HELP_PROJEC_SAVE_001");
        }
        sectionBrandMapper.deleteSaveSection(sectionBrand);
        sectionBrand.setId(UuidUtil.randomUUID());
        sectionBrand.setCreateTime(LocalDateTime.now());
        sectionBrand.setCreateUser(aopResourceEmployeeBean.getName());
        sectionBrandMapper.saveSectionBrand(sectionBrand);
    }

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Override
    public SectionBrand getSectionBrandById(String id) {
        return sectionBrandMapper.getSectionBrand(id);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSectionBrand(String id) {
        sectionBrandMapper.deleteSectionBrand(id);
    }

    /**
     * 查看列表
     * @param sectionBrandRequest
     * @param pageParam
     * @return
     */
    @Override
    public ResultPageList<SectionBrandResponse> getSectionBrandList(SectionBrandRequest sectionBrandRequest, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<SectionBrandResponse> result = sectionBrandMapper.getSectionBrandList(sectionBrandRequest);
        return new ResultPageList<SectionBrandResponse>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }

    @Override
    public SectionBrand getSectionBrandListById(SectionBrandSyRequest sectionBrandRequest) {
     //   String species = fittedBrandMapper.getFittedBrandSpeciesId(MapperFactoryUtil.mapperObject(sectionBrandRequest, FittedBrandSy.class));
      //  sectionBrandRequest.setSpeciesId(species);
        SectionBrand s = sectionBrandMapper.getSectionBrandListById(sectionBrandRequest);
        if(s == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_018");
        }
        return s;

    }
}
