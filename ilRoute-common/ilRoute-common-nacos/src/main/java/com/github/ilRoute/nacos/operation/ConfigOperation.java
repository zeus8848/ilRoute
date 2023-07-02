package com.github.ilRoute.nacos.operation;

/**
 * @author lwx
 */
public interface ConfigOperation {
    /**
     * 当配置发生改变时候，回调此方法
     * @param configInfo
     */
    void callbackConfig(String configInfo);

    /**
     * 解析配置规则
     * @param configInfo
     * @return
     */
    Object parse2RouteObject(String configInfo);

    /**
     * @param configInfo
     */
    void processBeforeInitialization(Object configInfo);
}
