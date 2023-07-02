package com.github.ilRoute.configuration;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.github.ilRoute.nacos.properties.NacosConfigProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@ConditionalOnProperty(prefix = "spring.ilRoute.gateway.dynamic.route", name = "enable",havingValue = "true")
public class NacosConfigServiceConfiguration {
    @Autowired
    private NacosConfigProperty nacosConfigProperty;

    @Bean
    @ConditionalOnMissingBean(ConfigService.class)
    public ConfigService nacosConfigService() throws NacosException {
        Properties properties = new Properties();
        properties.setProperty("serverAddr",nacosConfigProperty.getNacosServerAddr());
        properties.setProperty("namespace",nacosConfigProperty.getNacosNameSpace());
        return NacosFactory.createConfigService(properties);
    }
}
