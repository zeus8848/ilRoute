package com.github.ilRoute.callback;

/**
 * @author lwx
 */
public interface ConfigChangeCallBack {
    /**
     * 当配置发生改变时候，回调此方法
     * @param configInfo
     */
    void callbackConfig(String configInfo);
}
