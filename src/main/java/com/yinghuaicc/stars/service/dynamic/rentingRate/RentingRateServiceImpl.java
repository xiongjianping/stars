package com.yinghuaicc.stars.service.dynamic.rentingRate;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.mapper.MapperFactoryUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.contract.ContractMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.rentingRate.RentingRateMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.standardkxd.StandardGuestMapper;
import com.yinghuaicc.stars.repository.model.contract.Contract;
import com.yinghuaicc.stars.repository.model.dynamic.brand.BrandRate;
import com.yinghuaicc.stars.repository.model.dynamic.rentingRate.RentingRate;
import com.yinghuaicc.stars.repository.model.dynamic.standardkxd.StandardGuest;
import com.yinghuaicc.stars.repository.model.dynamic.standardkxd.StandardGuestSy;
import com.yinghuaicc.stars.service.dynamic.rentingRate.dto.request.getRentingRateListRequest;
import com.yinghuaicc.stars.service.dynamic.rentingRate.dto.response.RentingRateDetailResponse;
import com.yinghuaicc.stars.service.dynamic.rentingRate.dto.response.RentingRateListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 */
@Service
public class RentingRateServiceImpl implements RentingRateService {

    @Autowired
    RentingRateMapper rentingRateMapper;

    @Autowired
    ContractMapper contractMapper;

    @Autowired
    private ExceptionUtil exceptionUtil;

    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;

    @Autowired
    StandardGuestMapper standardGuestMapper;

    /**
     * 新增
     * @param rentingRate
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRentingRate(RentingRate rentingRate) {
        Integer count = contractMapper.getContractByContractIdAndDates(rentingRate.getContractId(),rentingRate.getEffectTime());
        if(count == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_001");
        }
        rentingRateMapper.deleteRentingRateByIdTime(rentingRate);

        rentingRate.setId(UuidUtil.randomUUID());
        rentingRate.setCreateTime(LocalDateTime.now());
        rentingRate.setCreateUser(aopResourceEmployeeBean.getName());
        rentingRateMapper.saveRentingRate(rentingRate);
    }

    /**
     * 查看列表
     * @param getRentingRateListRequest
     * @param pageParam
     * @return
     */
    @Override
    @Transactional
    public ResultPageList<RentingRateListResponse> getRentingRateList(getRentingRateListRequest getRentingRateListRequest, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<RentingRateListResponse> result = rentingRateMapper.getRentingRateList(getRentingRateListRequest);
        return new ResultPageList<RentingRateListResponse>()
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
    public RentingRateDetailResponse getRentingRateById(String id) {
        return rentingRateMapper.getRentingRateById(id);
    }

    /**
     * 修改
     * @param rentingRate
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRentingRate(RentingRate rentingRate) {
        Integer count = contractMapper.getContractByContractIdAndDate(rentingRate.getContractId(),rentingRate.getEffectTime());
        if(count == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_001");
        }
        rentingRateMapper.deleteRentingRateByIdDate(rentingRate);
        rentingRate.setModifyTime(LocalDateTime.now());
        rentingRate.setModifyUser(aopResourceEmployeeBean.getName());
        rentingRateMapper.updateRentingRate(rentingRate);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRentingRateById(String id) {
        rentingRateMapper.deleteRentingRateById(id);
    }

    /**
     * 首页项目
     * @param standardGuest
     * @return
     */
    @Override
    public BigDecimal getSyProjectRentingRateCount(StandardGuestSy standardGuest) {
        return getCont(standardGuest);
        //1根据项目ID查看所有品牌动态溢组率
      /*  List<RentingRate> list = rentingRateMapper.getSyProjectRentingRate(standardGuest);
        if(list == null) {
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        BigDecimal zjlr = new BigDecimal("0");
        BigDecimal zfm = new BigDecimal("0");
        list.forEach(p->{
            BigDecimal rent = new BigDecimal(p.getRent()); //租金
            BigDecimal propertyfee = new BigDecimal(p.getPropertyfee()); //物业费
            BigDecimal fm = rent.add(propertyfee); //分母

            BigDecimal depreciation = new BigDecimal(p.getDepreciation());//装修折旧费
            BigDecimal agencyFee = new BigDecimal(p.getAgencyFee());//代理费\
            BigDecimal laborCost = new BigDecimal(p.getLaborCost());//人工成本
            BigDecimal gdcb = rent.add(propertyfee).add(depreciation).add(agencyFee).add(laborCost); //固定成品
            //净利润=销售收入（动态客销度）*毛利率(标准客销度)-固定成本
            StandardGuest s = new StandardGuest();
            s.setProjectId(standardGuest.getProjectId());
            s.setContractId(p.getContractId());
            s.setCreateTime(standardGuest.getCreateTime());
            s.setModifyTime(standardGuest.getModifyTime());
            StandardGuest bz = standardGuestMapper.getStandardProjectGuestListByProject(s);
            if(bz == null) {
                throw exceptionUtil.throwCustomException("RENTING_RATE_011");
            }
            BigDecimal interestval = new BigDecimal(bz.getInterestVal());//毛利率

            //动态
            String xssr = rentingRateMapper.getProjectBrandById(MapperFactoryUtil.mapperObject(s,BrandRate.class));
            BigDecimal salesVolume = new BigDecimal(xssr);//销售收入
            BigDecimal jlr = salesVolume.multiply(interestval).subtract(gdcb);
            zjlr.add(jlr);
            zfm.add(fm);
        });
        BigDecimal yzl = zjlr.divide(zfm);
        if(yzl.intValue() == 0) {
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        return yzl.setScale(2,BigDecimal.ROUND_UP);*/
    }


    /**
     * 首页楼层
     * @param standardGuest
     * @return
     */
    @Override
    public BigDecimal getSyFloorRentingRateCount(StandardGuestSy standardGuest) {
        return getCont(standardGuest);
        //楼层下品牌
     /*   List<RentingRate> list = rentingRateMapper.getSyFloorRentingRate(standardGuest);
        if(list == null) {
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }

        BigDecimal zjlr = new BigDecimal("0");
        BigDecimal zfm = new BigDecimal("0");
        list.forEach(p->{
            BigDecimal rent = new BigDecimal(p.getRent()); //租金
            BigDecimal propertyfee = new BigDecimal(p.getPropertyfee()); //物业费
            BigDecimal fm = rent.add(propertyfee); //分母

            BigDecimal depreciation = new BigDecimal(p.getDepreciation());//装修折旧费
            BigDecimal agencyFee = new BigDecimal(p.getAgencyFee());//代理费\
            BigDecimal laborCost = new BigDecimal(p.getLaborCost());//人工成本
            BigDecimal gdcb = rent.add(propertyfee).add(depreciation).add(agencyFee).add(laborCost); //固定成品
            //净利润=销售收入（动态客销度）*毛利率(标准客销度)-固定成本
            StandardGuest s = new StandardGuest();
            s.setProjectId(standardGuest.getProjectId());
            s.setContractId(p.getContractId());
            s.setFloorId(standardGuest.getFloorId());
            s.setCreateTime(standardGuest.getCreateTime());
            s.setModifyTime(standardGuest.getModifyTime());
            StandardGuest bz = standardGuestMapper.getStandardFloorGuestListByFloor(s);
            if(bz == null) {
                throw exceptionUtil.throwCustomException("RENTING_RATE_011");
            }
            BigDecimal interestval = new BigDecimal(bz.getInterestVal());//毛利率

            //动态
            String xssr = rentingRateMapper.getFloorBrandById(MapperFactoryUtil.mapperObject(s,BrandRate.class));
            BigDecimal salesVolume = new BigDecimal(xssr);//销售收入
            BigDecimal jlr = salesVolume.multiply(interestval).subtract(gdcb);
            zjlr.add(jlr);
            zfm.add(fm);
        });
        BigDecimal yzl = zjlr.divide(zfm);
        if(yzl.intValue() == 0) {
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        return yzl.setScale(2,BigDecimal.ROUND_UP);*/
    }


    /**
     * 首页业态
     * @param standardGuest
     * @return
     */
    @Override
    public BigDecimal getSyFromRentingRateCount(StandardGuestSy standardGuest) {
        return getCont(standardGuest);
        //业态下品牌
     /*   List<RentingRate> list = rentingRateMapper.getSyFormRentingRate(standardGuest);
        if(list == null) {
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }

        BigDecimal zjlr = new BigDecimal("0");
        BigDecimal zfm = new BigDecimal("0");
        list.forEach(p->{
            BigDecimal rent = new BigDecimal(p.getRent()); //租金
            BigDecimal propertyfee = new BigDecimal(p.getPropertyfee()); //物业费
            BigDecimal fm = rent.add(propertyfee); //分母

            BigDecimal depreciation = new BigDecimal(p.getDepreciation());//装修折旧费
            BigDecimal agencyFee = new BigDecimal(p.getAgencyFee());//代理费\
            BigDecimal laborCost = new BigDecimal(p.getLaborCost());//人工成本
            BigDecimal gdcb = rent.add(propertyfee).add(depreciation).add(agencyFee).add(laborCost); //固定成品
            //净利润=销售收入（动态客销度）*毛利率(标准客销度)-固定成本
            StandardGuest s = new StandardGuest();
            s.setFormId(standardGuest.getFormId());
            s.setProjectId(standardGuest.getProjectId());
            s.setContractId(p.getContractId());
            s.setCreateTime(standardGuest.getCreateTime());
            s.setModifyTime(standardGuest.getModifyTime());
            StandardGuest bz = standardGuestMapper.getStandardFromGuestListByFloor(s);
            if(bz == null) {
                throw exceptionUtil.throwCustomException("RENTING_RATE_011");
            }
            BigDecimal interestval = new BigDecimal(bz.getInterestVal());//毛利率

            //动态
            String xssr = rentingRateMapper.getFromBrandById(MapperFactoryUtil.mapperObject(s,BrandRate.class));
            BigDecimal salesVolume = new BigDecimal(xssr);//销售收入
            BigDecimal jlr = salesVolume.multiply(interestval).subtract(gdcb);
            zjlr.add(jlr);
            zfm.add(fm);
        });
        BigDecimal yzl = zjlr.divide(zfm);
        if(yzl.intValue() == 0) {
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        return yzl.setScale(2,BigDecimal.ROUND_UP);*/
    }


    private BigDecimal getCont(StandardGuestSy standardGuest){
        BigDecimal yzl =  new BigDecimal("0");
        //获取所有品牌签约ID
        List<Contract>  contractProject = rentingRateMapper.getProject(standardGuest);
        if(contractProject.size() == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }

        List<String> list = rentingRateMapper.getSyDate(standardGuest);
        if(list.size() == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
         contractProject.forEach( p->{
             standardGuest.setContractId(p.getContractId());
             List<Contract> getContract = rentingRateMapper.getContract(standardGuest);
             getContract.forEach( a->{

                 list.forEach(b ->{
                     BigDecimal zjlr = new BigDecimal("0");
                     BigDecimal zfm = new BigDecimal("0");
                     standardGuest.setCreateUser(b);
                     RentingRate r = rentingRateMapper.getR(standardGuest);
                     if(r == null) {
                         throw exceptionUtil.throwCustomException("RENTING_RATE_011");
                     }
                     standardGuest.setModifyUser(b);
                     List<String> day = rentingRateMapper.getSyDateDay(standardGuest);
                     BigDecimal rent = new BigDecimal(r.getRent()).divide(new BigDecimal("30")).multiply(new BigDecimal(day.size())); //租金
                     BigDecimal propertyfee = new BigDecimal(r.getPropertyfee()).divide(new BigDecimal("30")).multiply(new BigDecimal(day.size()));; //物业费
                     BigDecimal fm = rent.add(propertyfee); //分母

                     BigDecimal depreciation = new BigDecimal(r.getDepreciation()).divide(new BigDecimal("30")).multiply(new BigDecimal(day.size()));;//装修折旧费
                     BigDecimal agencyFee = new BigDecimal(r.getAgencyFee()).divide(new BigDecimal("30")).multiply(new BigDecimal(day.size()));;//代理费\
                     BigDecimal laborCost = new BigDecimal(r.getLaborCost()).divide(new BigDecimal("30")).multiply(new BigDecimal(day.size()));;//人工成本
                     BigDecimal gdcb = rent.add(propertyfee).add(depreciation).add(agencyFee).add(laborCost); //固定成品
                     //净利润=销售收入（动态客销度）*毛利率(标准客销度)-固定成本

                     StandardGuest bz = standardGuestMapper.getStandardBrandGuestListByFloor(standardGuest);
                     if(bz == null) {
                         throw exceptionUtil.throwCustomException("RENTING_RATE_011");
                     }
                     BigDecimal interestval = new BigDecimal(bz.getInterestVal());//毛利率
                     //动态
                     String xssr = rentingRateMapper.getBrandById(MapperFactoryUtil.mapperObject(standardGuest,BrandRate.class));
                     BigDecimal salesVolume = new BigDecimal(xssr);//销售收入
                     BigDecimal jlr = salesVolume.multiply(interestval).subtract(gdcb);
                     zjlr.add(jlr);
                     zfm.add(fm);
                     yzl.add(zjlr.divide(zfm));

                    });
                });
            });
        if(yzl.intValue() == 0) {
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        return yzl.setScale(2,BigDecimal.ROUND_UP);
    }


    /**
     * 首页品牌
     * @param standardGuest
     * @return
     */
    @Override
    public BigDecimal getSyBrandRentingRateCount(StandardGuestSy standardGuest) {
        return getCont(standardGuest);
        //业态下品牌
      /*  RentingRate p = rentingRateMapper.getSyBrandRentingRate(standardGuest);
        if(p == null) {
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }

        BigDecimal zjlr = new BigDecimal("0");
        BigDecimal zfm = new BigDecimal("0");
            BigDecimal rent = new BigDecimal(p.getRent()); //租金
            BigDecimal propertyfee = new BigDecimal(p.getPropertyfee()); //物业费
            BigDecimal fm = rent.add(propertyfee); //分母

            BigDecimal depreciation = new BigDecimal(p.getDepreciation());//装修折旧费
            BigDecimal agencyFee = new BigDecimal(p.getAgencyFee());//代理费\
            BigDecimal laborCost = new BigDecimal(p.getLaborCost());//人工成本
            BigDecimal gdcb = rent.add(propertyfee).add(depreciation).add(agencyFee).add(laborCost); //固定成品
            //净利润=销售收入（动态客销度）*毛利率(标准客销度)-固定成本

            StandardGuest bz = standardGuestMapper.getStandardBrandGuestListByFloor(standardGuest);
            if(bz == null) {
                throw exceptionUtil.throwCustomException("RENTING_RATE_011");
            }
            BigDecimal interestval = new BigDecimal(bz.getInterestVal());//毛利率

            //动态
            String xssr = rentingRateMapper.getBrandById(MapperFactoryUtil.mapperObject(standardGuest,BrandRate.class));
            BigDecimal salesVolume = new BigDecimal(xssr);//销售收入
            BigDecimal jlr = salesVolume.multiply(interestval).subtract(gdcb);
            zjlr.add(jlr);
            zfm.add(fm);
        BigDecimal yzl = zjlr.divide(zfm);
        if(yzl.intValue() == 0) {
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        return yzl.setScale(2,BigDecimal.ROUND_UP);*/
    }


}
