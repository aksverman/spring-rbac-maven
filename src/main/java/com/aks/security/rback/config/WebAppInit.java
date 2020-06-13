package com.aks.security.rback.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.rudra.aks.util.SessionListener;


public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { PersistenceConfig.class, RBACSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addListener(new SessionListener());
		super.onStartup(servletContext);
	}
	
	

}
