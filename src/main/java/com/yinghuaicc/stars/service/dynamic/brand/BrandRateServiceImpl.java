package com.yinghuaicc.stars.service.dynamic.brand;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.date.LocalDateTimeUtils;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.contract.ContractMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.brand.BrandRateMapper;
import com.yinghuaicc.stars.repository.model.dynamic.brand.BrandRate;
import com.yinghuaicc.stars.repository.model.dynamic.brand.BrandRateSy;
import com.yinghuaicc.stars.service.dynamic.brand.dto.response.BrandRateListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/18.
 */
@Service
public class BrandRateServiceImpl implements BrandRateService {

    @Autowired
    BrandRateMapper brandRateMapper;

    @Autowired
    ContractMapper contractMapper;


    @Autowired
    private ExceptionUtil exceptionUtil;

    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;

    /**
     * 新增
     * @param brandRate
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBrandRate(BrandRate brandRate) {
        Integer count = contractMapper.getContractByContractIdAndDate(brandRate.getContractId(),brandRate.getEffectTime());
        if(count == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_001");
        }
        brandRateMapper.deleteBrandRateById(brandRate);
        brandRate.setId(UuidUtil.randomUUID());
        brandRate.setCreateTime(LocalDateTime.now());
        brandRate.setCreateUser(aopResourceEmployeeBean.getName());
        brandRateMapper.saveBrandRate(brandRate);
    }

    /**
     * 列表
     * @param brandRate
     * @param pageParam
     * @return
     */
    @Override
    public ResultPageList<BrandRateListResponse> getBrandRateList(BrandRate brandRate, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<BrandRateListResponse> result = brandRateMapper.getBrandRateList(brandRate);
        return new ResultPageList<BrandRateListResponse>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @Override
    @Transactional
    public BrandRate getBrandRateById(String id) {
        return brandRateMapper.getBrandRateById(id);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBrandRate(String id) {
        brandRateMapper.deleteBrandRate(id);
    }

    @Override
    public void updateBrandRate(BrandRate brandRate) {
        Integer count = contractMapper.getContractByContractIdAndDate(brandRate.getContractId(),brandRate.getEffectTime());
        if(count == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_001");
        }

        brandRateMapper.deleteBrandRateByIdAndDate(brandRate);
        brandRate.setModifyTime(LocalDateTime.now());
        brandRate.setModifyUser(aopResourceEmployeeBean.getName());
        brandRateMapper.updateBrandRate(brandRate);
    }

    /**
     * 首页品牌客销度
     * @param brandRate
     * @return
     */
    @Override
    public BigDecimal getSyBrandRateCount(BrandRateSy brandRate) {
        String kll = brandRateMapper.getBrandRateByIdSy(brandRate); //品牌客流量
        if(kll == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_022");
        }
        BigDecimal zkll = new BigDecimal(kll); //总客流量

        String mj = brandRateMapper.getBrandAcreageById(brandRate); //面积
        if(mj == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_014");
        }
        BigDecimal acreage = new BigDecimal(mj); //面积

        Duration duration = Duration.between(LocalDateTimeUtils.StringDate(brandRate.getCreateTime()),LocalDateTimeUtils.StringDate(brandRate.getModifyTime()));
        BigDecimal day = new BigDecimal(duration.toDays()+1); //时间差

        BigDecimal rx = zkll.divide(acreage.multiply(day),2,BigDecimal.ROUND_UP); // 1 品牌客流量除以面积
        String pb = brandRateMapper.getBrandBrandById(brandRate); // 2 品牌下所有品牌销售额
        if(pb == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_008");
        }
        BigDecimal sale = new BigDecimal(pb);
        BigDecimal px = sale.divide(acreage.multiply(day),2,BigDecimal.ROUND_UP); //销售额 / 面积

        BigDecimal kxd = rx.multiply(px);
        return kxd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 首页业态客销度
     * @param brandRate
     * @return
     */
    @Override
    public BigDecimal getSyFormRateCount(BrandRateSy brandRate) {
        String kll = brandRateMapper.getFormRateByIdSy(brandRate); //业态客流量
        if(kll == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_008");
        }
        BigDecimal zkll = new BigDecimal(kll); //总客流量

        String mj = brandRateMapper.getFormAcreageById(brandRate); //面积
        if(mj == null){
            mj = brandRateMapper.getFormAcreageByIda(brandRate); //面积
            if(mj == null){
                mj = brandRateMapper.getFormAcreageByIdb(brandRate); //面积
                if(mj == null){
                    throw exceptionUtil.throwCustomException("RENTING_RATE_009");
                }
            }
        }
        BigDecimal acreage = new BigDecimal(mj); //面积

        Duration duration = Duration.between(LocalDateTimeUtils.StringDate(brandRate.getCreateTime()),LocalDateTimeUtils.StringDate(brandRate.getModifyTime()));
        BigDecimal day = new BigDecimal(duration.toDays()+1); //时间差

        BigDecimal rx = zkll.divide(acreage.multiply(day),2,BigDecimal.ROUND_UP); // 1 业态客流量除以面积
        String pb = brandRateMapper.getFormBrandById(brandRate); // 2 业态下所有品牌销售额
        if(pb == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        BigDecimal sale = new BigDecimal(pb);
        BigDecimal px = sale.divide(acreage.multiply(day),2,BigDecimal.ROUND_UP); //销售额 / 面积

        BigDecimal kxd = rx.multiply(px);
        return kxd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}
