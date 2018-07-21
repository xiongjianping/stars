package com.yinghuaicc.stars.controller.business.pc.triangle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/19 上午10:30
 * @Description: 动态三角形
 * @Modified:
 */
@RestController
@RequestMapping(value = "/triangle")
public class TriangleController {


    @Autowired
    private ApplicationContext applicationContext;


}
