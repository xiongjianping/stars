package com.yinghuaicc.stars.controller.business.pc.intervalset;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.controller.config.system.SystemResource;
import com.yinghuaicc.stars.controller.config.utils.EndecryptUtil;
import com.yinghuaicc.stars.service.cqrs.Intervalset.ConditionGuestIntervalService;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.request.ConditionGuestIntervalRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * 客销度业态区间设置controller
 */
@RestController
@RequestMapping(value="/conditionguestinterval")
public class ConditionGuestIntervalController {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ConditionGuestIntervalService conditionGuestIntervalService;


    /**
     * 客销度区间设置业态列表
     * @param conditionGuestIntervalRequestDTO
     * @param pageParam
     * @return
     */
    @PostMapping(value = "/find/conditionguestinterval/list")
    public JsonResult findConditionGuestIntervalList(@RequestBody ConditionGuestIntervalRequestDTO conditionGuestIntervalRequestDTO, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                conditionGuestIntervalService.findConditionGuestIntervalByConditionGuestIntervalCQRS(conditionGuestIntervalRequestDTO, pageParam));
    }


}
