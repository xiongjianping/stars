package com.yinghuaicc.stars.service.dynamic.quarter;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.mapper.MapperFactoryUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.contract.ContractMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.project.ProjectRateMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.quarter.QuarterRateMapper;
import com.yinghuaicc.stars.repository.model.dynamic.quarter.QuarterRate;
import com.yinghuaicc.stars.repository.model.dynamic.quarter.QuarterRateSy;
import com.yinghuaicc.stars.service.dynamic.quarter.dto.response.ProjectQuarterRateResponse;
import com.yinghuaicc.stars.service.dynamic.quarter.dto.response.QuarterRateListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/18.
 */
@Service
public class QuarterRateServiceImpl implements QuarterRateService {

    @Autowired
    ContractMapper contractMapper;


    @Autowired
    private ExceptionUtil exceptionUtil;

    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;

    @Autowired
    QuarterRateMapper quarterRateMapper;

    @Autowired
    ProjectRateMapper projectRateMapper;


    /**
     * 新增
     * @param quarterRate
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveQuarterRate(QuarterRate quarterRate) {
        Integer count = contractMapper.getContractByContractIdAndDate(quarterRate.getContractId(),quarterRate.getEffectTime());
        if(count == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_001");
        }


        //三个值相加 = 适合度
        BigDecimal marketVal = new BigDecimal(quarterRate.getMarketVal() == null ? "0" : quarterRate.getMarketVal());//市场地位
        BigDecimal brandPositioningVal = new BigDecimal(quarterRate.getBrandPositioningVal()  == null ? "0" : quarterRate.getBrandPositioningVal());//品牌定位
        BigDecimal brandImgVal = new BigDecimal(quarterRate.getBrandImgVal()  == null ? "0" : quarterRate.getBrandImgVal());//品牌形象
        BigDecimal shd = marketVal.add(brandPositioningVal).add(brandImgVal); //适合度

        if(shd.intValue() > 100){
            throw exceptionUtil.throwCustomException("RENTING_RATE_002");
        }

        //四个值相加 = 配合度
        BigDecimal rentVal = new BigDecimal(quarterRate.getRentVal() == null ? "0" : quarterRate.getRentVal());//租费收缴率
        BigDecimal chainVal = new BigDecimal(quarterRate.getChainVal()  == null ? "0" : quarterRate.getChainVal());//连锁跟进度
        BigDecimal customerVal = new BigDecimal(quarterRate.getCustomerVal()  == null ? "0" : quarterRate.getCustomerVal());//客服投诉率
        BigDecimal enterpriseVal = new BigDecimal(quarterRate.getEnterpriseVal()  == null ? "0" : quarterRate.getEnterpriseVal());//企划配合度
        BigDecimal phd = rentVal.add(chainVal).add(customerVal).add(enterpriseVal); //配合度

        if(phd.intValue() > 100){
            throw exceptionUtil.throwCustomException("RENTING_RATE_003");
        }
        BigDecimal spz = shd.multiply(phd); //适配值

        quarterRate.setQuarterVal(spz.intValue() <0 ? "0" : spz.toString());
        quarterRate.setId(UuidUtil.randomUUID());
        quarterRate.setCreateTime(LocalDateTime.now());
        quarterRate.setCreateUser(aopResourceEmployeeBean.getName());
        quarterRateMapper.deletQuarterRateById(quarterRate);
        quarterRateMapper.saveQuarterRate(quarterRate);
    }


    /**
     * 查看列表
     * @param quarterRate
     * @param pageParam
     * @return
     */
    @Override
    @Transactional
    public ResultPageList<QuarterRateListResponse> getQuarterRateList(QuarterRate quarterRate, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<QuarterRateListResponse> result = quarterRateMapper.getQuarterRateList(quarterRate);
        return new ResultPageList<QuarterRateListResponse>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Override
    @Transactional
    public QuarterRateListResponse getQuarterRateById(String id) {
        return quarterRateMapper.getQuarterRateById(id);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteQuarterRateById(String id) {
        quarterRateMapper.deleteQuarterRateById(id);
    }


    /**
     * 更新
     * @param quarterRate
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateQuarterRate(QuarterRate quarterRate) {
        Integer count = contractMapper.getContractByContractIdAndDate(quarterRate.getContractId(),quarterRate.getEffectTime());
        if(count == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_001");
        }


        //三个值相加 = 适合度
        BigDecimal marketVal = new BigDecimal(quarterRate.getMarketVal() == null ? "0" : quarterRate.getMarketVal());//市场地位
        BigDecimal brandPositioningVal = new BigDecimal(quarterRate.getBrandPositioningVal()  == null ? "0" : quarterRate.getBrandPositioningVal());//品牌定位
        BigDecimal brandImgVal = new BigDecimal(quarterRate.getBrandImgVal()  == null ? "0" : quarterRate.getBrandImgVal());//品牌形象
        BigDecimal shd = marketVal.add(brandPositioningVal).add(brandImgVal); //适合度

        if(shd.intValue() > 100){
            throw exceptionUtil.throwCustomException("RENTING_RATE_002");
        }

        //四个值相加 = 配合度
        BigDecimal rentVal = new BigDecimal(quarterRate.getRentVal() == null ? "0" : quarterRate.getRentVal());//租费收缴率
        BigDecimal chainVal = new BigDecimal(quarterRate.getChainVal()  == null ? "0" : quarterRate.getChainVal());//连锁跟进度
        BigDecimal customerVal = new BigDecimal(quarterRate.getCustomerVal()  == null ? "0" : quarterRate.getCustomerVal());//客服投诉率
        BigDecimal enterpriseVal = new BigDecimal(quarterRate.getEnterpriseVal()  == null ? "0" : quarterRate.getEnterpriseVal());//企划配合度
        BigDecimal phd = rentVal.add(chainVal).add(customerVal).add(enterpriseVal); //配合度

        if(phd.intValue() > 100){
            throw exceptionUtil.throwCustomException("RENTING_RATE_003");
        }
        BigDecimal spz = shd.multiply(phd); //适配值

        quarterRate.setQuarterVal(spz.intValue() <0 ? "0" : spz.toString());

        quarterRate.setModifyTime(LocalDateTime.now());
        quarterRate.setModifyUser(aopResourceEmployeeBean.getName());
        quarterRateMapper.deleteQuarterRate(quarterRate);
        quarterRateMapper.updateQuarterRate(quarterRate);
    }

    /**
     * 首页品牌级别适配值
     * @param quarterRate
     * @return
     */
    @Override
    public BigDecimal getBrandQuarterRate(QuarterRateSy quarterRate) {
        return new BigDecimal(val(quarterRate)).setScale(2,BigDecimal.ROUND_HALF_UP);
    }


    private String val(QuarterRateSy quarterRate){
        String val = quarterRateMapper.getQuarterContractId(quarterRate);
        if(val != null){
            return val;
        }
        val = quarterRateMapper.getBigQuarterContractId(quarterRate);
        if(val != null){
            return val;
        }
        val = quarterRateMapper.getSmalQuarterContractId(quarterRate);
        if(val != null){
            return val;
        }
        if(val == null || val == ""){
            throw exceptionUtil.throwCustomException("RENTING_RATE_019");
        }
        return val;
    }



    /**
     * 首页项目级别适配值
     * @param quarterRate
     * @return
     */
    @Override
    public BigDecimal getProjectQuarterRate(QuarterRateSy quarterRate) {
      /*  String mj = projectRateMapper.getProjectacreageById(quarterRate.getProjectId()); //项目面积
        if(mj == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_005");
        }*/
        BigDecimal zspz = new BigDecimal("0"); //总适配值
        BigDecimal xmmj = new BigDecimal("0"); //项目面积
        List<ProjectQuarterRateResponse> mj = projectRateMapper.getFloorQuarterRate(MapperFactoryUtil.mapperObject(quarterRate, QuarterRateSy.class));
        List<ProjectQuarterRateResponse> mjs = projectRateMapper.getFloorQuarterRates(MapperFactoryUtil.mapperObject(quarterRate, QuarterRateSy.class));
        for (ProjectQuarterRateResponse m : mj){
            xmmj = xmmj.add(new BigDecimal(m.getAcreage()));
        }
        for (ProjectQuarterRateResponse m : mjs){
            xmmj = xmmj.add(new BigDecimal(m.getAcreage()));
        }


       // List<ProjectQuarterRateResponse> projectQuarterRateResponses = quarterRateMapper.getProjectQuarterRate(quarterRate); //项目铺位面积
        for (ProjectQuarterRateResponse  p :mj ) {
            if(p.getId() == null){
                throw exceptionUtil.throwCustomException("RENTING_RATE_014");
            }
            quarterRate.setContractId(p.getId());
            String val = val(quarterRate); //获取品牌适配值
            //获取到品牌适配值
            BigDecimal ppmj = new BigDecimal(p.getAcreage()); //品牌面积
            BigDecimal ppxm = ppmj.divide(xmmj,2,BigDecimal.ROUND_UP); //品牌面积 / 项目面积

            BigDecimal ppspz = new BigDecimal(val); //适配值
            BigDecimal spz = ppspz.multiply(ppxm); // 适配值 * （品牌面积 / 项目面积）
            zspz = zspz.add(spz); //总相加
        }

        for (ProjectQuarterRateResponse  p :mjs ) {
            if(p.getId() == null){
                throw exceptionUtil.throwCustomException("RENTING_RATE_014");
            }
            quarterRate.setContractId(p.getId());
            String val = val(quarterRate); //获取品牌适配值
            //获取到品牌适配值
            BigDecimal ppmj = new BigDecimal(p.getAcreage()); //品牌面积
            BigDecimal ppxm = ppmj.divide(xmmj); //品牌面积 / 项目面积

            BigDecimal ppspz = new BigDecimal(val); //适配值
            BigDecimal spz = ppspz.multiply(ppxm); // 适配值 * （品牌面积 / 项目面积）
            zspz = zspz.add(spz); //总相加
        }

        return zspz.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 首页业态级别适配值
     * @param quarterRate
     * @return
     */
    @Override
    public BigDecimal getFormQuarterRate(QuarterRateSy quarterRate) {

        List<ProjectQuarterRateResponse> mj = quarterRateMapper.getFormQuarterRate(quarterRate); //业态面积
        List<ProjectQuarterRateResponse> mjs = quarterRateMapper.getFormQuarterRates(quarterRate); //业态面积
        for(ProjectQuarterRateResponse a : mj){
            int i = 1;
            int j = 0;
            for(ProjectQuarterRateResponse b : mjs){

                if(b.getId().equals(a.getId())){
                    i++;
                    a.setAcreage(new BigDecimal(a.getAcreage()).add(new BigDecimal(b.getAcreage())).toString());
                    mjs.remove(j);
                }
                j++;
            }
            a.setAcreage(new BigDecimal(a.getAcreage()).divide(new BigDecimal(i)).toString());
        }

        BigDecimal ytmj = new BigDecimal("0");//业态面积
        BigDecimal zspz = new BigDecimal(0); //总适配值
        for(ProjectQuarterRateResponse a : mj){
            ytmj = ytmj.add(new BigDecimal(a.getAcreage()));
        }
        for(ProjectQuarterRateResponse a : mjs){
            ytmj = ytmj.add(new BigDecimal(a.getAcreage()));
        }

        if(ytmj.intValue() == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_009");
        }
        for(ProjectQuarterRateResponse p : mj){
            quarterRate.setContractId(p.getId());
            String val = val(quarterRate); //获取品牌适配值
            //获取到品牌适配值
            BigDecimal ppmj = new BigDecimal(p.getAcreage()); //品牌面积
            BigDecimal ppyt = ppmj.divide(ytmj,2,BigDecimal.ROUND_UP); //品牌面积 / 品牌面积

            BigDecimal ppspz = new BigDecimal(val); //适配值
            BigDecimal spz = ppspz.multiply(ppyt); // 适配值 * （品牌面积 / 品牌面积）
            zspz = zspz.add(spz); //总相加
        }

        for(ProjectQuarterRateResponse p : mjs){
            quarterRate.setContractId(p.getId());
            String val = val(quarterRate); //获取品牌适配值
            //获取到品牌适配值
            BigDecimal ppmj = new BigDecimal(p.getAcreage()); //品牌面积
            BigDecimal ppyt = ppmj.divide(ytmj,2,BigDecimal.ROUND_UP); //品牌面积 / 品牌面积

            BigDecimal ppspz = new BigDecimal(val); //适配值
            BigDecimal spz = ppspz.multiply(ppyt); // 适配值 * （品牌面积 / 品牌面积）
            zspz = zspz.add(spz); //总相加
        }

        return zspz.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 首页楼层级别适配值
     * @param quarterRate
     * @return
     */
    @Override
    public BigDecimal getFloorQuarterRate(QuarterRateSy quarterRate) {
       /* String mj = quarterRateMapper.getFloorQuarter(quarterRate); //楼层面积
        if(mj == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_007");
        }*/
        BigDecimal zspz = new BigDecimal("0"); //总适配值
        BigDecimal lcmj = new BigDecimal("0"); //楼层面积

        List<ProjectQuarterRateResponse> projectQuarterRateResponses = quarterRateMapper.getFloorQuarterRate(quarterRate); //楼层铺位面积
        List<ProjectQuarterRateResponse> projectQuarterRateResponsess = quarterRateMapper.getFloorQuarterRates(quarterRate); //楼层铺位面积
        for(ProjectQuarterRateResponse a : projectQuarterRateResponses){
            int i = 1;
            int j = 0;
            for(ProjectQuarterRateResponse b : projectQuarterRateResponsess){

                if(b.getId().equals(a.getId())){
                    i++;
                    a.setAcreage(new BigDecimal(a.getAcreage()).add(new BigDecimal(b.getAcreage())).toString());
                    projectQuarterRateResponsess.remove(j);
                }
                j++;
            }
            a.setAcreage(new BigDecimal(a.getAcreage()).divide(new BigDecimal(i)).toString());
        }


        for(ProjectQuarterRateResponse p : projectQuarterRateResponses){
            lcmj = lcmj.add(new BigDecimal(p.getAcreage()));
        }

        for(ProjectQuarterRateResponse p : projectQuarterRateResponsess){
            lcmj = lcmj.add(new BigDecimal(p.getAcreage()));
        }



        for(ProjectQuarterRateResponse p : projectQuarterRateResponses){
            quarterRate.setContractId(p.getId());
            String val = val(quarterRate); //获取品牌适配值
            //获取到品牌适配值
            BigDecimal ppmj = new BigDecimal(p.getAcreage()); //品牌面积
            BigDecimal pplc = ppmj.divide(lcmj,2,BigDecimal.ROUND_UP); //品牌面积 / 楼层面积

            BigDecimal ppspz = new BigDecimal(val); //适配值
            BigDecimal spz = ppspz.multiply(pplc); // 适配值 * （品牌面积 / 楼层面积）
            zspz = zspz.add(spz); //总相加
        }

        for(ProjectQuarterRateResponse p : projectQuarterRateResponsess){
            quarterRate.setContractId(p.getId());
            String val = val(quarterRate); //获取品牌适配值
            //获取到品牌适配值
            BigDecimal ppmj = new BigDecimal(p.getAcreage()); //品牌面积
            BigDecimal pplc = ppmj.divide(lcmj,2,BigDecimal.ROUND_UP); //品牌面积 / 楼层面积

            BigDecimal ppspz = new BigDecimal(val); //适配值
            BigDecimal spz = ppspz.multiply(pplc); // 适配值 * （品牌面积 / 楼层面积）
            zspz = zspz.add(spz); //总相加
        }

        return zspz.setScale(2,BigDecimal.ROUND_HALF_UP);
    }


}
