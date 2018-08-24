package com.yinghuaicc.stars.controller.business.pc.fitted;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedBrand;
import com.yinghuaicc.stars.service.dynamic.fitted.FittedBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
@RestController
@RequestMapping(value = "/fittedbrand")
public class FittedBrandController {
  
    @Autowired
    FittedBrandService fittedBrandService;

    /**
     *@Description: 添加业种
     */
    @PostMapping(value = "/save")
    public JsonResult saveFittedBrand(@RequestBody FittedBrand fittedBrand){
        fittedBrandService.saveFittedBrand(fittedBrand);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表适配值业种
     */
    @PostMapping(value = "/find/list")
    public JsonResult getFittedBrandList(@RequestBody FittedBrand fittedBrand, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                fittedBrandService.getFittedBrandList(fittedBrand, pageParam));
    }

    /**
     *@Description: 详情适配值业种
     */
    @GetMapping(value = "/find/id/{id}")
    public JsonResult getFittedBrandById(@PathVariable("id") String id){
        return JsonResult.success(
                fittedBrandService.getFittedBrandById(id));
    }

    /**
     *@Description: 删除适配值业种
     */
    @GetMapping(value = "/delete/id/{id}")
    public JsonResult deleteFittedBrand(@PathVariable("id") String id){
        fittedBrandService.deleteFittedBrand(id);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 修改适配值品牌级
     */
    @PostMapping(value = "/update")
    public JsonResult updateFittedBrand(@RequestBody FittedBrand fittedBrand){
        fittedBrandService.updateFittedBrand(fittedBrand);
        return JsonResult.success("OK");
    }
}
