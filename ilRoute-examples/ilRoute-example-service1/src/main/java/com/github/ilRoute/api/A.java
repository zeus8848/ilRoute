package com.github.ilRoute.api;

import com.github.ilRoute.dto.User;
import com.github.ilRoute.factory.FallbackFactoryTest;
import com.github.ilRoute.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@RestController
@FeignClient(name = "service2",fallbackFactory = FallbackFactoryTest.class, decode404 = true)
public interface A {
    @PostMapping("/testGateway/test12")
    Result test(@RequestBody User user);

    @GetMapping("/testGateway/test11")
    Result test1();

}
