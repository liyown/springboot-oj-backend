package com.lyw.springbootstarter;

import com.lyw.springbootstarter.openfeign.config.FeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableFeignClients(basePackages = "com.lyw.springbootstarter.openfeign.client", defaultConfiguration = FeignConfig.class)
@MapperScan("com.lyw.springbootstarter.mapper")
public class SpringbootStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStarterApplication.class, args);
    }

}
