package org.learning.server.config;

import org.learning.server.interceptor.LogInterceptor;
import org.learning.server.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configure the Interceptors
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**");
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns("/**");
    }
}
