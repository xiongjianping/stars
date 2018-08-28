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
        s.setTriangleRent(projecyzl);

        SectionProject sp = sectionProjectService.getSectionProjectListById(MapperFactoryUtil.mapperObject(brandRate,SectionBrandSyRequest.class));

        BigDecimal expv = new BigDecimal(sp.getExcellentPgeVal()); //优秀百分比
        s.setExcellentPgeVal(projecbzkxd.multiply(expv.add(new BigDecimal("1"))));

        BigDecimal goodPgeVal = new BigDecimal(sp.getGoodPgeVal());//良好百分比
        s.setGoodPgeVal(projecbzkxd.multiply(goodPgeVal.add(new BigDecimal("1"))));

        BigDecimal promotePgeVal = new BigDecimal(sp.getPromotePgeVal());//提升百分比
        s.setPromotePgeVal(projecbzkxd.multiply(promotePgeVal.add(new BigDecimal("1"))));

        BigDecimal reasonablePgeVal = new BigDecimal(sp.getReasonablePgeVal());//合理百分比
        s.setReasonablePgeVal(projecbzkxd.multiply(reasonablePgeVal.add(new BigDecimal("1"))));
        s.setLossVal(projecbzkxd);

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

        s.setStandardRent(flooryzl);
        s.setStandardFitted(floorspz);
        s.setStandardGuest(floorkxd);
        s.setTriangleGuest(floorbzkxd);
        s.setTriangleFitted(floorbzspz);
        s.setTriangleRent(floorbzyzl);


        SectionFloor sp = sectionFloorService.getSectionFloorListById(MapperFactoryUtil.mapperObject(brandRate,SectionBrandSyRequest.class));

        BigDecimal expv = new BigDecimal(sp.getExcellentPgeVal()); //优秀百分比
        s.setExcellentPgeVal(floorbzkxd.multiply(expv.add(new BigDecimal("1"))));

        BigDecimal goodPgeVal = new BigDecimal(sp.getGoodPgeVal());//良好百分比
        s.setGoodPgeVal(floorbzkxd.multiply(goodPgeVal.add(new BigDecimal("1"))));

        BigDecimal promotePgeVal = new BigDecimal(sp.getPromotePgeVal());//提升百分比
        s.setPromotePgeVal(floorbzkxd.multiply(promotePgeVal.add(new BigDecimal("1"))));

        BigDecimal reasonablePgeVal = new BigDecimal(sp.getReasonablePgeVal());//合理百分比
        s.setReasonablePgeVal(floorbzkxd.multiply(reasonablePgeVal.add(new BigDecimal("1"))));

        s.setLossVal(floorbzkxd);
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

        s.setStandardRent(formyzl);
        s.setStandardFitted(formspz);
        s.setStandardGuest(formkxd);
        s.setTriangleGuest(formbzkxd);
        s.setTriangleFitted(formbzspz);
        s.setTriangleRent(formbzyzl);

        SectionForm sp = sectionFormService.getSectionFormListById(MapperFactoryUtil.mapperObject(brandRate,SectionBrandSyRequest.class));

        BigDecimal expv = new BigDecimal(sp.getExcellentPgeVal()); //优秀百分比
        s.setExcellentPgeVal(formbzkxd.multiply(expv.add(new BigDecimal("1"))));

        BigDecimal goodPgeVal = new BigDecimal(sp.getGoodPgeVal());//良好百分比
        s.setGoodPgeVal(formbzkxd.multiply(goodPgeVal.add(new BigDecimal("1"))));

        BigDecimal promotePgeVal = new BigDecimal(sp.getPromotePgeVal());//提升百分比
        s.setPromotePgeVal(formbzkxd.multiply(promotePgeVal.add(new BigDecimal("1"))));

        BigDecimal reasonablePgeVal = new BigDecimal(sp.getReasonablePgeVal());//合理百分比
        s.setReasonablePgeVal(formbzkxd.multiply(reasonablePgeVal.add(new BigDecimal("1"))));

        s.setLossVal(formbzkxd);

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

        s.setStandardRent(brandyzl);
        s.setStandardFitted(brandspz);
        s.setStandardGuest(brandkxd);
        s.setTriangleGuest(brandbzkxd);
        s.setTriangleFitted(brandbzspz);
        s.setTriangleRent(brandbzyzl);


        SectionBrand sp = sectionBrandService.getSectionBrandListById(MapperFactoryUtil.mapperObject(brandRate,SectionBrandSyRequest.class));

        BigDecimal expv = new BigDecimal(sp.getExcellentPgeVal()); //优秀百分比
        s.setExcellentPgeVal(brandbzkxd.multiply(expv.add(new BigDecimal("1"))));

        BigDecimal goodPgeVal = new BigDecimal(sp.getGoodPgeVal());//良好百分比
        s.setGoodPgeVal(brandbzkxd.multiply(goodPgeVal.add(new BigDecimal("1"))));

        BigDecimal promotePgeVal = new BigDecimal(sp.getPromotePgeVal());//提升百分比
        s.setPromotePgeVal(brandbzkxd.multiply(promotePgeVal.add(new BigDecimal("1"))));

        BigDecimal reasonablePgeVal = new BigDecimal(sp.getReasonablePgeVal());//合理百分比
        s.setReasonablePgeVal(brandbzkxd.multiply(reasonablePgeVal.add(new BigDecimal("1"))));

        s.setLossVal(brandbzkxd);


        return JsonResult.success(s);
    }

}
