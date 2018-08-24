package com.yinghuaicc.stars.controller.business.pc.section;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.section.SectionProject;
import com.yinghuaicc.stars.service.section.SectionProjectService;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/24.
 */
@RestController
@RequestMapping(value = "/section/project")
public class SectionProjectController {
    @Autowired
    SectionProjectService sectionProjectService;

    /**
     *@Description: 添加项目区间
     */
    @PostMapping(value = "/save")
    public JsonResult saveSectionProject(@RequestBody SectionProject sectionProject){
        sectionProjectService.saveSectionProject(sectionProject);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表项目区间
     */
    @PostMapping(value = "/find/list")
    public JsonResult getSectionProjectList(@RequestBody SectionBrandRequest sectionBrandRequest, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                sectionProjectService.getSectionProjectList(sectionBrandRequest, pageParam));
    }

    /**
     *@Description: 详情项目区间
     */
    @GetMapping(value = "/find/id/{id}")
    public JsonResult getSectionProjectById(@PathVariable("id") String id){
        return JsonResult.success(
                sectionProjectService.getSectionProjectById(id));
    }

    /**
     *@Description: 删除项目区间
     */
    @GetMapping(value = "/delete/id/{id}")
    public JsonResult deleteSectionProject(@PathVariable("id") String id){
        sectionProjectService.deleteSectionProject(id);
        return JsonResult.success("OK");
    }
}
