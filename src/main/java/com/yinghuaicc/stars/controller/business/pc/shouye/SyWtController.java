package com.yinghuaicc.stars.controller.business.pc.shouye;

import com.yinghuaicc.stars.common.utils.mapper.MapperFactoryUtil;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.brand.BrandRateSy;
import com.yinghuaicc.stars.repository.model.dynamic.brand.Tj;
import com.yinghuaicc.stars.repository.model.dynamic.brand.Wt;
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
import com.yinghuaicc.stars.service.cqrs.help.HelpCQRSService;
import com.yinghuaicc.stars.service.dynamic.brand.BrandRateService;
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

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/19.
 * 首页
 */
@RestController
@RequestMapping(value = "/sy/wt")
public class SyWtController {


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
        Wt w = new Wt();
        //动态
        List<String> projeckxd = projectRateService.getSyWtProjectCount(MapperFactoryUtil.mapperObject(brandRate,ProjectRateSy.class)); //客销度
        List<String> projecspz = quarterRateService.getWtProjectQuarterRate(MapperFactoryUtil.mapperObject(brandRate,QuarterRateSy.class)); //适配值
        List<String> projecyzl = rentingRateService.getSyWtProjectRentingRateCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class)); //溢租率

        //标准
        List<String> projecbzyzl = standardProjectService.getSyWtProjectCount(MapperFactoryUtil.mapperObject(brandRate,StandardProjectSy.class));//溢租率
        List<String> projecbzspz = fittedProjectService.getWtFittedProject(MapperFactoryUtil.mapperObject(brandRate,FittedProjectSy.class));//适配值
        List<String> projecbzkxd = standardGuestService.getSyWtStandardProjectGuestCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class));//客销度

        String sp = sectionProjectService.getWtSectionProjectListById(MapperFactoryUtil.mapperObject(brandRate,SectionBrandSyRequest.class));

        w.setProjeckxd(projeckxd);
        w.setProjecspz(projecspz);
        w.setProjecyzl(projecyzl);

        //标准
        w.setProjecbzyzl(projecbzyzl);
        w.setProjecbzspz(projecbzspz);
        w.setProjecbzkxd(projecbzkxd);
        w.setSp(sp);
        return JsonResult.success(w);
    }


    /**
     * 首页三角形楼层级别
     * @return
     */
    @PostMapping(value = "/get/floor")
    public JsonResult getFloor(@Validated @RequestBody BrandRateSy brandRate){
        Wt w = new Wt();
        List<String> floorkxd = floorRateService.getSyWtFloorRateCount(MapperFactoryUtil.mapperObject(brandRate,FloorRateSy.class)); //客销度
        List<String> floorspz = quarterRateService.getWtFloorQuarterRate(MapperFactoryUtil.mapperObject(brandRate,QuarterRateSy.class)); //适配值
        List<String> flooryzl = rentingRateService.getSyWtFloorRentingRateCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class)); //溢租率


        //标准
        List<String> floorbzyzl = standardFloorService.getSyWtFloorCount(MapperFactoryUtil.mapperObject(brandRate,StandardFloorSy.class));//溢租率
        List<String> floorbzspz = fittedFloorService.getWtFittedFloor(MapperFactoryUtil.mapperObject(brandRate,FittedFloorSy.class));//适配值
        List<String> floorbzkxd = standardGuestService.getSyWtStandardFloorGuestCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class));//客销度

        String sp = sectionFloorService.getWtSectionFloorListById(MapperFactoryUtil.mapperObject(brandRate,SectionBrandSyRequest.class));

        w.setProjeckxd(floorkxd);
        w.setProjecspz(floorspz);
        w.setProjecyzl(flooryzl);

        //标准
        w.setProjecbzyzl(floorbzyzl);
        w.setProjecbzspz(floorbzspz);
        w.setProjecbzkxd(floorbzkxd);
        w.setSp(sp);
        return JsonResult.success(w);
    }



    /**
     * 首页三角形业态级别
     * @return
     */
    @PostMapping(value = "/get/yetai")
    public JsonResult getYetai(@Validated @RequestBody BrandRateSy brandRate){
        Wt w = new Wt();
        List<String> formkxd = brandRateService.getSyWtFormRateCount(brandRate); //客销度
        List<String> formspz = quarterRateService.getWtFormQuarterRate(MapperFactoryUtil.mapperObject(brandRate,QuarterRateSy.class)); //适配值
        List<String> formyzl =  rentingRateService.getSyWtFromRentingRateCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class)); //溢租率

        //标准
        List<String> formbzyzl = standardFormService.getSyWtFormCount(MapperFactoryUtil.mapperObject(brandRate,StandardFormSy.class));//溢租率
        List<String> formbzspz = fittedFormService.getWtFittedForm(MapperFactoryUtil.mapperObject(brandRate,FittedFormSy.class));//适配值
        List<String> formbzkxd = standardGuestService.getSyWtStandardFormGuestCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class));//客销度

        String sp = sectionFormService.getWtSectionFormListById(MapperFactoryUtil.mapperObject(brandRate,SectionBrandSyRequest.class));

        w.setProjeckxd(formkxd);
        w.setProjecspz(formspz);
        w.setProjecyzl(formyzl);

        //标准
        w.setProjecbzyzl(formbzyzl);
        w.setProjecbzspz(formbzspz);
        w.setProjecbzkxd(formbzkxd);
        w.setSp(sp);
        return JsonResult.success(w);
    }




    /**
     * 首页三角形品牌级别
     * @return
     */
    @PostMapping(value = "/get/brand")
    public JsonResult getBrand(@Validated @RequestBody BrandRateSy brandRate){
        Wt w = new Wt();
        List<String> brandkxd = brandRateService.getSyWtBrandRateCount(brandRate); //客销度
        List<String> brandspz = quarterRateService.getWtBrandQuarterRate(MapperFactoryUtil.mapperObject(brandRate,QuarterRateSy.class)); //适配值
        List<String> brandyzl =  rentingRateService.getSyWtBrandRentingRateCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class)); //溢租率

        //标准
        List<String> brandbzyzl = standardBrandService.getSyWtBrandCount(MapperFactoryUtil.mapperObject(brandRate,StandardBrandSy.class));//溢租率
        List<String> brandbzspz = fittedBrandService.getWtFittedBrand(MapperFactoryUtil.mapperObject(brandRate,FittedBrandSy.class));//适配值
        List<String> brandbzkxd = standardGuestService.getSyWtStandardBrandGuestCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuestSy.class));//客销度

        String sp = sectionBrandService.getWtSectionBrandListById(MapperFactoryUtil.mapperObject(brandRate,SectionBrandSyRequest.class));


        w.setProjeckxd(brandkxd);
        w.setProjecspz(brandspz);
        w.setProjecyzl(brandyzl);

        //标准
        w.setProjecbzyzl(brandbzyzl);
        w.setProjecbzspz(brandbzspz);
        w.setProjecbzkxd(brandbzkxd);
        w.setSp(sp);
        return JsonResult.success(w);
    }


    /**
     * 数据统计
     */
    @PostMapping(value = "/get/list")
    public JsonResult getSjtjList(@Validated @RequestBody Tj t){

        return JsonResult.success(sectionBrandService.getTjList(t));
    }


    /**
     * 数据统计
     */
    @PostMapping(value = "/get/detail")
    public JsonResult getSjtjDetail(@Validated @RequestBody Tj t){

        return JsonResult.success(sectionBrandService.getTjDetail(t));
    }
}
