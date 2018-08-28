package com.yinghuaicc.stars.service.dynamic.fitted;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.contract.ContractMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.fitted.FittedBrandMapper;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedBrand;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedBrandSy;
import com.yinghuaicc.stars.service.dynamic.fitted.dto.response.FittedBrandListResponse;
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
public class FittedBrandServiceImpl implements FittedBrandService {


    @Autowired
    FittedBrandMapper fittedBrandMapper ;

    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;

    @Autowired
    private ExceptionUtil exceptionUtil;

    @Autowired
    ContractMapper contractMapper;

    /**
     * 新增
     * @param fittedBrand
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFittedBrand(FittedBrand fittedBrand) {

        fittedBrandMapper.deleteFittedBrandById(fittedBrand);
        fittedBrand.setId(UuidUtil.randomUUID());
        fittedBrand.setCreateTime(LocalDateTime.now());
        fittedBrand.setCreateUser(aopResourceEmployeeBean.getName());
        fittedBrandMapper.saveFittedBrand(fittedBrand);
    }

    /**
     * 列表
     * @param fittedBrand
     * @param pageParam
     * @return
     */
    @Override
    public ResultPageList<FittedBrandListResponse> getFittedBrandList(FittedBrand fittedBrand, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<FittedBrandListResponse> result = fittedBrandMapper.getFittedBrandList(fittedBrand);
        return new ResultPageList<FittedBrandListResponse>()
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
    public FittedBrand getFittedBrandById(String id) {
        return fittedBrandMapper.getFittedBrandById(id);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFittedBrand(String id) {
        fittedBrandMapper.deleteFittedBrand(id);
    }

    /**
     * 更新
     * * @param fittedBrand
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFittedBrand(FittedBrand fittedBrand) {
        fittedBrandMapper.deleteFittedBrandByIdAndDate(fittedBrand);
        fittedBrand.setModifyTime(LocalDateTime.now());
        fittedBrand.setModifyUser(aopResourceEmployeeBean.getName());
        fittedBrandMapper.updateFittedBrand(fittedBrand);
    }


    /**
     * 标准三角形，适配值，业种级别
     * @param fittedBrand
     * @return
     */
    @Override
    public BigDecimal getFittedBrand(FittedBrandSy fittedBrand) {
        //查找业种
        String species = fittedBrandMapper.getFittedBrandSpeciesId(fittedBrand);
        if(species == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        fittedBrand.setSpeciesId(species);

        String val = fittedBrandMapper.getFittedBrandId(fittedBrand);
        if(val == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        BigDecimal v = new BigDecimal(val).setScale(2,BigDecimal.ROUND_HALF_UP);
        return v;
    }
}
