package com.junkfood;


import com.junkfood.interceptor.Globalinterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    Globalinterceptor globalinterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalinterceptor)
                .addPathPatterns("/**") //muốn interceptors thực hiện
                .excludePathPatterns("/rest/**","/admin/**","/assets/**")// loại trừ địa chỉ không sử dụng interceptor
        ;
    }
}
