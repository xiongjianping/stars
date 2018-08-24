package com.yinghuaicc.stars.controller.business.pc.standardproject;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardFloor;
import com.yinghuaicc.stars.service.dynamic.standardproject.StandardFloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
@RestController
@RequestMapping(value = "/standardfloor")
public class StandardFloorController {

    @Autowired
    StandardFloorService standardFloorService;

    /**
     *@Description: 添加 楼层级
     */
    @PostMapping(value = "/save")
    public JsonResult saveStandardFloor(@RequestBody StandardFloor standardFloor){
        standardFloorService.saveStandardFloor(standardFloor);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表溢租率 楼层级
     */
    @PostMapping(value = "/find/list")
    public JsonResult getStandardFloorList(@RequestBody StandardFloor standardFloor, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                standardFloorService.getStandardFloorList(standardFloor, pageParam));
    }

    /**
     *@Description: 详情溢租率 楼层级
     */
    @GetMapping(value = "/find/id/{id}")
    public JsonResult getStandardFloorById(@PathVariable("id") String id){
        return JsonResult.success(
                standardFloorService.getStandardFloorById(id));
    }

    /**
     *@Description: 删除溢租率 楼层级
     */
    @GetMapping(value = "/delete/id/{id}")
    public JsonResult deleteStandardFloor(@PathVariable("id") String id){
        standardFloorService.deleteStandardFloor(id);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 修改溢租率楼层级
     */
    @PostMapping(value = "/update")
    public JsonResult updateStandardFloor(@RequestBody StandardFloor standardFloor){
        standardFloorService.updateStandardFloor(standardFloor);
        return JsonResult.success("OK");
    }
}
