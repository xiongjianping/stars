package com.yinghuaicc.stars.controller.business.pc.dynamic;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.project.ProjectRate;
import com.yinghuaicc.stars.service.dynamic.project.ProjectRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 * 客销度 项目级
 */
@RestController
@RequestMapping(value = "/projectrate")
public class ProjectRateController {

    @Autowired
    ProjectRateService projectRateService;

    /**
     *@Description: 添加客销度 项目级
     */
    @PostMapping(value = "/save/projectrate")
    public JsonResult saveProjectRate(@RequestBody ProjectRate projectRate){
        projectRateService.saveProjectRate(projectRate);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表客销度 项目级
     */
    @PostMapping(value = "/find/projectrate/list")
    public JsonResult getProjectRateList(@RequestBody ProjectRate projectRate, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                projectRateService.getProjectRateList(projectRate, pageParam));
    }

    /**
     *@Description: 详情客销度 项目级
     */
    @GetMapping(value = "/find/projectrate/id/{id}")
    public JsonResult getProjectRateById(@PathVariable("id") String id){
        return JsonResult.success(
                projectRateService.getProjectRateById(id));
    }

    /**
     *@Description: 删除客销度 项目级
     */
    @GetMapping(value = "/delete/projectrate/id/{id}")
    public JsonResult deleteProjectRate(@PathVariable("id") String id){
        projectRateService.deleteProjectRate(id);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 修改溢租率客销度 项目级
     */
    @PostMapping(value = "/update/projectrate")
    public JsonResult updateRentingRate(@RequestBody ProjectRate projectRate){
        projectRateService.updateProjectRate(projectRate);
        return JsonResult.success("OK");
    }

}
