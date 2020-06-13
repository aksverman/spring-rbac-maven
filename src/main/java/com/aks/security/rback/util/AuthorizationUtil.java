package com.aks.security.rback.util;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;

public interface AuthorizationUtil {
	
	boolean isAuthorize(Object principal);
	boolean isAuthorize(Authentication authentication, String methodauth);
	boolean isAuthorize(Object principal, HttpHeaders header);
	
}
