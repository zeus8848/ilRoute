package com.github.ilRoute.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * @author lwx
 * 从配置文件中获取版本号等
 */
public class ServiceInstanceExtConfigAgent implements ServiceInstanceExtAgent{
    @Autowired
    private Environment environment;

    @Override
    public String getVersion() {
        return null;
    }

    @Override
    public String getZone() {
        return null;
    }

    @Override
    public String getEnvironment() {
        return null;
    }

    @Override
    public String getGroup() {
        return null;
    }
}
