package com.github.ilRoute.operation;
import com.github.ilRoute.nacos.operation.NacosProcessor;
import com.github.ilRoute.parser.GatewayRouteConfigParser;
import com.github.ilRoute.strategy.DynamicRouteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;


/**
 * @author lwx
 */
public class GatewayNacosProcessor extends NacosProcessor {
    @Autowired
    private DynamicRouteStrategy dynamicRouteStrategy;

    @Override
    public void callbackConfig(String config) {
        if (!StringUtils.hasText(config)){
            throw new IllegalArgumentException("nacos config should not null");
        }
        updateAllRoutes(config);
    }

    @Override
    public void addAllRoutesWhenApplicationStarts(String routesConfig) {
        //在应用启动时候，需要将路由信息设置到gateway，因为我们的gateway配置文件里面没有配置路由信息
        //路由信息在route-dev.json文件中配置
        updateAllRoutes(routesConfig);
    }


    /**
     *
     * @param routeConfig
     */
    public void updateAllRoutes(String routeConfig){
        dynamicRouteStrategy.updateAll(new GatewayRouteConfigParser().parseConfig(routeConfig));
    }


}
