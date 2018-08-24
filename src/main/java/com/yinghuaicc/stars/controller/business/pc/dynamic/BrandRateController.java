package com.yinghuaicc.stars.controller.business.pc.dynamic;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.brand.BrandRate;
import com.yinghuaicc.stars.service.dynamic.brand.BrandRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 * 客销度 品牌
 */
@RestController
@RequestMapping(value = "/brandrate")
public class BrandRateController {

    @Autowired
    BrandRateService brandRateService;

    /**
     *@Description: 添加客销度 品牌
     */
    @PostMapping(value = "/save/brandrate")
    public JsonResult saveBrandRate(@RequestBody BrandRate brandRate){
        brandRateService.saveBrandRate(brandRate);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表客销度 品牌
     */
    @PostMapping(value = "/find/brandrate/list")
    public JsonResult getBrandRateList(@RequestBody BrandRate brandRate, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                brandRateService.getBrandRateList(brandRate, pageParam));
    }

    /**
     *@Description: 详情客销度 品牌
     */
    @GetMapping(value = "/find/brandrate/id/{id}")
    public JsonResult getBrandRateById(@PathVariable("id") String id){
        return JsonResult.success(
                brandRateService.getBrandRateById(id));
    }

    /**
     *@Description: 删除客销度 品牌
     */
    @GetMapping(value = "/delete/brandrate/id/{id}")
    public JsonResult deleteBrandRate(@PathVariable("id") String id){
        brandRateService.deleteBrandRate(id);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 修改溢租率客销度 品牌
     */
    @PostMapping(value = "/update/brandrate")
    public JsonResult updateBrandRate(@RequestBody BrandRate brandRate){
        brandRateService.updateBrandRate(brandRate);
        return JsonResult.success("OK");
    }

}
