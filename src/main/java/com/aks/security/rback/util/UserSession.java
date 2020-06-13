package com.aks.security.rback.util;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope ( value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {

	private static Logger logger = Logger.getLogger(UserSession.class);
	
	@Autowired
	HttpSession session;
	
	
	public void getSessionDetails() {
		logger.info("Session accessed... " + session);
	}
}
