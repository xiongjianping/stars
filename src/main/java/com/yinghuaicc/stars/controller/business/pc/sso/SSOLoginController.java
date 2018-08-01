package com.yinghuaicc.stars.controller.business.pc.sso;

import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.service.sso.SsoService;
import com.yinghuaicc.stars.service.sso.dto.request.SsoRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/24 下午4:40
 * @Description: 平台到业务系统单点
 * @Modified:
 */
@RestController
@RequestMapping(value = "/sso")
public class SSOLoginController {

    @Autowired
    private SsoService ssoService;

    /**
     *@Author:Fly Created in 2018/7/24 下午4:41
     *@Description: 单点登录
     */
    @PostMapping(value = "/login")
    public JsonResult ssoLogin(@Validated @RequestBody SsoRequestDTO ssoRequestDTO){

        return JsonResult.success(ssoService.ssoLogin(ssoRequestDTO));
    }
}
