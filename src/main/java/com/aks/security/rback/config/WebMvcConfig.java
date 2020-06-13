package com.aks.security.rback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;


@Configuration
@ComponentScan//(basePackages = {"com.rudra.aks.web", "com.rudra.aks.util"} )
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		//registry.addViewController("/resources").setViewName("resource");
	}

	@Bean
	public	FreeMarkerConfigurer	freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("classpath:/templates");
		return configurer;
	}
	
	
	@Bean 
	public FreeMarkerViewResolver freemarkerViewResolver() { 
	    FreeMarkerViewResolver resolver = new FreeMarkerViewResolver(); 
	    resolver.setCache(true); 
	    resolver.setPrefix("/"); 
	    resolver.setSuffix(".ftl"); 
	    resolver.setRequestContextAttribute("rc");
	    resolver.setOrder(1);
	    return resolver; 
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/public/views/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(2);
		return resolver;
	}

}
