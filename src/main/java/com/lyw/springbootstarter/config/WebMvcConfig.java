package com.lyw.springbootstarter.config;

import com.lyw.springbootstarter.common.UserHolder;
import com.lyw.springbootstarter.intercepter.UserInterceptor;
import com.lyw.springbootstarter.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-22 13:34
 * @Description:
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private UserService userService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        UserInterceptor userInterceptor = new UserInterceptor(userService);

        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/**");

    }
}


