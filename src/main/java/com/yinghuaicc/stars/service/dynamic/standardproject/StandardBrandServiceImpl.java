package com.yinghuaicc.stars.service.dynamic.standardproject;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.contract.ContractMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.standardproject.StandardBrandMapper;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardBrand;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardBrandSy;
import com.yinghuaicc.stars.service.dynamic.standardproject.dto.response.StandardBrandListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
@Service
public class StandardBrandServiceImpl implements StandardBrandService {


    @Autowired
    StandardBrandMapper standardBrandMapper;

    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;

    @Autowired
    private ExceptionUtil exceptionUtil;

    @Autowired
    ContractMapper contractMapper;

    /**
     * 新增
     * @param standardBrand
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveStandardBrand(StandardBrand standardBrand) {
       /* Integer count = contractMapper.getContractByContractIdAndDate(standardBrand.getContractId(),standardBrand.getEffectTime());
        if(count == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_001");
        }*/
        standardBrandMapper.deleteStandardBrandById(standardBrand);
        standardBrand.setId(UuidUtil.randomUUID());
        standardBrand.setCreateTime(LocalDateTime.now());
        standardBrand.setCreateUser(aopResourceEmployeeBean.getName());
        standardBrandMapper.saveStandardBrand(standardBrand);
    }

    /**
     * 列表
     * @param standardBrand
     * @param pageParam
     * @return
     */
    @Override
    public ResultPageList<StandardBrandListResponse> getStandardBrandList(StandardBrand standardBrand, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<StandardBrandListResponse> result = standardBrandMapper.getStandardBrandList(standardBrand);
        return new ResultPageList<StandardBrandListResponse>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }

    /**、
     * 详情
     * @param id
     * @return
     */
    @Override
    public StandardBrand getStandardBrandById(String id) {
        return standardBrandMapper.getStandardBrandById(id);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteStandardBrand(String id) {
        standardBrandMapper.deleteStandardBrand(id);
    }

    /**
     * 更新
     * * @param standardBrand
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStandardBrand(StandardBrand standardBrand) {
        Integer count = contractMapper.getContractByContractIdAndDate(standardBrand.getContractId(),standardBrand.getEffectTime());
        if(count == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_001");
        }

        standardBrandMapper.deleteStandardBrandByIdAndDate(standardBrand);
        standardBrand.setModifyTime(LocalDateTime.now());
        standardBrand.setModifyUser(aopResourceEmployeeBean.getName());
        standardBrandMapper.updateStandardBrand(standardBrand);
    }



    /**
     * 首页标准三角形溢租率 品牌
     * @param standardBrand
     * @return
     */
    @Override
    public BigDecimal getSyBrandCount(StandardBrandSy standardBrand) {
        String val = standardBrandMapper.getStandardBrandId(standardBrand);
        if(val == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        BigDecimal v = new BigDecimal(val).setScale(2,BigDecimal.ROUND_HALF_UP);
        return v;
    }
}
