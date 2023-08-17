package com.github.ilRoute.properties;

import com.github.ilRoute.agent.ServiceInstanceExtAgent;

/**
 * @author lwx
 */
public class ServiceInstanceExtHolder {

    private ServiceInstanceExtAgent serviceInstanceExtAgent;

    ServiceInstanceExtHolder(ServiceInstanceExtAgent serviceInstanceExtAgent){
        this.serviceInstanceExtAgent = serviceInstanceExtAgent;
    }

    public String getVersion() {
        return serviceInstanceExtAgent.getVersion();
    }

    public String getZone() {
        return serviceInstanceExtAgent.getZone();
    }

    public String getGroup() {
        return serviceInstanceExtAgent.getGroup();
    }

    public String getEnvironment() {
        return serviceInstanceExtAgent.getEnvironment();
    }
}
