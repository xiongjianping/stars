package com.yinghuaicc.stars.controller.business.pc.dynamic;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.floor.FloorRate;
import com.yinghuaicc.stars.service.dynamic.floor.FloorRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 * 客销度 楼层级
 */
@RestController
@RequestMapping(value = "/floorrate")
public class FloorRateController {

    @Autowired
    FloorRateService floorRateService;

    /**
     *@Description: 添加客销度 楼层级
     */
    @PostMapping(value = "/save/floorrate")
    public JsonResult saveFloorRate(@RequestBody FloorRate floorRate){
        floorRateService.saveFloorRate(floorRate);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表客销度 楼层级
     */
    @PostMapping(value = "/find/floorrate/list")
    public JsonResult getFloorRateList(@RequestBody FloorRate floorRate, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                floorRateService.getFloorRateList(floorRate, pageParam));
    }

    /**
     *@Description: 详情客销度 楼层级
     */
    @GetMapping(value = "/find/floorrate/id/{id}")
    public JsonResult getFloorRateById(@PathVariable("id") String id){
        return JsonResult.success(
                floorRateService.getFloorRateById(id));
    }

    /**
     *@Description: 删除客销度 楼层级
     */
    @GetMapping(value = "/delete/floorrate/id/{id}")
    public JsonResult deleteFloorRate(@PathVariable("id") String id){
        floorRateService.deleteFloorRate(id);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 修改溢租率客销度 楼层级
     */
    @PostMapping(value = "/update/floorrate")
    public JsonResult updateRentingRate(@RequestBody FloorRate floorRate){
        floorRateService.updateFloorRate(floorRate);
        return JsonResult.success("OK");
    }

}
