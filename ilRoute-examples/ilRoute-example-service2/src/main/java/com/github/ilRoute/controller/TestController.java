package com.github.ilRoute.controller;

import com.github.ilRoute.dto.User;
import com.github.ilRoute.result.Result;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/test1")
    public Result testGateway(@RequestBody User user, HttpServletRequest request){
        System.out.println(request.getHeader("myheader"));
        System.out.println(user);
        return Result.fail("请求不见喽");
    }

    @GetMapping("/test11")
    public Result testGateway1(){
        System.out.println("test11");
        return Result.fail("请求不见喽");
    }
}
