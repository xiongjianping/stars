package com.yinghuaicc.stars.controller.business.pc.standardproject;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardForm;
import com.yinghuaicc.stars.service.dynamic.standardproject.StandardFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 * 标准三角形 溢租率 业态
 */
@RestController
@RequestMapping(value = "/standardform")
public class StandardFormController {
    
    @Autowired
    StandardFormService standardFormService;


    /**
     *@Description: 添加 业态级
     */
    @PostMapping(value = "/save")
    public JsonResult saveStandardForm(@RequestBody StandardForm standardForm){
        standardFormService.saveStandardForm(standardForm);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表溢租率 业态级
     */
    @PostMapping(value = "/find/list")
    public JsonResult getStandardFormList(@RequestBody StandardForm standardForm, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                standardFormService.getStandardFormList(standardForm, pageParam));
    }

    /**
     *@Description: 详情溢租率 业态级
     */
    @GetMapping(value = "/find/id/{id}")
    public JsonResult getStandardFormById(@PathVariable("id") String id){
        return JsonResult.success(
                standardFormService.getStandardFormById(id));
    }

    /**
     *@Description: 删除溢租率 业态级
     */
    @GetMapping(value = "/delete/id/{id}")
    public JsonResult deleteStandardForm(@PathVariable("id") String id){
        standardFormService.deleteStandardForm(id);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 修改溢租率业态级
     */
    @PostMapping(value = "/update")
    public JsonResult updateStandardForm(@RequestBody StandardForm standardForm){
        standardFormService.updateStandardForm(standardForm);
        return JsonResult.success("OK");
    }
}
