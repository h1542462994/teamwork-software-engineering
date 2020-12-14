package main.java.com.example.config;


import main.java.com.example.interceptor.LoginInterceptor;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/loginsuccess","/loginfail");
    }
    //后面都是自动生成的需要实现的函数，我是eclipse的，所以有些代码，不是很看得懂
    //但是看这个应该是添加登陆成功和失败的struts2标签的web.xml配置文件的作用
    //这两句写的人：朱佰亿
    //以下是自动生成的函数，不知道为什么会缺。
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addFormatters(FormatterRegistry arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addViewControllers(ViewControllerRegistry arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public MessageCodesResolver getMessageCodesResolver() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Validator getValidator() {
		// TODO 自动生成的方法存根
		return null;
	}
}
