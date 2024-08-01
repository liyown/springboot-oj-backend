package com.lyw.springbootstarter.openfeign.config;

import com.lyw.springbootstarter.common.UserHolder;
import com.lyw.springbootstarter.openfeign.client.fallback.RemoteCodeCodeSandBoxServiceFallbackFactory;
import feign.Logger;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

/**
 * @author: liuyaowen
 * @poject: springboot-OJ-backend
 * @create: 2024-07-31 19:22
 * @Description:
 */
@Slf4j
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


    @Bean
    public RemoteCodeCodeSandBoxServiceFallbackFactory itermServiceFallbackFactory() {
        return new RemoteCodeCodeSandBoxServiceFallbackFactory();
    }


}
