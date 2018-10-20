package com.yinghuaicc.stars.controller.business.pc.shouye;

import com.yinghuaicc.stars.common.utils.mapper.MapperFactoryUtil;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.brand.BrandRateSy;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedBrandSy;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedFloorSy;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedFormSy;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedProjectSy;
import com.yinghuaicc.stars.repository.model.dynamic.floor.FloorRateSy;
import com.yinghuaicc.stars.repository.model.dynamic.project.ProjectRateSy;
import com.yinghuaicc.stars.repository.model.dynamic.quarter.QuarterRateSy;
import com.yinghuaicc.stars.repository.model.dynamic.standardkxd.StandardGuestSy;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardBrandSy;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardFloorSy;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardFormSy;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardProjectSy;
import com.yinghuaicc.stars.repository.model.section.SectionBrand;
import com.yinghuaicc.stars.repository.model.section.SectionFloor;
import com.yinghuaicc.stars.repository.model.section.SectionForm;
import com.yinghuaicc.stars.repository.model.section.SectionProject;
import com.yinghuaicc.stars.service.cqrs.help.HelpCQRSService;
import com.yinghuaicc.stars.service.cqrs.help.dto.request.FindHelpRequestDTO;
import com.yinghuaicc.stars.service.dynamic.brand.BrandRateService;
import com.yinghuaicc.stars.service.dynamic.brand.dto.response.SyResponse;
import com.yinghuaicc.stars.service.dynamic.fitted.FittedBrandService;
import com.yinghuaicc.stars.service.dynamic.fitted.FittedFloorService;
import com.yinghuaicc.stars.service.dynamic.fitted.FittedFormService;
import com.yinghuaicc.stars.service.dynamic.fitted.FittedProjectService;
import com.yinghuaicc.stars.service.dynamic.floor.FloorRateService;
import com.yinghuaicc.stars.service.dynamic.project.ProjectRateService;
import com.yinghuaicc.stars.service.dynamic.quarter.QuarterRateService;
import com.yinghuaicc.stars.service.dynamic.rentingRate.RentingRateService;
import com.yinghuaicc.stars.service.dynamic.standardKxd.StandardGuestService;
import com.yinghuaicc.stars.service.dynamic.standardproject.StandardBrandService;
import com.yinghuaicc.stars.service.dynamic.standardproject.StandardFloorService;
import com.yinghuaicc.stars.service.dynamic.standardproject.StandardFormService;
import com.yinghuaicc.stars.service.dynamic.standardproject.StandardProjectService;
import com.yinghuaicc.stars.service.section.SectionBrandService;
import com.yinghuaicc.stars.service.section.SectionFloorService;
import com.yinghuaicc.stars.service.section.SectionFormService;
import com.yinghuaicc.stars.service.section.SectionProjectService;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandSyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/19.
 * 首页
 */
@RestController
@RequestMapping(value = "/sy")
public class SyController {


    @Autowired
    RentingRateService rentingRateService;

    @Autowired
    ProjectRateService projectRateService; //动态

    @Autowired
    QuarterRateService quarterRateService; //动态

    @Autowired
    FloorRateService floorRateService; //动态

    @Autowired
    BrandRateService brandRateService; //动态

    @Autowired
    StandardProjectService standardProjectService; //标准三角形溢租率

    @Autowired
    StandardFloorService standardFloorService; //标准三角形溢租率

    @Autowired
    StandardFormService standardFormService; //标准三角形溢租率

    @Autowired
    StandardBrandService standardBrandService; //标准三角形溢租率


    @Autowired
    FittedProjectService fittedProjectService; //标准三角形适配值

    @Autowired
    FittedFloorService fittedFloorService;//标准三角形适配值

    @Autowired
    FittedFormService fittedFormService;//标准三角形适配值

    @Autowired
    FittedBrandService fittedBrandService;//标准三角形适配值

    @Autowired
    StandardGuestService standardGuestService; //标准三角形客销度

    @Autowired
    SectionProjectService sectionProjectService; //区间项目级别

    @Autowired
    SectionFloorService sectionFloorService; //区间楼层

    @Autowired
    SectionFormService sectionFormService; //区间业态

    @Autowired
    SectionBrandService sectionBrandService; //区间业种

    @Autowired
    HelpCQRSService helpCQRSService;//帮扶计划

    /**
     * 首页三角形项目级别
     * @return
     */
    @PostMapping(value = "/get/project")
    public JsonResult getProject(@Validated @RequestBody BrandRateSy brandRate){

        SyResponse s = new SyResponse();
        BigDecimal projeckxd = projectRateService.getSyProjectCount(MapperFactoryUtil.mapperObject(brandRate,ProjectRateSy.class)); //客销度
        BigDecimal projecspz = quarterRateService.getProjectQuarterRate(MapperFactoryUtil.mapperObject(brandRate,QuarterRateSy.class)); //适配值
        BigDecimal projecyzl = rentingRateService.getSyProjectRentingRateCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class)); //溢租率




        //标准
        BigDecimal projecbzyzl = standardProjectService.getSyProjectCount(MapperFactoryUtil.mapperObject(brandRate,StandardProjectSy.class));//溢租率
        BigDecimal projecbzspz = fittedProjectService.getFittedProject(MapperFactoryUtil.mapperObject(brandRate,FittedProjectSy.class));//适配值
        BigDecimal projecbzkxd = standardGuestService.getSyStandardProjectGuestCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class));//客销度
        s.setStandardRent(projecbzyzl);
        s.setStandardFitted(projecbzspz);
        s.setStandardGuest(projecbzkxd);
        s.setTriangleGuest(projeckxd);
        s.setTriangleFitted(projecspz);
        s.setTriangleRent(projecyzl.multiply(new BigDecimal("100")));

        SectionProject sp = sectionProjectService.getSectionProjectListById(MapperFactoryUtil.mapperObject(brandRate,SectionBrandSyRequest.class));

        BigDecimal reasonablePgeVal = new BigDecimal(sp.getReasonablePgeVal());//合理百分比
        s.setReasonablePgeVal(projecbzkxd.multiply(reasonablePgeVal.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));

        BigDecimal promotePgeVal = new BigDecimal(sp.getPromotePgeVal());//提升百分比
        s.setPromotePgeVal(s.getReasonablePgeVal().multiply(promotePgeVal.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));

        BigDecimal goodPgeVal = new BigDecimal(sp.getGoodPgeVal());//良好百分比
        s.setGoodPgeVal(s.getPromotePgeVal().multiply(goodPgeVal.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));

        BigDecimal expv = new BigDecimal(sp.getExcellentPgeVal()); //优秀百分比
        s.setExcellentPgeVal(s.getGoodPgeVal().multiply(expv.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));



        s.setLossVal(projecbzkxd);



        FindHelpRequestDTO f = new FindHelpRequestDTO();
        f.setProjectId(brandRate.getProjectId());
        f.setType(1);

        if(s.getTriangleRent().floatValue() >= new BigDecimal("0").floatValue() && s.getTriangleRent().floatValue()<=new BigDecimal("99").floatValue()){
            f.setState(4);
            s.setYzlState(4);
        }else if(s.getTriangleRent().floatValue() >=new BigDecimal("100").floatValue() && s.getTriangleRent().floatValue()<=new BigDecimal("199").floatValue()){
            f.setState(3);
            s.setYzlState(3);
        }else if(s.getTriangleRent().floatValue() >=new BigDecimal("200").floatValue() && s.getTriangleRent().floatValue()<=new BigDecimal("299").floatValue()){
            f.setState(2);
            s.setYzlState(2);
        }else if(s.getTriangleRent().floatValue()>=new BigDecimal("300").floatValue()){
            f.setState(1);
            s.setYzlState(1);
        }else{
            s.setYzlState(5);
            f.setState(5);
        }
        s.setYzl(helpCQRSService.getProjectList(f).size() == 0 ? null : helpCQRSService.getProjectList(f));


        f.setType(2);
       if(projeckxd.floatValue() >=s.getReasonablePgeVal().floatValue() && projeckxd.floatValue() < s.getPromotePgeVal().floatValue()){
            f.setState(4);
            s.setKxdState(4);
        }else if(projeckxd.floatValue()  >=s.getPromotePgeVal().floatValue() && projeckxd.floatValue() < s.getGoodPgeVal().floatValue()){
            f.setState(3);
            s.setKxdState(3);
        }else if(projeckxd.floatValue()  >=s.getGoodPgeVal().floatValue() && projeckxd.floatValue() <s.getExcellentPgeVal().floatValue()){
            f.setState(2);
            s.setKxdState(2);
        }else if(projeckxd.floatValue() >=s.getExcellentPgeVal().floatValue()){
            f.setState(1);
            s.setKxdState(1);
        }else{
            f.setState(5);
            s.setKxdState(5);
        }
        s.setKxd(helpCQRSService.getProjectList(f).size() == 0 ? null : helpCQRSService.getProjectList(f));

        f.setType(3);
        if(projecspz.intValue() >=6000 && projecspz.intValue()<=6999){
            f.setState(4);
            s.setSpzState(4);
        }else if(projecspz.intValue() >=7000 && projecspz.intValue()<=7999){
            f.setState(3);
            s.setSpzState(3);
        }else if(projecspz.intValue() >=8000 && projecspz.intValue()<=8999){
            f.setState(2);
            s.setSpzState(2);
        }else if(projecspz.intValue()>=9000){
            f.setState(1);
            s.setSpzState(1);
        }else{
            s.setSpzState(5);
            f.setState(5);
        }
        s.setSpz(helpCQRSService.getProjectList(f).size() == 0 ? null : helpCQRSService.getProjectList(f));

        return JsonResult.success(s);
    }


    /**
     * 首页三角形楼层级别
     * @return
     */
    @PostMapping(value = "/get/floor")
    public JsonResult getFloor(@Validated @RequestBody BrandRateSy brandRate){

        SyResponse s = new SyResponse();
        BigDecimal floorkxd = floorRateService.getSyFloorRateCount(MapperFactoryUtil.mapperObject(brandRate,FloorRateSy.class)); //客销度
        BigDecimal floorspz = quarterRateService.getFloorQuarterRate(MapperFactoryUtil.mapperObject(brandRate,QuarterRateSy.class)); //适配值
        BigDecimal flooryzl = rentingRateService.getSyFloorRentingRateCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class)); //溢租率


        //标准
        BigDecimal floorbzyzl = standardFloorService.getSyFloorCount(MapperFactoryUtil.mapperObject(brandRate,StandardFloorSy.class));//溢租率
        BigDecimal floorbzspz = fittedFloorService.getFittedFloor(MapperFactoryUtil.mapperObject(brandRate,FittedFloorSy.class));//适配值
        BigDecimal floorbzkxd = standardGuestService.getSyStandardFloorGuestCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class));//客销度

        s.setStandardRent(floorbzyzl);
        s.setStandardFitted(floorbzspz);
        s.setStandardGuest(floorbzkxd);
        s.setTriangleGuest(floorkxd);
        s.setTriangleFitted(floorspz);
        s.setTriangleRent(flooryzl.multiply(new BigDecimal("100")));


        SectionFloor sp = sectionFloorService.getSectionFloorListById(MapperFactoryUtil.mapperObject(brandRate,SectionBrandSyRequest.class));

        BigDecimal reasonablePgeVal = new BigDecimal(sp.getReasonablePgeVal());//合理百分比
        s.setReasonablePgeVal(floorbzkxd.multiply(reasonablePgeVal.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));

        BigDecimal promotePgeVal = new BigDecimal(sp.getPromotePgeVal());//提升百分比
        s.setPromotePgeVal(s.getReasonablePgeVal().multiply(promotePgeVal.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));

        BigDecimal goodPgeVal = new BigDecimal(sp.getGoodPgeVal());//良好百分比
        s.setGoodPgeVal(s.getPromotePgeVal().multiply(goodPgeVal.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));

        BigDecimal expv = new BigDecimal(sp.getExcellentPgeVal()); //优秀百分比
        s.setExcellentPgeVal(s.getGoodPgeVal().multiply(expv.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));

        s.setLossVal(floorbzkxd);

        FindHelpRequestDTO f = new FindHelpRequestDTO();
        f.setProjectId(brandRate.getProjectId());
        f.setFloorId(brandRate.getFloorId());
        f.setType(1);
        if(s.getTriangleRent().floatValue() >= new BigDecimal("0").floatValue() && s.getTriangleRent().floatValue()<=new BigDecimal("99").floatValue()){
            f.setState(4);
            s.setYzlState(4);
        }else if(s.getTriangleRent().floatValue() >=new BigDecimal("100").floatValue() && s.getTriangleRent().floatValue()<=new BigDecimal("199").floatValue()){
            f.setState(3);
            s.setYzlState(3);
        }else if(s.getTriangleRent().floatValue() >=new BigDecimal("200").floatValue() && s.getTriangleRent().floatValue()<=new BigDecimal("299").floatValue()){
            f.setState(2);
            s.setYzlState(2);
        }else if(s.getTriangleRent().floatValue()>=new BigDecimal("300").floatValue()){
            f.setState(1);
            s.setYzlState(1);
        }else{
            f.setState(5);
            s.setYzlState(5);
        }
        s.setYzl(helpCQRSService.getfloorList(f).size() == 0 ? null : helpCQRSService.getfloorList(f));

        f.setType(2);
        if(floorkxd.floatValue() >=s.getReasonablePgeVal().floatValue() && floorkxd.floatValue() <s.getPromotePgeVal().floatValue()){
            f.setState(4);
            s.setKxdState(4);
        }else if(floorkxd.floatValue()  >=s.getPromotePgeVal().floatValue() && floorkxd.floatValue() < s.getGoodPgeVal().floatValue()){
            f.setState(3);
            s.setKxdState(3);
        }else if(floorkxd.floatValue()  >=s.getGoodPgeVal().floatValue() && floorkxd.floatValue() <s.getExcellentPgeVal().floatValue()){
            f.setState(2);
            s.setKxdState(2);
        }else if(floorkxd.floatValue() >=s.getExcellentPgeVal().floatValue()){
            f.setState(1);
            s.setKxdState(1);
        }else{
            f.setState(5);
            s.setKxdState(5);
        }
        s.setKxd(helpCQRSService.getfloorList(f).size() == 0 ? null : helpCQRSService.getfloorList(f));

        f.setType(3);
        if(floorspz.intValue() >=6000 && floorspz.intValue()<=6999){
            f.setState(4);
            s.setSpzState(4);
        }else if(floorspz.intValue() >=7000 && floorspz.intValue()<=7999){
            f.setState(3);
            s.setSpzState(3);
        }else if(floorspz.intValue() >=8000 && floorspz.intValue()<=8999){
            f.setState(2);
            s.setSpzState(2);
        }else if(floorspz.intValue()>=9000){
            f.setState(1);
            s.setSpzState(1);
        }else{
            f.setState(5);
            s.setSpzState(5);
        }
        s.setSpz(helpCQRSService.getfloorList(f).size() == 0 ? null : helpCQRSService.getfloorList(f));

        return JsonResult.success(s);
    }



    /**
     * 首页三角形业态级别
     * @return
     */
    @PostMapping(value = "/get/yetai")
    public JsonResult getYetai(@Validated @RequestBody BrandRateSy brandRate){

        SyResponse s = new SyResponse();
        BigDecimal formkxd = brandRateService.getSyFormRateCount(brandRate); //客销度
        BigDecimal formspz = quarterRateService.getFormQuarterRate(MapperFactoryUtil.mapperObject(brandRate,QuarterRateSy.class)); //适配值
        BigDecimal formyzl =  rentingRateService.getSyFromRentingRateCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class)); //溢租率

        //标准
        BigDecimal formbzyzl = standardFormService.getSyFormCount(MapperFactoryUtil.mapperObject(brandRate,StandardFormSy.class));//溢租率
        BigDecimal formbzspz = fittedFormService.getFittedForm(MapperFactoryUtil.mapperObject(brandRate,FittedFormSy.class));//适配值
        BigDecimal formbzkxd = standardGuestService.getSyStandardFormGuestCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class));//客销度

        s.setStandardRent(formbzyzl);
        s.setStandardFitted(formbzspz);
        s.setStandardGuest(formbzkxd);
        s.setTriangleGuest(formkxd);
        s.setTriangleFitted(formspz);
        s.setTriangleRent(formyzl.multiply(new BigDecimal("100")));

        SectionForm sp = sectionFormService.getSectionFormListById(MapperFactoryUtil.mapperObject(brandRate,SectionBrandSyRequest.class));

        BigDecimal reasonablePgeVal = new BigDecimal(sp.getReasonablePgeVal());//合理百分比
        s.setReasonablePgeVal(formbzkxd.multiply(reasonablePgeVal.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));

        BigDecimal promotePgeVal = new BigDecimal(sp.getPromotePgeVal());//提升百分比
        s.setPromotePgeVal(s.getReasonablePgeVal().multiply(promotePgeVal.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));

        BigDecimal goodPgeVal = new BigDecimal(sp.getGoodPgeVal());//良好百分比
        s.setGoodPgeVal(s.getPromotePgeVal().multiply(goodPgeVal.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));

        BigDecimal expv = new BigDecimal(sp.getExcellentPgeVal()); //优秀百分比
        s.setExcellentPgeVal(s.getGoodPgeVal().multiply(expv.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));

        s.setLossVal(formbzkxd);

        FindHelpRequestDTO f = new FindHelpRequestDTO();
        f.setProjectId(brandRate.getProjectId());
        f.setFormId(brandRate.getFormId());
        f.setType(1);
        if(s.getTriangleRent().floatValue() >= new BigDecimal("0").floatValue() && s.getTriangleRent().floatValue()<=new BigDecimal("99").floatValue()){
            f.setState(4);
            s.setYzlState(4);
        }else if(s.getTriangleRent().floatValue() >=new BigDecimal("100").floatValue() && s.getTriangleRent().floatValue()<=new BigDecimal("199").floatValue()){
            f.setState(3);
            s.setYzlState(3);
        }else if(s.getTriangleRent().floatValue() >=new BigDecimal("200").floatValue() && s.getTriangleRent().floatValue()<=new BigDecimal("299").floatValue()){
            f.setState(2);
            s.setYzlState(2);
        }else if(s.getTriangleRent().floatValue()>=new BigDecimal("300").floatValue()){
            f.setState(1);
            s.setYzlState(1);
        }else{
            f.setState(5);
            s.setYzlState(5);
        }
        s.setYzl(helpCQRSService.getformList(f).size() == 0 ? null : helpCQRSService.getformList(f));

        f.setType(2);
        if(formkxd.floatValue() >=s.getReasonablePgeVal().floatValue() && formkxd.floatValue() <s.getPromotePgeVal().floatValue()){
            f.setState(4);
            s.setKxdState(4);
        }else if(formkxd.floatValue()  >=s.getPromotePgeVal().floatValue() && formkxd.floatValue() < s.getGoodPgeVal().floatValue()){
            f.setState(3);
            s.setKxdState(3);
        }else if(formkxd.floatValue()  >=s.getGoodPgeVal().floatValue() && formkxd.floatValue() <s.getExcellentPgeVal().floatValue()){
            f.setState(2);
            s.setKxdState(2);
        }else if(formkxd.floatValue() >=s.getExcellentPgeVal().floatValue()){
            f.setState(1);
            s.setKxdState(1);
        }else{
            f.setState(5);
            s.setKxdState(5);
        }
        s.setKxd(helpCQRSService.getformList(f).size() == 0 ? null : helpCQRSService.getformList(f));

        f.setType(3);
        if(formspz.intValue() >=6000 && formspz.intValue()<=6999){
            f.setState(4);
            s.setSpzState(4);
        }else if(formspz.intValue() >=7000 && formspz.intValue()<=7999){
            f.setState(3);
            s.setSpzState(3);
        }else if(formspz.intValue() >=8000 && formspz.intValue()<=8999){
            f.setState(2);
            s.setSpzState(2);
        }else if(formspz.intValue()>=9000){
            f.setState(1);
            s.setSpzState(1);
        }else{
            f.setState(5);
            s.setSpzState(5);
        }
        s.setSpz(helpCQRSService.getformList(f).size() == 0 ? null : helpCQRSService.getformList(f));

        return JsonResult.success(s);
    }




    /**
     * 首页三角形品牌级别
     * @return
     */
    @PostMapping(value = "/get/brand")
    public JsonResult getBrand(@Validated @RequestBody BrandRateSy brandRate){

        SyResponse s = new SyResponse();
        BigDecimal brandkxd = brandRateService.getSyBrandRateCount(brandRate); //客销度
        BigDecimal brandspz = quarterRateService.getBrandQuarterRate(MapperFactoryUtil.mapperObject(brandRate,QuarterRateSy.class)); //适配值
        BigDecimal brandyzl =  rentingRateService.getSyBrandRentingRateCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class)); //溢租率

        //标准
        BigDecimal brandbzyzl = standardBrandService.getSyBrandCount(MapperFactoryUtil.mapperObject(brandRate,StandardBrandSy.class));//溢租率
        BigDecimal brandbzspz = fittedBrandService.getFittedBrand(MapperFactoryUtil.mapperObject(brandRate,FittedBrandSy.class));//适配值
        BigDecimal brandbzkxd = standardGuestService.getSyStandardBrandGuestCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class));//客销度

        s.setStandardRent(brandbzyzl);
        s.setStandardFitted(brandbzspz);
        s.setStandardGuest(brandbzkxd);
        s.setTriangleGuest(brandkxd);
        s.setTriangleFitted(brandspz);
        s.setTriangleRent(brandyzl.multiply(new BigDecimal("100")));


        SectionBrand sp = sectionBrandService.getSectionBrandListById(MapperFactoryUtil.mapperObject(brandRate,SectionBrandSyRequest.class));

        BigDecimal reasonablePgeVal = new BigDecimal(sp.getReasonablePgeVal());//合理百分比
        s.setReasonablePgeVal(brandbzkxd.multiply(reasonablePgeVal.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));

        BigDecimal promotePgeVal = new BigDecimal(sp.getPromotePgeVal());//提升百分比
        s.setPromotePgeVal(s.getReasonablePgeVal().multiply(promotePgeVal.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));

        BigDecimal goodPgeVal = new BigDecimal(sp.getGoodPgeVal());//良好百分比
        s.setGoodPgeVal(s.getPromotePgeVal().multiply(goodPgeVal.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));

        BigDecimal expv = new BigDecimal(sp.getExcellentPgeVal()); //优秀百分比
        s.setExcellentPgeVal(s.getGoodPgeVal().multiply(expv.add(new BigDecimal("1"))).setScale(2,BigDecimal.ROUND_UP));

        s.setLossVal(brandbzkxd);

        FindHelpRequestDTO f = new FindHelpRequestDTO();
        f.setProjectId(brandRate.getProjectId());
        f.setFormId(brandRate.getFormId());
        f.setSpeciesId(brandRate.getSpeciesId());
        f.setType(1);
        if(s.getTriangleRent().floatValue() >= new BigDecimal("0").floatValue() && s.getTriangleRent().floatValue()<=new BigDecimal("99").floatValue()){
            f.setState(4);
            s.setYzlState(4);
        }else if(s.getTriangleRent().floatValue() >=new BigDecimal("100").floatValue() && s.getTriangleRent().floatValue()<=new BigDecimal("199").floatValue()){
            f.setState(3);
            s.setYzlState(3);
        }else if(s.getTriangleRent().floatValue() >=new BigDecimal("200").floatValue() && s.getTriangleRent().floatValue()<=new BigDecimal("299").floatValue()){
            f.setState(2);
            s.setYzlState(2);
        }else if(s.getTriangleRent().floatValue()>=new BigDecimal("300").floatValue()){
            f.setState(1);
            s.setYzlState(1);
        }else{
            f.setState(5);
            s.setYzlState(5);
        }
        s.setYzl(helpCQRSService.getspeciesList(f).size() == 0 ? null : helpCQRSService.getspeciesList(f));

        f.setType(2);
        if(brandkxd.floatValue() >=s.getReasonablePgeVal().floatValue() && brandkxd.floatValue() <s.getPromotePgeVal().floatValue()){
            f.setState(4);
            s.setKxdState(4);
        }else if(brandkxd.floatValue()  >=s.getPromotePgeVal().floatValue() && brandkxd.floatValue() < s.getGoodPgeVal().floatValue()){
            f.setState(3);
            s.setKxdState(3);
        }else if(brandkxd.floatValue()  >=s.getGoodPgeVal().floatValue() && brandkxd.floatValue() <s.getExcellentPgeVal().floatValue()){
            f.setState(2);
            s.setKxdState(2);
        }else if(brandkxd.floatValue() >=s.getExcellentPgeVal().floatValue()){
            f.setState(1);
            s.setKxdState(1);
        }else{
            f.setState(5);
            s.setKxdState(5);
        }
        s.setKxd(helpCQRSService.getspeciesList(f).size() == 0 ? null : helpCQRSService.getspeciesList(f));

        f.setType(3);
        if(brandspz.intValue() >=6000 && brandspz.intValue()<=6999){
            f.setState(4);
            s.setSpzState(4);
        }else if(brandspz.intValue() >=7000 && brandspz.intValue()<=7999){
            f.setState(3);
            s.setSpzState(3);
        }else if(brandspz.intValue() >=8000 && brandspz.intValue()<=8999){
            f.setState(2);
            s.setSpzState(2);
        }else if(brandspz.intValue()>=9000){
            f.setState(1);
            s.setSpzState(1);
        }else{
            f.setState(5);
            s.setSpzState(5);
        }
        s.setSpz(helpCQRSService.getspeciesList(f).size() == 0 ? null : helpCQRSService.getspeciesList(f));


        return JsonResult.success(s);
    }

}
