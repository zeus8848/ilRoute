package com.github.ilRoute.nacos.properties;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author lwx
 */
@Component
public class NacosConfigProperty {

    public static final String DYNAMIC_ROUTE_NACOS_CONFIG_PREFIX = "route";
    public static final String DYNAMIC_ROUTE_NACOS_CONFIG_SUFFIX = "json";

    private NacosConfigProperties nacosConfigProperties;

    @JsonIgnore
    private Environment environment;

    private long nacosDefaultTimeout;

    private String nacosServerAddr;

    private String nacosNameSpace;

    private String nacosDataID;

    private String nacosGroup;

    private String fileExtern;

    public NacosConfigProperty(NacosConfigProperties nacosConfigProperties , Environment environment) {
        this.nacosConfigProperties = nacosConfigProperties;
        this.environment = environment;
        nacosDefaultTimeout = nacosConfigProperties.getTimeout();
        nacosServerAddr = nacosConfigProperties.getServerAddr();
        nacosNameSpace = nacosConfigProperties.getNamespace();
        fileExtern = nacosConfigProperties.getFileExtension();
        nacosDataID =DYNAMIC_ROUTE_NACOS_CONFIG_PREFIX +"-"+getRunEnvProFile()+"."+DYNAMIC_ROUTE_NACOS_CONFIG_SUFFIX;
        nacosGroup = nacosConfigProperties.getGroup();
    }

    private String getRunEnvProFile(){
        return environment.getProperty("spring.profiles.active");
    }

    @Deprecated
    private String getPrefix(){
        String prefix = nacosConfigProperties.getPrefix();
        String name = environment.getProperty("spring.application.name");
        return StringUtils.hasText(prefix) ? prefix : name;
    }

    public NacosConfigProperties getNacosConfigProperties() {
        return nacosConfigProperties;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public long getNacosDefaultTimeout() {
        return nacosDefaultTimeout;
    }

    public String getNacosServerAddr() {
        return nacosServerAddr;
    }

    public String getNacosNameSpace() {
        return nacosNameSpace;
    }

    public String getNacosDataID() {
        return nacosDataID;
    }

    public String getNacosGroup() {
        return nacosGroup;
    }
}
