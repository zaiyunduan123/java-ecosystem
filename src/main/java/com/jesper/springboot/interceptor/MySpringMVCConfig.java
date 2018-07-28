package com.jesper.springboot.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jiangyunxiong on 2018/7/26.
 */
@SpringBootConfiguration
public class MySpringMVCConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private UserConfig userConfig;

    /**
     * 自定义拦截器
     * 继承WebMvcConfigurerAdapter，chon
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userConfig).addPathPatterns("/**");
    }
}
