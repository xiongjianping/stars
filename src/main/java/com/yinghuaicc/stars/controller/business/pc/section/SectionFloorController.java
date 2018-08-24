package com.yinghuaicc.stars.controller.business.pc.section;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.section.SectionFloor;
import com.yinghuaicc.stars.service.section.SectionFloorService;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/24.
 */
@RestController
@RequestMapping(value = "/section/floor")
public class SectionFloorController {
    @Autowired
    SectionFloorService sectionFloorService;

    /**
     *@Description: 添加楼层区间
     */
    @PostMapping(value = "/save")
    public JsonResult saveSectionFloor(@RequestBody SectionFloor sectionFloor){
        sectionFloorService.saveSectionFloor(sectionFloor);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表楼层区间
     */
    @PostMapping(value = "/find/list")
    public JsonResult getSectionFloorList(@RequestBody SectionBrandRequest sectionBrandRequest, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                sectionFloorService.getSectionFloorList(sectionBrandRequest, pageParam));
    }

    /**
     *@Description: 详情楼层区间
     */
    @GetMapping(value = "/find/id/{id}")
    public JsonResult getSectionFloorById(@PathVariable("id") String id){
        return JsonResult.success(
                sectionFloorService.getSectionFloorById(id));
    }

    /**
     *@Description: 删除楼层区间
     */
    @GetMapping(value = "/delete/id/{id}")
    public JsonResult deleteSectionFloor(@PathVariable("id") String id){
        sectionFloorService.deleteSectionFloor(id);
        return JsonResult.success("OK");
    }
}
