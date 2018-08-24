package com.yinghuaicc.stars.controller.business.pc.shouye;

import com.yinghuaicc.stars.common.utils.mapper.MapperFactoryUtil;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.brand.BrandRate;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedBrand;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedFloor;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedForm;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedProject;
import com.yinghuaicc.stars.repository.model.dynamic.floor.FloorRate;
import com.yinghuaicc.stars.repository.model.dynamic.project.ProjectRate;
import com.yinghuaicc.stars.repository.model.dynamic.quarter.QuarterRate;
import com.yinghuaicc.stars.repository.model.dynamic.standardkxd.StandardGuest;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardBrand;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardFloor;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardForm;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardProject;
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

    StandardGuestService standardGuestService; //标准三角形客销度

    /**
     * 首页三角形项目级别
     * @return
     */
    @PostMapping(value = "/get/project")
    public JsonResult getProject(@Validated @RequestBody BrandRate brandRate){

        SyResponse s = new SyResponse();
        BigDecimal projeckxd = projectRateService.getSyProjectCount(MapperFactoryUtil.mapperObject(brandRate,ProjectRate.class)); //客销度
        BigDecimal projecspz = quarterRateService.getProjectQuarterRate(MapperFactoryUtil.mapperObject(brandRate,QuarterRate.class)); //适配值
        BigDecimal projecyzl = rentingRateService.getSyProjectRentingRateCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuest.class)); //溢租率

        s.setDtyzl(projecyzl);
        s.setDtkxd(projeckxd);
        s.setDtspz(projecspz);


        //标准
        BigDecimal projecbzyzl = standardProjectService.getSyProjectCount(MapperFactoryUtil.mapperObject(brandRate,StandardProject.class));//溢租率
        BigDecimal projecbzspz = fittedProjectService.getFittedProject(MapperFactoryUtil.mapperObject(brandRate,FittedProject.class));//适配值
        BigDecimal projecbzkxd = standardGuestService.getSyStandardProjectGuestCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuest.class));//客销度
        s.setBzyzl(projecbzyzl);
        s.setBzspz(projecbzspz);
        s.setBzkxd(projecbzkxd);
        return JsonResult.success(s);
    }


    /**
     * 首页三角形楼层级别
     * @return
     */
    @PostMapping(value = "/get/floor")
    public JsonResult getFloor(@Validated @RequestBody BrandRate brandRate){

        SyResponse s = new SyResponse();
        BigDecimal floorkxd = floorRateService.getSyFloorRateCount(MapperFactoryUtil.mapperObject(brandRate,FloorRate.class)); //客销度
        BigDecimal floorspz = quarterRateService.getFloorQuarterRate(MapperFactoryUtil.mapperObject(brandRate,QuarterRate.class)); //适配值
        BigDecimal flooryzl = rentingRateService.getSyFloorRentingRateCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuest.class)); //溢租率
        s.setDtkxd(floorkxd);
        s.setDtspz(floorspz);
        s.setDtyzl(flooryzl);

        //标准
        BigDecimal floorbzyzl = standardFloorService.getSyFloorCount(MapperFactoryUtil.mapperObject(brandRate,StandardFloor.class));//溢租率
        BigDecimal floorbzspz = fittedFloorService.getFittedFloor(MapperFactoryUtil.mapperObject(brandRate,FittedFloor.class));//适配值
        BigDecimal floorbzkxd = standardGuestService.getSyStandardFloorGuestCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuest.class));//客销度

        s.setBzyzl(floorbzyzl);
        s.setBzspz(floorbzspz);
        s.setBzkxd(floorbzkxd);
        return JsonResult.success(s);
    }



    /**
     * 首页三角形业态级别
     * @return
     */
    @PostMapping(value = "/get/yetai")
    public JsonResult getYetai(@Validated @RequestBody BrandRate brandRate){

        SyResponse s = new SyResponse();
        BigDecimal formkxd = brandRateService.getSyFormRateCount(brandRate); //客销度
        BigDecimal formspz = quarterRateService.getFormQuarterRate(MapperFactoryUtil.mapperObject(brandRate,QuarterRate.class)); //适配值
        BigDecimal formyzl =  rentingRateService.getSyFromRentingRateCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuest.class)); //溢租率
        s.setDtkxd(formkxd);
        s.setDtspz(formspz);
        s.setDtyzl(formyzl);

        //标准
        BigDecimal formbzyzl = standardFormService.getSyFormCount(MapperFactoryUtil.mapperObject(brandRate,StandardForm.class));//溢租率
        BigDecimal formbzspz = fittedFormService.getFittedForm(MapperFactoryUtil.mapperObject(brandRate,FittedForm.class));//适配值
        BigDecimal formbzkxd = standardGuestService.getSyStandardFormGuestCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuest.class));//客销度

        s.setBzkxd(formbzkxd);
        s.setBzyzl(formbzyzl);
        s.setBzspz(formbzspz);
        return JsonResult.success(s);
    }




    /**
     * 首页三角形品牌级别
     * @return
     */
    @PostMapping(value = "/get/brand")
    public JsonResult getBrand(@Validated @RequestBody BrandRate brandRate){

        SyResponse s = new SyResponse();
        BigDecimal brandkxd = brandRateService.getSyBrandRateCount(brandRate); //客销度
        BigDecimal brandspz = quarterRateService.getBrandQuarterRate(MapperFactoryUtil.mapperObject(brandRate,QuarterRate.class)); //适配值
        BigDecimal brandyzl =  rentingRateService.getSyBrandRentingRateCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuest.class)); //溢租率
        s.setDtkxd(brandkxd);
        s.setDtspz(brandspz);
        s.setDtyzl(brandyzl);

        //标准
        BigDecimal brandbzyzl = standardBrandService.getSyBrandCount(MapperFactoryUtil.mapperObject(brandRate,StandardBrand.class));//溢租率
        BigDecimal brandbzspz = fittedBrandService.getFittedBrand(MapperFactoryUtil.mapperObject(brandRate,FittedBrand.class));//适配值
        BigDecimal brandbzkxd = standardGuestService.getSyStandardBrandGuestCount(MapperFactoryUtil.mapperObject(brandRate,StandardGuest.class));//客销度

        s.setBzyzl(brandbzyzl);
        s.setBzspz(brandbzspz);
        s.setBzkxd(brandbzkxd);
        return JsonResult.success(s);
    }

}
