package com.github.ilRoute.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class MyInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header("myheader","laiwenx header");
    }
}
