package com.github.ilRoute.nacos.configuration;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.github.ilRoute.nacos.properties.NacosConfigProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class NacosConfigServiceConfiguration {
    @Autowired
    private NacosConfigProperty nacosConfigProperty;

    @Bean
    public ConfigService nacosConfigService() throws NacosException {
        Properties properties = new Properties();
        properties.setProperty("serverAddr",nacosConfigProperty.getNacosServerAddr());
        properties.setProperty("namespace",nacosConfigProperty.getNacosNameSpace());
        return NacosFactory.createConfigService(properties);
    }
}
