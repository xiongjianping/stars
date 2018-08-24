package com.yinghuaicc.stars.controller.business.pc.standardKxd;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.standardkxd.StandardGuest;
import com.yinghuaicc.stars.service.dynamic.standardKxd.StandardGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
@RestController
@RequestMapping(value = "/standardguest")
public class StandardGuestController {
  
    @Autowired
    StandardGuestService standardGuestService;

    /**
     *@Description: 添加 品牌级
     */
    @PostMapping(value = "/save")
    public JsonResult saveStandardGuest(@RequestBody StandardGuest standardGuest){
        standardGuestService.saveStandardGuest(standardGuest);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表溢租率 品牌级
     */
    @PostMapping(value = "/find/list")
    public JsonResult geStandardGuestList(@RequestBody StandardGuest standardGuest, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                standardGuestService.geStandardGuestList(standardGuest, pageParam));
    }

    /**
     *@Description: 详情溢租率 品牌级
     */
    @GetMapping(value = "/find/id/{id}")
    public JsonResult getStandardGuestById(@PathVariable("id") String id){
        return JsonResult.success(
                standardGuestService.getStandardGuestById(id));
    }

    /**
     *@Description: 删除溢租率 品牌级
     */
    @GetMapping(value = "/delete/id/{id}")
    public JsonResult deleteStandardGuestById(@PathVariable("id") String id){
        standardGuestService.deleteStandardGuestById(id);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 修改溢租率品牌级
     */
    @PostMapping(value = "/update")
    public JsonResult updateStandardGuest(@RequestBody StandardGuest standardGuest){
        standardGuestService.updateStandardGuest(standardGuest);
        return JsonResult.success("OK");
    }
}
