package com.yinghuaicc.stars.controller.business.pc.dynamic;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.dynamic.rentingRate.RentingRate;
import com.yinghuaicc.stars.service.dynamic.rentingRate.RentingRateService;
import com.yinghuaicc.stars.service.dynamic.rentingRate.dto.request.getRentingRateListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 * 溢租率
 */
@RestController
@RequestMapping(value = "/rentingrate")
public class RentingRateController {

    @Autowired
    RentingRateService rentingRateService;

    /**
     *@Description: 添加溢租率
     */
    @PostMapping(value = "/save/rentingrate")
    public JsonResult saveRentingRate(@RequestBody RentingRate rentingRate){
        rentingRateService.saveRentingRate(rentingRate);
        return JsonResult.success("OK");
    }



    /**
     *@Description: 列表
     */
    @PostMapping(value = "/find/rentingrate/list")
    public JsonResult getRentingRateList(@RequestBody getRentingRateListRequest getRentingRateListRequest, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                rentingRateService.getRentingRateList(getRentingRateListRequest, pageParam));
    }


    /**
     *@Description: 详情
     */
    @GetMapping(value = "/find/rentingrate/id/{id}")
    public JsonResult getRentingRateById(@PathVariable("id") String id){
        return JsonResult.success(
                rentingRateService.getRentingRateById(id));
    }


    /**
     *@Description: 删除
     */
    @GetMapping(value = "/delete/rentingrate/id/{id}")
    public JsonResult deleteRentingRateById(@PathVariable("id") String id){
        rentingRateService.deleteRentingRateById(id);
        return JsonResult.success("OK");
    }


    /**
     *@Description: 修改溢租率
     */
    @PostMapping(value = "/update/rentingrate")
    public JsonResult updateRentingRate(@RequestBody RentingRate rentingRate){
        rentingRateService.updateRentingRate(rentingRate);
        return JsonResult.success("OK");
    }

}
