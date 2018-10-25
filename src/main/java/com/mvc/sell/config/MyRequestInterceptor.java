package com.mvc.sell.config;

import com.mvc.common.context.BaseContextHandler;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * bean confing
 *
 * @author qiyichen
 * @create 2018/3/12 19:38
 */
@Configuration
public class MyRequestInterceptor implements RequestInterceptor{

    @Value("${service.eurekaKey}")
    private String eurekaKey;

//    @Bean
//    public RequestInterceptor headerInterceptor() {
//        return new RequestInterceptor() {
//            @Override
//            public void apply(RequestTemplate requestTemplate) {
//                String token = (String) BaseContextHandler.get("Authorization");
//                requestTemplate.header("Authorization", token);
//                requestTemplate.header("type", "feign");
//                requestTemplate.header("Accept-Language", LocaleContextHolder.getLocale().toString());
//                requestTemplate.header("eurekaKey", eurekaKey);
//            }
//        };
//    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = (String) BaseContextHandler.get("Authorization");
        requestTemplate.header("Authorization", token);
        requestTemplate.header("type", "feign");
        requestTemplate.header("Accept-Language", LocaleContextHolder.getLocale().toString());
        requestTemplate.header("eurekaKey", eurekaKey);
    }
}
