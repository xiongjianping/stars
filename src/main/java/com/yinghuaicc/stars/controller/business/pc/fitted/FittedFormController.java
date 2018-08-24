package com.yinghuaicc.stars.controller.business.pc.fitted;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedForm;
import com.yinghuaicc.stars.service.dynamic.fitted.FittedFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 * 标准三角形 适配值 业态
 */
@RestController
@RequestMapping(value = "/fittedform")
public class FittedFormController {
    
    @Autowired
    FittedFormService fittedFormService;


    /**
     *@Description: 添加 业态级
     */
    @PostMapping(value = "/save")
    public JsonResult saveFittedForm(@RequestBody FittedForm f){
        fittedFormService.saveFittedForm(f);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表适配值 业态级
     */
    @PostMapping(value = "/find/list")
    public JsonResult getFittedFormList(@RequestBody FittedForm f, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                fittedFormService.getFittedFormList(f, pageParam));
    }

    /**
     *@Description: 详情适配值 业态级
     */
    @GetMapping(value = "/find/id/{id}")
    public JsonResult getFittedFormById(@PathVariable("id") String id){
        return JsonResult.success(
                fittedFormService.getFittedFormById(id));
    }

    /**
     *@Description: 删除适配值 业态级
     */
    @GetMapping(value = "/delete/id/{id}")
    public JsonResult deleteFittedForm(@PathVariable("id") String id){
        fittedFormService.deleteFittedForm(id);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 修改适配值业态级
     */
    @PostMapping(value = "/update")
    public JsonResult updateFittedForm(@RequestBody FittedForm f){
        fittedFormService.updateFittedForm(f);
        return JsonResult.success("OK");
    }
}
