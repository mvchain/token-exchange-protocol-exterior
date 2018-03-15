package com.mvc.sell;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-05-25 12:44
 */
@EnableEurekaClient
@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableTransactionManagement
@EnableSwagger2
@EnableFeignClients
public class TokenSellBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(TokenSellBootstrap.class).web(true).run(args);
    }
}
