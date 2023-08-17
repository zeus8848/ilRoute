package com.github.ilRoute.nacos.operation;

import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.github.ilRoute.callback.ConfigChangeCallBack;
import com.github.ilRoute.util.DefaultThreadPoolFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;

/**
 * @author lwx
 */
public abstract class NacosProcessor implements ConfigChangeCallBack{
    private ExecutorService executorService = DefaultThreadPoolFactory.getExecutorService("nacos-config");

    @Autowired
    private NacosConfigOperation nacosConfigOperation;

    private Listener listener;

    @PostConstruct
    public void init() throws NacosException {
        String config = nacosConfigOperation.getConfig();
        addAllRoutesWhenApplicationStarts(config);
        listener = nacosConfigOperation.addListener(executorService,this);
    }

    /**
     * 当程序启动的时候，手动添加路由信息
     * @param routesConfig
     */
    public abstract void addAllRoutesWhenApplicationStarts(String routesConfig);

    @PreDestroy
    public void destroy(){
        nacosConfigOperation.unsubscribe(listener);
        executorService.shutdownNow();
    }
}
