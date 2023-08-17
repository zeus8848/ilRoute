package com.github.ilRoute.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.ilRoute.util.JsonUtils;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.util.StringUtils;

import java.util.List;

public class GatewayRouteConfigParser implements ConfigParser{

    /**
     * 解析路由配置规则
     * @param configContent
     * @return
     */
    @Override
    public List<RouteDefinition> parseConfig(String configContent) {
        if (!StringUtils.hasText(configContent)){
            return null;
        }
        List<RouteDefinition> routeDefinitions;
        try {
            routeDefinitions = JsonUtils.json2List(configContent, new TypeReference<List<RouteDefinition>>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e.getMessage(),e);
        };
        return routeDefinitions;
    }
}
