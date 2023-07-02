package com.github.ilRoute.configuration;

import com.github.ilRoute.operation.GatewayNacosProcesser;
import com.github.ilRoute.strategy.GatewayDynamicRouteStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lwx
 */
@Configuration
public class IlRouteGatewayConfiguration {

    @Bean
    @ConditionalOnMissingBean(GatewayNacosProcesser.class)
    public GatewayNacosProcesser gatewayNacosProcesser(){
        return new GatewayNacosProcesser();
    }

    @Bean
    @ConditionalOnMissingBean(GatewayDynamicRouteStrategy.class)
    public GatewayDynamicRouteStrategy gatewayDynamicRouteStrategy(){
        return new GatewayDynamicRouteStrategy();
    }
}
