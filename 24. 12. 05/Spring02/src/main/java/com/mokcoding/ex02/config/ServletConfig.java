package com.mokcoding.ex02.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// servlet-context.xml�� ������
@Configuration // Spring Container���� �����ϴ� ���� Ŭ����
@EnableWebMvc // Spring MVC ��� ���
@ComponentScan(basePackages = { "com.mokcoding.ex02" }) // component scen ����

public class ServletConfig implements WebMvcConfigurer {
	// ViewResolver ���� �޼ҵ�
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}

	// ResourceHandlers ���� �޼ҵ�
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// resources ���丮 ����
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
