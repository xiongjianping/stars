package com.yinghuaicc.stars.controller.business.pc.section;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.section.SectionForm;
import com.yinghuaicc.stars.service.section.SectionFormService;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/24.
 */
@RestController
@RequestMapping(value = "/section/form")
public class SectionFormController {
    @Autowired
    SectionFormService sectionFormService;

    /**
     *@Description: 添加业态区间
     */
    @PostMapping(value = "/save")
    public JsonResult saveSectionForm(@RequestBody SectionForm sectionForm){
        sectionFormService.saveSectionForm(sectionForm);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表业态区间
     */
    @PostMapping(value = "/find/list")
    public JsonResult getSectionFormList(@RequestBody SectionBrandRequest sectionBrandRequest, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                sectionFormService.getSectionFormList(sectionBrandRequest, pageParam));
    }

    /**
     *@Description: 详情业态区间
     */
    @GetMapping(value = "/find/id/{id}")
    public JsonResult getSectionFormById(@PathVariable("id") String id){
        return JsonResult.success(
                sectionFormService.getSectionFormById(id));
    }

    /**
     *@Description: 删除业态区间
     */
    @GetMapping(value = "/delete/id/{id}")
    public JsonResult deleteSectionForm(@PathVariable("id") String id){
        sectionFormService.deleteSectionForm(id);
        return JsonResult.success("OK");
    }
}
