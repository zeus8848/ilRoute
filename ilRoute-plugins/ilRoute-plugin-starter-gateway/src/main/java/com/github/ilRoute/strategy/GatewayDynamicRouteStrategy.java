package com.github.ilRoute.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lwx
 */
public class GatewayDynamicRouteStrategy implements ApplicationEventPublisherAware , DynamicRouteStrategy {

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
    @Autowired
    private RouteDefinitionLocator routeDefinitionLocator;

    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private GatewayProperties gatewayProperties;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * lazyPublish: true 表示不会立即发布RefreshRoutesEvent
     * @param routeDefinition
     * @param lazyPublish
     */
    public void add(RouteDefinition routeDefinition,boolean lazyPublish){
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        if (lazyPublish){
            return;
        }
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public void add(RouteDefinition routeDefinition){
        add(routeDefinition,true);
    }

    /**
     * 修改单个路由
     * @param routeDefinition
     */
    @Override
    public void modify(RouteDefinition routeDefinition){
        //先删，如果操作在缓存中找不到，那么直接删 gatewayProperties
        /*routeDefinitionWriter.delete(Mono.just(routeDefinition.getId())
                .onErrorResume(throwable -> throwable instanceof NotFoundException,(id)->{
                    gatewayProperties.getRoutes().removeIf(routePredicate-> routePredicate.getId().equals(id));
                    return Mono.empty();
                })).subscribe();*/
        delete(routeDefinition.getId());
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
    }



    /**
     * 更新全部路由
     * @param routeDefinitions
     */
    @Override
    public synchronized void  updateAll(List<RouteDefinition> routeDefinitions){
        if (CollectionUtils.isEmpty(routeDefinitions)){
            return;
        }
        Map<String, RouteDefinition> dynamicRouteDefinitionMap = routeDefinitions.stream().collect(Collectors.toMap(RouteDefinition::getId, routes->routes));
        Map<String, RouteDefinition> currentRouteDefinitionMap = locateRoute();
        routeDefinitions.stream()
                .filter(dynamic -> !currentRouteDefinitionMap.containsKey(dynamic.getId()))
                .forEach(this::add);
        routeDefinitions.stream()
                .filter(dynamic -> currentRouteDefinitionMap.containsKey(dynamic.getId()))
                .filter(dynamic -> !dynamic.equals(currentRouteDefinitionMap.get(dynamic.getId())))
                .forEach(this::modify);
        currentRouteDefinitionMap.entrySet().stream()
                .filter(entry -> !dynamicRouteDefinitionMap.containsKey(entry.getKey()))
                .map(Map.Entry::getValue)
                .map(RouteDefinition::getId)
                .forEach(this::delete);
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    /**
     * 获取gateway所有的routeDefinition
     * @return
     */
    public Map<String,RouteDefinition> locateRoute(){
        Flux<RouteDefinition> routeDefinitions = routeDefinitionLocator.getRouteDefinitions();
        return routeDefinitions.collect(Collectors.toMap(RouteDefinition::getId, RouteDefinition -> RouteDefinition)).block();
    }

    /**
     * 删除路由
     * @param routeId
     */
    @Override
    public void delete(String routeId){
        routeDefinitionWriter.delete(Mono.just(routeId))
                .onErrorResume(throwable -> throwable instanceof NotFoundException,(id)->{
                    List<RouteDefinition> routes = gatewayProperties.getRoutes();
                    routes .removeIf(routePredicate-> routePredicate.getId().equals(routeId));
                    gatewayProperties.setRoutes(routes);
                    return Mono.empty();
                }).subscribe();
    }
}
