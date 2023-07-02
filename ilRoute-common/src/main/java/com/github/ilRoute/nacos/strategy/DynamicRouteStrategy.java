package com.github.ilRoute.nacos.strategy;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

/**
 * 动态路由操作接口
 * @author lwx
 */
public interface DynamicRouteStrategy {
    void add(RouteDefinition route);

    void modify(RouteDefinition route);

    void delete(String routeId);

    //这里的RouteDefinition要换成一个包装对象
    void updateAll(List<RouteDefinition> routes);


}
