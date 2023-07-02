package com.github.ilRoute.operation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.ilRoute.nacos.operation.NacosProcesser;
import com.github.ilRoute.nacos.strategy.DynamicRouteStrategy;
import com.github.ilRoute.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author lwx
 */
public class GatewayNacosProcesser extends NacosProcesser {
    @Autowired
    private DynamicRouteStrategy dynamicRouteStrategy;

    @Override
    public void callbackConfig(String config) {
        if (!StringUtils.hasText(config)){
            throw new IllegalArgumentException("nacos config should not null");
        }
        updateAll(config);
    }

    @Override
    public List<RouteDefinition> parse2RouteObject(String configStr) {
        if (!StringUtils.hasText(configStr)){
            return null;
        }
        List<RouteDefinition> routeDefinitions;
        try {
            routeDefinitions = JsonUtils.json2List(configStr, new TypeReference<List<RouteDefinition>>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e.getMessage(),e);
        };
        return routeDefinitions;
    }

    /**
     * 在应用启动时候，需要将路由信息设置到gateway，因为我们的gateway配置文件里面没有配置路由信息
     * 路由信息在route-dev.json文件中配置
     * @param config
     */
    @Override
    public void processBeforeInitialization(Object config) {
        if (config instanceof String){
            updateAll((String) config);
        }
    }


    /**
     *
     * @param routeConfig
     */
    public void updateAll(String routeConfig){
        dynamicRouteStrategy.updateAll(parse2RouteObject(routeConfig));
    }

}
