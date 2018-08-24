package com.yinghuaicc.stars.service.dynamic.standardKxd;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.mapper.MapperFactoryUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.contract.ContractMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.brand.BrandRateMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.floor.FloorRateMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.project.ProjectRateMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.rentingRate.RentingRateMapper;
import com.yinghuaicc.stars.repository.mapper.dynamic.standardkxd.StandardGuestMapper;
import com.yinghuaicc.stars.repository.model.dynamic.brand.BrandRate;
import com.yinghuaicc.stars.repository.model.dynamic.standardkxd.StandardGuest;
import com.yinghuaicc.stars.service.dynamic.rentingRate.dto.request.getRentingRateListRequest;
import com.yinghuaicc.stars.service.dynamic.rentingRate.dto.response.RentingRateListResponse;
import com.yinghuaicc.stars.service.dynamic.standardKxd.dto.StandardGuestListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
@Service
public class StandardGuestServiceImpl implements StandardGuestService {

    @Autowired
    ContractMapper contractMapper;


    @Autowired
    private ExceptionUtil exceptionUtil;

    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;

    @Autowired
    StandardGuestMapper standardGuestMapper;

    @Autowired
    RentingRateMapper rentingRateMapper;

    @Autowired
    ProjectRateMapper projectRateMapper;

    @Autowired
    FloorRateMapper floorRateMapper;

    @Autowired
    BrandRateMapper brandRateMapper;
    /**
     * 新增
     * @param standardGuest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveStandardGuest(StandardGuest standardGuest) {
        Integer count = contractMapper.getContractByContractIdAndDate(standardGuest.getContractId(),standardGuest.getEffectTime());
        if(count == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_001");
        }


        BigDecimal rent; //租金
        BigDecimal propertyfee; //物业费
        BigDecimal depreciation;//装修折旧费
        BigDecimal agencyFee;//代理费\
        BigDecimal laborCost;//人工成本
        BigDecimal gdcb = new BigDecimal("0");//固定成本
        //动态固定成本
        RentingRateListResponse rentingRateListResponse = rentingRateMapper.getRentingRateByKxd(MapperFactoryUtil.mapperObject(standardGuest, getRentingRateListRequest.class));
        if(rentingRateListResponse != null){
            //月份总和除以30，最后相加
            rent = new BigDecimal(rentingRateListResponse.getRent()).divide(new BigDecimal("30")).setScale(2,BigDecimal.ROUND_HALF_UP);  //租金
            propertyfee = new BigDecimal(rentingRateListResponse.getPropertyfee()).divide(new BigDecimal("30")).setScale(2,BigDecimal.ROUND_HALF_UP);  //物业费
            depreciation = new BigDecimal(rentingRateListResponse.getDepreciation()).divide(new BigDecimal("30")).setScale(2,BigDecimal.ROUND_HALF_UP); //装修折旧费
            agencyFee = new BigDecimal(rentingRateListResponse.getAgencyFee()).divide(new BigDecimal("30")).setScale(2,BigDecimal.ROUND_HALF_UP); //代理费\
            laborCost = new BigDecimal(rentingRateListResponse.getLaborCost()).divide(new BigDecimal("30")).setScale(2,BigDecimal.ROUND_HALF_UP); //人工成本
            gdcb.add(rent).add(propertyfee).add(depreciation).add(agencyFee).add(laborCost);
        }

        if(gdcb.intValue() < 1){
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }

        BigDecimal mll = new BigDecimal(standardGuest.getInterestVal());//毛利率
        BigDecimal kdj = new BigDecimal(standardGuest.getPriceVal());//客单价

        BigDecimal xsl = gdcb.divide(mll).setScale(2,BigDecimal.ROUND_HALF_UP);//销售量
        BigDecimal kll = xsl.divide(kdj).setScale(2,BigDecimal.ROUND_HALF_UP);//客流量
        String mj = standardGuestMapper.getStandardGuestAcreageById(standardGuest);
        if(mj == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_010");
        }
        BigDecimal ppmj = new BigDecimal(mj);//品牌面积
        BigDecimal zkxd = xsl.divide(ppmj).multiply(kll.divide(ppmj)).setScale(2,BigDecimal.ROUND_HALF_UP);// 销售量/品牌面积 *（客流量/品牌面积）
        standardGuest.setStandardVal(zkxd.toString());
        standardGuest.setId(UuidUtil.randomUUID());
        standardGuest.setCreateTime(LocalDateTime.now());
        standardGuest.setCreateUser(aopResourceEmployeeBean.getName());
        standardGuestMapper.deletStandardGuestById(standardGuest);
        standardGuestMapper.saveStandardGuest(standardGuest);
    }

    /**
     * 列表
     * @param standardGuest
     * @param pageParam
     * @return
     */
    @Override
    public ResultPageList<StandardGuestListResponse> geStandardGuestList(StandardGuest standardGuest, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<StandardGuestListResponse> result = standardGuestMapper.getStandardGuestList(standardGuest);
        return new ResultPageList<StandardGuestListResponse>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }

    /**
     * 根据ID
     * @param id
     * @return
     */
    @Override
    public StandardGuest getStandardGuestById(String id) {
        return standardGuestMapper.getStandardGuestById(id);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteStandardGuestById(String id) {
        standardGuestMapper.deleteStandardGuestById(id);
    }

    /**
     * 修改
     * @param standardGuest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStandardGuest(StandardGuest standardGuest) {
        Integer count = contractMapper.getContractByContractIdAndDate(standardGuest.getContractId(),standardGuest.getEffectTime());
        if(count == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_001");
        }


        BigDecimal rent; //租金
        BigDecimal propertyfee; //物业费
        BigDecimal depreciation;//装修折旧费
        BigDecimal agencyFee;//代理费\
        BigDecimal laborCost;//人工成本
        BigDecimal gdcb = new BigDecimal("0");//固定成本
        //动态固定成本
        RentingRateListResponse rentingRateListResponse = rentingRateMapper.getRentingRateByKxd(MapperFactoryUtil.mapperObject(standardGuest, getRentingRateListRequest.class));
        if(rentingRateListResponse != null){
            //月份总和除以30，最后相加
            rent = new BigDecimal(rentingRateListResponse.getRent()).divide(new BigDecimal("30")).setScale(2,BigDecimal.ROUND_HALF_UP);  //租金
            propertyfee = new BigDecimal(rentingRateListResponse.getPropertyfee()).divide(new BigDecimal("30")).setScale(2,BigDecimal.ROUND_HALF_UP);  //物业费
            depreciation = new BigDecimal(rentingRateListResponse.getDepreciation()).divide(new BigDecimal("30")).setScale(2,BigDecimal.ROUND_HALF_UP); //装修折旧费
            agencyFee = new BigDecimal(rentingRateListResponse.getAgencyFee()).divide(new BigDecimal("30")).setScale(2,BigDecimal.ROUND_HALF_UP); //代理费\
            laborCost = new BigDecimal(rentingRateListResponse.getLaborCost()).divide(new BigDecimal("30")).setScale(2,BigDecimal.ROUND_HALF_UP); //人工成本
            gdcb.add(rent).add(propertyfee).add(depreciation).add(agencyFee).add(laborCost);
        }

        if(gdcb.intValue() < 1){
            gdcb = new BigDecimal("1");//固定成本
        }

        BigDecimal mll = new BigDecimal(standardGuest.getInterestVal());//毛利率
        BigDecimal kdj = new BigDecimal(standardGuest.getPriceVal());//客单价

        BigDecimal xsl = gdcb.divide(mll).setScale(2,BigDecimal.ROUND_HALF_UP);//销售量
        BigDecimal kll = xsl.divide(kdj).setScale(2,BigDecimal.ROUND_HALF_UP);//客流量
        String mj = standardGuestMapper.getStandardGuestAcreageById(standardGuest);
        if(mj == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_010");
        }
        BigDecimal ppmj = new BigDecimal(mj);//品牌面积
        BigDecimal zkxd = xsl.divide(ppmj).multiply(kll.divide(ppmj)).setScale(2,BigDecimal.ROUND_HALF_UP);// 销售量/品牌面积 *（客流量/品牌面积）
        standardGuest.setStandardVal(zkxd.toString());
        standardGuest.setModifyTime(LocalDateTime.now());
        standardGuest.setModifyUser(aopResourceEmployeeBean.getName());
        standardGuestMapper.deleteStandardGuest(standardGuest);
        standardGuestMapper.updateStandardGuest(standardGuest);

    }

    /**
     * 首页，客销度，项目级
     * @param standardGuest
     * @return
     */
    @Override
    public BigDecimal getSyStandardProjectGuestCount(StandardGuest standardGuest) {
        String mj = projectRateMapper.getProjectacreageById(standardGuest.getProjectId()); //项目面积
        if(mj == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_005");
        }
        List<StandardGuestListResponse> getStandardProjectGuestList = standardGuestMapper.getStandardProjectGuestList(standardGuest);
        if(getStandardProjectGuestList.size() == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        BigDecimal xmmj = new BigDecimal(mj); //项目面积
        BigDecimal zxse = new BigDecimal("0");//销售额
        BigDecimal zkll = new BigDecimal("0"); // 客流量
        Duration duration = Duration.between(standardGuest.getCreateTime(),standardGuest.getModifyTime());
        BigDecimal day = new BigDecimal(duration.toDays()); //时间差
        getStandardProjectGuestList.forEach(p->{
            standardGuest.setContractId(p.getContractId());
            RentingRateListResponse rentingRateListResponse = rentingRateMapper.getRentingRateByKxd(MapperFactoryUtil.mapperObject(standardGuest, getRentingRateListRequest.class));
            if(rentingRateListResponse == null) {
                throw exceptionUtil.throwCustomException("RENTING_RATE_011");
            }
            BigDecimal rent = new BigDecimal(rentingRateListResponse.getRent()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP);  //租金
            BigDecimal propertyfee = new BigDecimal(rentingRateListResponse.getPropertyfee()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP);  //物业费
            BigDecimal depreciation = new BigDecimal(rentingRateListResponse.getDepreciation()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP); //装修折旧费
            BigDecimal agencyFee = new BigDecimal(rentingRateListResponse.getAgencyFee()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP); //代理费\
            BigDecimal laborCost = new BigDecimal(rentingRateListResponse.getLaborCost()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP); //人工成本
            BigDecimal gdcb = new BigDecimal("0").add(rent).add(propertyfee).add(depreciation).add(agencyFee).add(laborCost); //固定成本

            BigDecimal mll = new BigDecimal(p.getInterestVal()); //毛利率
            BigDecimal xse = gdcb.divide(mll);
            BigDecimal kll = xse.divide(new BigDecimal(p.getPriceVal())); // 客流量
            zxse.add(xse);
            zkll.add(kll);
        });


        BigDecimal kxd = zkll.divide(xmmj.multiply(day)).multiply(zxse.divide(xmmj.multiply(day))); //客销度
        return kxd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 首页，客销度，楼层级
     * @param standardGuest
     * @return
     */
    @Override
    public BigDecimal getSyStandardFloorGuestCount(StandardGuest standardGuest) {
        String mj = floorRateMapper.getFloorAcreageById(standardGuest.getFloorId()); //面积
        if(mj == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_007");
        }
        List<StandardGuestListResponse> getStandardProjectGuestList = standardGuestMapper.getStandardProjectGuestList(standardGuest);
        if(getStandardProjectGuestList.size() == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        BigDecimal xmmj = new BigDecimal(mj); //楼层面积
        BigDecimal zxse = new BigDecimal("0");//销售额
        BigDecimal zkll = new BigDecimal("0"); // 客流量
        Duration duration = Duration.between(standardGuest.getCreateTime(),standardGuest.getModifyTime());
        BigDecimal day = new BigDecimal(duration.toDays()); //时间差
        getStandardProjectGuestList.forEach(p->{
            standardGuest.setContractId(p.getContractId());
            RentingRateListResponse rentingRateListResponse = rentingRateMapper.getRentingRateByKxd(MapperFactoryUtil.mapperObject(standardGuest, getRentingRateListRequest.class));
            if(rentingRateListResponse == null) {
                throw exceptionUtil.throwCustomException("RENTING_RATE_011");
            }
            BigDecimal rent = new BigDecimal(rentingRateListResponse.getRent()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP);  //租金
            BigDecimal propertyfee = new BigDecimal(rentingRateListResponse.getPropertyfee()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP);  //物业费
            BigDecimal depreciation = new BigDecimal(rentingRateListResponse.getDepreciation()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP); //装修折旧费
            BigDecimal agencyFee = new BigDecimal(rentingRateListResponse.getAgencyFee()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP); //代理费\
            BigDecimal laborCost = new BigDecimal(rentingRateListResponse.getLaborCost()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP); //人工成本
            BigDecimal gdcb = new BigDecimal("0").add(rent).add(propertyfee).add(depreciation).add(agencyFee).add(laborCost); //固定成本

            BigDecimal mll = new BigDecimal(p.getInterestVal()); //毛利率
            BigDecimal xse = gdcb.divide(mll);
            BigDecimal kll = xse.divide(new BigDecimal(p.getPriceVal())); // 客流量
            zxse.add(xse);
            zkll.add(kll);
        });


        BigDecimal kxd = zkll.divide(xmmj.multiply(day)).multiply(zxse.divide(xmmj.multiply(day))); //客销度
        return kxd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 首页，客销度，业态级
     * @param standardGuest
     * @return
     */
    @Override
    public BigDecimal getSyStandardFormGuestCount(StandardGuest standardGuest) {
        String mj = brandRateMapper.getFormAcreageById(MapperFactoryUtil.mapperObject(standardGuest, BrandRate.class)); //面积
        if(mj == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_009");
        }
        List<StandardGuestListResponse> getStandardProjectGuestList = standardGuestMapper.getStandardProjectGuestList(standardGuest);
        if(getStandardProjectGuestList.size() == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        BigDecimal xmmj = new BigDecimal(mj); //业态面积
        BigDecimal zxse = new BigDecimal("0");//销售额
        BigDecimal zkll = new BigDecimal("0"); // 客流量
        Duration duration = Duration.between(standardGuest.getCreateTime(),standardGuest.getModifyTime());
        BigDecimal day = new BigDecimal(duration.toDays()); //时间差
        getStandardProjectGuestList.forEach(p->{
            standardGuest.setContractId(p.getContractId());
            standardGuest.setBusinessFormId(p.getFormId());
            RentingRateListResponse rentingRateListResponse = rentingRateMapper.getRentingRateByKxd(MapperFactoryUtil.mapperObject(standardGuest, getRentingRateListRequest.class));
            if(rentingRateListResponse == null) {
                throw exceptionUtil.throwCustomException("RENTING_RATE_011");
            }
            BigDecimal rent = new BigDecimal(rentingRateListResponse.getRent()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP);  //租金
            BigDecimal propertyfee = new BigDecimal(rentingRateListResponse.getPropertyfee()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP);  //物业费
            BigDecimal depreciation = new BigDecimal(rentingRateListResponse.getDepreciation()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP); //装修折旧费
            BigDecimal agencyFee = new BigDecimal(rentingRateListResponse.getAgencyFee()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP); //代理费\
            BigDecimal laborCost = new BigDecimal(rentingRateListResponse.getLaborCost()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP); //人工成本
            BigDecimal gdcb = new BigDecimal("0").add(rent).add(propertyfee).add(depreciation).add(agencyFee).add(laborCost); //固定成本

            BigDecimal mll = new BigDecimal(p.getInterestVal()); //毛利率
            BigDecimal xse = gdcb.divide(mll);
            BigDecimal kll = xse.divide(new BigDecimal(p.getPriceVal())); // 客流量
            zxse.add(xse);
            zkll.add(kll);
        });


        BigDecimal kxd = zkll.divide(xmmj.multiply(day)).multiply(zxse.divide(xmmj.multiply(day))); //客销度
        return kxd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 首页，客销度，品牌级
     * @param standardGuest
     * @return
     */
    @Override
    public BigDecimal getSyStandardBrandGuestCount(StandardGuest standardGuest) {
        String mj = brandRateMapper.getBrandAcreageById(MapperFactoryUtil.mapperObject(standardGuest, BrandRate.class)); //面积
        if(mj == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_009");
        }
        List<StandardGuestListResponse> getStandardProjectGuestList = standardGuestMapper.getStandardProjectGuestList(standardGuest);
        if(getStandardProjectGuestList.size() == 0){
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        BigDecimal xmmj = new BigDecimal(mj); //品牌面积
        BigDecimal zxse = new BigDecimal("0");//销售额
        BigDecimal zkll = new BigDecimal("0"); // 客流量
        Duration duration = Duration.between(standardGuest.getCreateTime(),standardGuest.getModifyTime());
        BigDecimal day = new BigDecimal(duration.toDays()); //时间差
        getStandardProjectGuestList.forEach(p->{
            standardGuest.setContractId(p.getContractId());
            standardGuest.setBuildingId(p.getFormId());
            RentingRateListResponse rentingRateListResponse = rentingRateMapper.getRentingRateByKxd(MapperFactoryUtil.mapperObject(standardGuest, getRentingRateListRequest.class));
            if(rentingRateListResponse == null) {
                throw exceptionUtil.throwCustomException("RENTING_RATE_011");
            }
            BigDecimal rent = new BigDecimal(rentingRateListResponse.getRent()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP);  //租金
            BigDecimal propertyfee = new BigDecimal(rentingRateListResponse.getPropertyfee()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP);  //物业费
            BigDecimal depreciation = new BigDecimal(rentingRateListResponse.getDepreciation()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP); //装修折旧费
            BigDecimal agencyFee = new BigDecimal(rentingRateListResponse.getAgencyFee()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP); //代理费\
            BigDecimal laborCost = new BigDecimal(rentingRateListResponse.getLaborCost()).divide(new BigDecimal("30")).multiply(day).setScale(2,BigDecimal.ROUND_HALF_UP); //人工成本
            BigDecimal gdcb = new BigDecimal("0").add(rent).add(propertyfee).add(depreciation).add(agencyFee).add(laborCost); //固定成本

            BigDecimal mll = new BigDecimal(p.getInterestVal()); //毛利率
            BigDecimal xse = gdcb.divide(mll);
            BigDecimal kll = xse.divide(new BigDecimal(p.getPriceVal())); // 客流量
            zxse.add(xse);
            zkll.add(kll);
        });


        BigDecimal kxd = zkll.divide(xmmj.multiply(day)).multiply(zxse.divide(xmmj.multiply(day))); //客销度
        return kxd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }


}
