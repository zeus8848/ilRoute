package com.github.ilRoute.configuration;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@LoadBalancerClient(configuration = MyRandomLoadBalancerClientConfiguration.class)
public class MyLoadBalancerAutoConfiguration {
}
