package com.github.ilRoute.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * @author lwx
 */
@RestController()
@RequestMapping("/testGateway")
public class TestController {

    @GetMapping("/test")
    public String testGateway(HttpServletRequest request){
        System.out.println(request.getRemoteHost());
        System.out.println(request.getRemotePort());
        return "success";
    }
}
