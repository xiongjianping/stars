package com.yinghuaicc.stars.controller.business.pc.fitted;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedProject;
import com.yinghuaicc.stars.service.dynamic.fitted.FittedProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 * 标准三角形 适配值 项目级
 */
@RestController
@RequestMapping(value = "/fittedproject")
public class FittedProjectController {

    @Autowired
    FittedProjectService fittedProjectService;


    /**
     *@Description: 添加 项目级
     */
    @PostMapping(value = "/save")
    public JsonResult saveFittedProject(@RequestBody FittedProject f){
        fittedProjectService.saveFittedProject(f);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表适配值 项目级
     */
    @PostMapping(value = "/find/list")
    public JsonResult getFittedProjectList(@RequestBody FittedProject f, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                fittedProjectService.getFittedProjectList(f, pageParam));
    }

    /**
     *@Description: 详情适配值 项目级
     */
    @GetMapping(value = "/find/id/{id}")
    public JsonResult getFittedProjectById(@PathVariable("id") String id){
        return JsonResult.success(
                fittedProjectService.getFittedProjectById(id));
    }

    /**
     *@Description: 删除适配值 项目级
     */
    @GetMapping(value = "/delete/id/{id}")
    public JsonResult deleteFittedProject(@PathVariable("id") String id){
        fittedProjectService.deleteFittedProject(id);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 修改适配值项目级
     */
    @PostMapping(value = "/update")
    public JsonResult updatFittedProject(@RequestBody FittedProject f){
        fittedProjectService.updatFittedProject(f);
        return JsonResult.success("OK");
    }
}
