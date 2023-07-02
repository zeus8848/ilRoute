package com.github.ilRoute.nacos.operation;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.github.ilRoute.nacos.properties.NacosConfigProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * @author lwx
 */
public class NacosConfigOperation {
    @Autowired
    private ConfigService configService;
    @Autowired
    private NacosConfigProperty nacosConfigProperty;


    public String getConfig() throws NacosException {
       return  configService.getConfig(nacosConfigProperty.getNacosDataID(),nacosConfigProperty.getNacosGroup(),nacosConfigProperty.getNacosDefaultTimeout());
    }

    public void publishConfig(String content) throws NacosException {
        configService.publishConfig(nacosConfigProperty.getNacosDataID(),nacosConfigProperty.getNacosGroup(),content);
    }

    public void unsubscribe(Listener listener){
        configService.removeListener(nacosConfigProperty.getNacosDataID(),nacosConfigProperty.getNacosGroup(),listener);
    }

    public Listener addListener(Executor executor, ConfigOperation configOperation) throws NacosException {
        Listener listener = new Listener() {
            @Override
            public Executor getExecutor() {
                return executor;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                configOperation.callbackConfig(configInfo);
            }
        };
        configService.addListener(nacosConfigProperty.getNacosDataID(),nacosConfigProperty.getNacosGroup(),listener);
        return listener;
    }
}
