package com.github.ilRoute.configuration;

import com.github.ilRoute.nacos.operation.NacosConfigOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IlRouteNacosConfiguration {

    @Bean
    @ConditionalOnMissingBean(NacosConfigOperation.class)
    public NacosConfigOperation nacosConfigOperation(){
        return new NacosConfigOperation();
    }
}
