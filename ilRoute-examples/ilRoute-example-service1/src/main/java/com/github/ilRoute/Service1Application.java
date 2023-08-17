package com.github.ilRoute;

import com.github.ilRoute.configuration.MyFeignClientsConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = {MyFeignClientsConfiguration.class})
public class Service1Application {
    public static void main(String[] args) {
        SpringApplication.run(Service1Application.class);
    }
}
