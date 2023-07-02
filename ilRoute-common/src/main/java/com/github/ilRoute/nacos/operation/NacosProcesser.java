package com.github.ilRoute.nacos.operation;

import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.github.ilRoute.util.DefaultThreadPoolFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;

/**
 * @author lwx
 */
public abstract class NacosProcesser implements ConfigOperation, DisposableBean {
    private ExecutorService executorService = DefaultThreadPoolFactory.getExecutorService("nacos-config");

    @Autowired
    private NacosConfigOperation nacosConfigOperation;

    private Listener listener;

    @PostConstruct
    public void init() throws NacosException {
        String config = nacosConfigOperation.getConfig();
        this.processBeforeInitialization(config);
        listener = nacosConfigOperation.addListener(executorService,this);
    }

    @Override
    public void destroy() throws Exception {
        nacosConfigOperation.unsubscribe(listener);
        executorService.shutdownNow();
    }
}
