package com.yinghuaicc.stars.controller.business.app.sso;

import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.service.sso.SsoService;
import com.yinghuaicc.stars.service.sso.dto.request.AppSsoRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/25 下午4:52
 * @Description:
 * @Modified:
 */
@RestController
@RequestMapping(value = "/app/sso")
public class AppOssLoginController {

    @Autowired
    private SsoService ssoService;

    /**
     *@Author:Fly Created in 2018/7/25 下午5:05
     *@Description: app单点登录
     */
    @PostMapping(value = "/login")
    public JsonResult appSsoLogin(@Validated @RequestBody AppSsoRequestDTO appSsoRequestDTO){

        return JsonResult.success(ssoService.appSsoLogin(appSsoRequestDTO));
    }
}
