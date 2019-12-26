package com.imooc.config;

import com.imooc.controller.interceptor.OneInterceptor;
import com.imooc.controller.interceptor.TwoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Arsenal
 * created on 2019/2/22 1:21
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截器按照顺序执行
        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**");
        registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/two/**")
                                                    .addPathPatterns("/one/**");

        super.addInterceptors(registry);
    }

}
