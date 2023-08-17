package com.github.ilRoute.initializer;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.github.ilRoute.constant.PluginConstant;
import com.github.ilRoute.properties.ServiceInstanceExtHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lwx
 */
@Component
public class ServiceDiscoveryApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Autowired
    private ServiceInstanceExtHolder serviceInstanceExtHolder;
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        applicationContext.getBeanFactory().addBeanPostProcessor(new InstantiationAwareBeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof NacosDiscoveryProperties){
                    NacosDiscoveryProperties nacosDiscoveryProperties = (NacosDiscoveryProperties) bean;
                    Map<String, String> metadata = nacosDiscoveryProperties.getMetadata();
                    metadata.put(PluginConstant.VERSION,serviceInstanceExtHolder.getVersion());
                    metadata.put(PluginConstant.GROUP,serviceInstanceExtHolder.getGroup());
                    metadata.put(PluginConstant.ZONE,serviceInstanceExtHolder.getZone());
                    metadata.put(PluginConstant.Environment,serviceInstanceExtHolder.getEnvironment());
                }
                return  bean;
            }
        });
    }

}
