package com.rudra.aks.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SessionListener implements HttpSessionListener, ApplicationContextAware {

	private static Logger logger = Logger.getLogger(SessionListener.class);
	private static ApplicationContext	_context;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.info("Session is created with inactive time 30 seconds ");
		//se.getSession().setMaxInactiveInterval(15);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		long etime = (se.getSession().getLastAccessedTime() - se.getSession().getCreationTime());
		//logger.info("Session is expired! : " + etime);
		
		String path = se.getSession().getServletContext().getContextPath();
		//_context.getBean(requiredType)
		
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		_context = applicationContext;
	}


}
