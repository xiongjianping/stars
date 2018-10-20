package com.yinghuaicc.stars.controller.business.pc.triangle;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.service.cqrs.triangle.DayGuestService;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.DayGuestRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * 每日客销度controller
 */
@RestController
@RequestMapping(value = "/dayguest")
public class DayGuestController {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private DayGuestService dayGuestService;

    /**
     * 动态三角形客销度
     * @param dayGuestResponseDTO
     * @param pageParam
     * @return
     */
    @PostMapping(value = "/find/dayguest/list")
    public JsonResult findMoonRantList(@RequestBody DayGuestRequestDTO dayGuestResponseDTO, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                dayGuestService.findDayGuestByDayGuestCQRS(dayGuestResponseDTO, pageParam));
    }

    /**
     * 查询全国客流量
     * @return
     */
    @GetMapping(value = "/find/passengerflowall/list")
    public JsonResult findPassengerFlowAll(){
        return JsonResult.success(dayGuestService.findPassengerFlowAll());
    }

    /**
     * 查询全国销售额
     * @return
     */
    @GetMapping(value = "/find/saleroomall/list")
    public JsonResult findSaleroomAll(){
        return JsonResult.success(dayGuestService.findSaleroomAll());

    }
    /**
     * 查询全国销售额7天
     * @return
     */
    @GetMapping(value = "/find/day/saleroomall/list")
    public JsonResult findDaySaleroomAll(){
        DayResponse day = new DayResponse();
        day.setXse(dayGuestService.findDaySaleroomAll());//销售额
        day.setKll(dayGuestService.findDayPassengerFlowAll());//客流量
        return JsonResult.success(day);

    }

    /**
     * 查询全国客流量7天
     * @return
     */
    @GetMapping(value = "/find/day/passengerflowall/list")
    public JsonResult findDayPassengerFlowAll(){
        return JsonResult.success("");
    }
}
