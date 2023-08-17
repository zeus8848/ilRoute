package com.github.ilRoute.nacos.operation;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.github.ilRoute.callback.ConfigChangeCallBack;
import com.github.ilRoute.nacos.properties.NacosConfigProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executor;

/**
 * @author lwx
 */
public class
NacosConfigOperation {

    @Autowired(required = false)
    private ConfigService configService;
    @Autowired(required = false)
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

    public Listener addListener(Executor executor, ConfigChangeCallBack configChangeCallBack) throws NacosException {
        Listener listener = new Listener() {
            @Override
            public Executor getExecutor() {
                return executor;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                configChangeCallBack.callbackConfig(configInfo);
            }
        };
        configService.addListener(nacosConfigProperty.getNacosDataID(),nacosConfigProperty.getNacosGroup(),listener);
        return listener;
    }
}
