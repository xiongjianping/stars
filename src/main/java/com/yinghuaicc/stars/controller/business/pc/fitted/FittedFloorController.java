package com.yinghuaicc.stars.controller.business.pc.fitted;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedFloor;
import com.yinghuaicc.stars.service.dynamic.fitted.FittedFloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
@RestController
@RequestMapping(value = "/fittedfloor")
public class FittedFloorController {

    @Autowired
    FittedFloorService fittedFloorService;

    /**
     *@Description: 添加 楼层级
     */
    @PostMapping(value = "/save")
    public JsonResult saveFittedFloor(@RequestBody FittedFloor fittedFloor){
        fittedFloorService.saveFittedFloor(fittedFloor);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表适配值 楼层级
     */
    @PostMapping(value = "/find/list")
    public JsonResult getFittedFloorList(@RequestBody FittedFloor fittedFloor, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                fittedFloorService.getFittedFloorList(fittedFloor, pageParam));
    }

    /**
     *@Description: 详情适配值 楼层级
     */
    @GetMapping(value = "/find/id/{id}")
    public JsonResult getFittedFloorById(@PathVariable("id") String id){
        return JsonResult.success(
                fittedFloorService.getFittedFloorById(id));
    }

    /**
     *@Description: 删除适配值 楼层级
     */
    @GetMapping(value = "/delete/id/{id}")
    public JsonResult deleteFittedFloor(@PathVariable("id") String id){
        fittedFloorService.deleteFittedFloor(id);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 修改适配值楼层级
     */
    @PostMapping(value = "/update")
    public JsonResult updateFittedFloor(@RequestBody FittedFloor fittedFloor){
        fittedFloorService.updateFittedFloor(fittedFloor);
        return JsonResult.success("OK");
    }
}
