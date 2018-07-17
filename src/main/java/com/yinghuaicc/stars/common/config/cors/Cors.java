package com.yinghuaicc.stars.common.config.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author:Fly
 * @Date:Create in 2018/6/21 下午12:51
 * @Description: 解决跨域问题
 * @Modified:
 */
@Configuration
public class Cors {

    /**
     *@Author:Fly Created in 2018/6/21 下午12:52
     *@Description: 配置全局跨域
     */
    @Bean
    public WebMvcConfigurer corsConfigurer(){

        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
