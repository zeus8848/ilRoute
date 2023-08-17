package com.github.ilRoute.configuration;

import com.github.ilRoute.interceptor.MyInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author lwx
 */
@Configuration(proxyBeanMethods = false)
public class MyFeignClientsConfiguration {

    @Bean
    public Retryer feignRetryer(){
        return new Retryer.Default(200, TimeUnit.SECONDS.toMillis(1),2);
    }

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new MyInterceptor();
    }


    //public
}
