package com.github.ilRoute.controller;

import com.github.ilRoute.api.A;
import com.github.ilRoute.dto.User;
import com.github.ilRoute.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * @author lwx
 */
@RestController()
@RequestMapping("/testGateway")
public class TestController {

    @Autowired
    private A a;

    @GetMapping("/test")
    public Result testGateway(HttpServletRequest request){
        Result result = a.test(new User("lwx", 20));
        return result;
    }

    @GetMapping("/test1")
    public String testGateway1(HttpServletRequest request){
        a.test1();
        return "success";
    }
}
