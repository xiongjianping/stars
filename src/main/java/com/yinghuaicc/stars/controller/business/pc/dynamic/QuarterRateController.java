package com.yinghuaicc.stars.controller.business.pc.dynamic;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.quarter.QuarterRate;
import com.yinghuaicc.stars.service.dynamic.quarter.QuarterRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/18.
 * 适配值
 */
@RestController
@RequestMapping(value = "/quarterrate")
public class QuarterRateController {
    
    @Autowired
    QuarterRateService quarterRateService;

    /**
     *@Description: 添加适配值
     */
    @PostMapping(value = "/save/quarterrate")
    public JsonResult saveQuarterRate(@RequestBody QuarterRate quarterRate){
        quarterRateService.saveQuarterRate(quarterRate);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表适配值
     */
    @PostMapping(value = "/find/quarterrate/list")
    public JsonResult getQuarterRateList(@RequestBody QuarterRate quarterRate, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                quarterRateService.getQuarterRateList(quarterRate, pageParam));
    }

    /**
     *@Description: 详情适配值
     */
    @GetMapping(value = "/find/quarterrate/id/{id}")
    public JsonResult getQuarterRateById(@PathVariable("id") String id){
        return JsonResult.success(
                quarterRateService.getQuarterRateById(id));
    }

    /**
     *@Description: 删除适配值
     */
    @GetMapping(value = "/delete/quarterrate/id/{id}")
    public JsonResult deleteQuarterRateById(@PathVariable("id") String id){
        quarterRateService.deleteQuarterRateById(id);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 修改溢租率适配值
     */
    @PostMapping(value = "/update/quarterrate")
    public JsonResult updateBrandRate(@RequestBody QuarterRate quarterRate){
        quarterRateService.updateQuarterRate(quarterRate);
        return JsonResult.success("OK");
    }
}
