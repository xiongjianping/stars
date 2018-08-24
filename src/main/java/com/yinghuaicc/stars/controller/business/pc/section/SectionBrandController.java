package com.yinghuaicc.stars.controller.business.pc.section;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.section.SectionBrand;
import com.yinghuaicc.stars.service.section.SectionBrandService;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/24.
 */@RestController
@RequestMapping(value = "/section/brand")
public class SectionBrandController {

    @Autowired
    SectionBrandService sectionBrandService;

    /**
     *@Description: 添加业种区间
     */
    @PostMapping(value = "/save")
    public JsonResult saveSectionBrand(@RequestBody SectionBrand sectionBrand){
        sectionBrandService.saveSectionBrand(sectionBrand);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表业种区间
     */
    @PostMapping(value = "/find/list")
    public JsonResult getSectionBrandList(@RequestBody SectionBrandRequest sectionBrandRequest, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                sectionBrandService.getSectionBrandList(sectionBrandRequest, pageParam));
    }

    /**
     *@Description: 详情业种区间
     */
    @GetMapping(value = "/find/id/{id}")
    public JsonResult getSectionBrandById(@PathVariable("id") String id){
        return JsonResult.success(
                sectionBrandService.getSectionBrandById(id));
    }

    /**
     *@Description: 删除业种区间
     */
    @GetMapping(value = "/delete/id/{id}")
    public JsonResult deleteSectionBrand(@PathVariable("id") String id){
        sectionBrandService.deleteSectionBrand(id);
        return JsonResult.success("OK");
    }


}
