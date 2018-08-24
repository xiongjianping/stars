package com.yinghuaicc.stars.controller.business.pc.standardproject;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardBrand;
import com.yinghuaicc.stars.service.dynamic.standardproject.StandardBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
@RestController
@RequestMapping(value = "/standardbrand")
public class StandardBrandController {
  
    @Autowired
    StandardBrandService standardBrandService;

    /**
     *@Description: 添加 品牌级
     */
    @PostMapping(value = "/save")
    public JsonResult saveStandardBrand(@RequestBody StandardBrand standardBrand){
        standardBrandService.saveStandardBrand(standardBrand);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表溢租率 品牌级
     */
    @PostMapping(value = "/find/list")
    public JsonResult getStandardBrandList(@RequestBody StandardBrand standardBrand, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                standardBrandService.getStandardBrandList(standardBrand, pageParam));
    }

    /**
     *@Description: 详情溢租率 品牌级
     */
    @GetMapping(value = "/find/id/{id}")
    public JsonResult getStandardBrandById(@PathVariable("id") String id){
        return JsonResult.success(
                standardBrandService.getStandardBrandById(id));
    }

    /**
     *@Description: 删除溢租率 品牌级
     */
    @GetMapping(value = "/delete/id/{id}")
    public JsonResult deleteStandardBrand(@PathVariable("id") String id){
        standardBrandService.deleteStandardBrand(id);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 修改溢租率品牌级
     */
    @PostMapping(value = "/update")
    public JsonResult updateStandardBrand(@RequestBody StandardBrand standardBrand){
        standardBrandService.updateStandardBrand(standardBrand);
        return JsonResult.success("OK");
    }
}
