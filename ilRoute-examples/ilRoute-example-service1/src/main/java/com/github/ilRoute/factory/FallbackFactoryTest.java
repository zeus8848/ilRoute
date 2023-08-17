package com.github.ilRoute.factory;

import com.github.ilRoute.api.A;
import com.github.ilRoute.dto.User;
import com.github.ilRoute.result.Result;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FallbackFactoryTest implements FallbackFactory<A> {
    @Override
    public A create(Throwable cause) {
        return new A() {
            @Override
            public Result test(User user) {
                System.out.println("失败啦");
                return Result.fail("失败啦");
            }

            @Override
            public Result test1() {
                System.out.println("失败啦");
                return Result.fail("失败啦");
            }
        };
    }
}
