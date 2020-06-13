package com.aks.security.rback.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private static final Logger logger = Logger.getLogger(CustomAccessDeniedHandler.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		logger.info("Custom Access Denied handler : " + response.getStatus());
		//response.sendError(HttpServletResponse.SC_FORBIDDEN, "no access re-directing to home page");
		response.sendRedirect("/rbac-security/accessdenied/" + HttpServletResponse.SC_FORBIDDEN + "/" + accessDeniedException.getMessage());
	}

}
