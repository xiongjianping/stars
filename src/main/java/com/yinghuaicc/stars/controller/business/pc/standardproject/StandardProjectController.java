package com.yinghuaicc.stars.controller.business.pc.standardproject;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardProject;
import com.yinghuaicc.stars.service.dynamic.standardproject.StandardProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 * 标准三角形 溢租率 项目级
 */
@RestController
@RequestMapping(value = "/standardproject")
public class StandardProjectController {

    @Autowired
    StandardProjectService standardProjectService;


    /**
     *@Description: 添加 项目级
     */
    @PostMapping(value = "/save")
    public JsonResult saveStandardProject(@RequestBody StandardProject standardProject){
        standardProjectService.saveStandardProject(standardProject);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表溢租率 项目级
     */
    @PostMapping(value = "/find/list")
    public JsonResult getStandardProjectList(@RequestBody StandardProject standardProject, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                standardProjectService.getStandardProjectList(standardProject, pageParam));
    }

    /**
     *@Description: 详情溢租率 项目级
     */
    @GetMapping(value = "/find/id/{id}")
    public JsonResult getStandardProjectById(@PathVariable("id") String id){
        return JsonResult.success(
                standardProjectService.getStandardProjectById(id));
    }

    /**
     *@Description: 删除溢租率 项目级
     */
    @GetMapping(value = "/delete/id/{id}")
    public JsonResult deleteStandardProject(@PathVariable("id") String id){
        standardProjectService.deleteStandardProject(id);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 修改溢租率项目级
     */
    @PostMapping(value = "/update")
    public JsonResult updatStandardProject(@RequestBody StandardProject standardProject){
        standardProjectService.updatStandardProject(standardProject);
        return JsonResult.success("OK");
    }
}
