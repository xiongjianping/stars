package com.yinghuaicc.stars.service.sso;

import com.yinghuaicc.stars.service.sso.dto.request.SsoRequestDTO;
import com.yinghuaicc.stars.service.tissue.dto.response.EmployeeLoginTokenResponseDTO;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/24 下午4:44
 * @Description: 单点登录
 * @Modified:
 */
public interface SsoService {

    /**
     *@Author:Fly Created in 2018/7/24 下午4:52
     *@Description: 单点登录
     */
    EmployeeLoginTokenResponseDTO ssoLogin(SsoRequestDTO ssoRequestDTO);
}
