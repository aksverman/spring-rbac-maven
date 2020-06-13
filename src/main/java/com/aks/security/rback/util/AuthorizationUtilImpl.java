package com.aks.security.rback.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


/**
 * Authorizatio util for custom authorizatio of resources based on invoked method.
 * Get the Auhorization and check the authority i.e., privileges of current user for requested resource.
 * 
 * @author Ankush.Verman
 *
 */
@Service(value = "customAuthorizationUtil")
public class AuthorizationUtilImpl implements AuthorizationUtil {

	private static Logger logger = Logger.getLogger(AuthorizationUtilImpl.class);
	
	@Autowired
	private UserSession session;
	
	@Override
	public boolean isAuthorize(Object authentication) {
		logger.info("Start : " + getClass().getName() + " : isAuthorize()");
		
		return authentication != null;
	}
	
	public boolean isAuthorize(Authentication authentication, String methodauth) {
		logger.info("Start : " + getClass().getName() + " : isAuthorize()");
		
		return checkAuthority(authentication, methodauth);
	}

	public boolean isAuthorize(Object principal, HttpHeaders header) {
		logger.info("Start : " + getClass().getName() + " : isAuthorize()");
		logger.info(header);
		return true;
	}
	
	/**
	 * Checks for the authority of resource with current principal
	 * 
	 * @param authentication - Current Authenticated user
	 * @param methodauth	- Requested method resource name
	 * @return - true if authorized, otherwise false 
	 */
	private boolean checkAuthority(Authentication authentication, String methodauth) {
		Collection<? extends GrantedAuthority> roleSet = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iterator = roleSet.iterator();
		Set<String> roles = new HashSet<String>( roleSet.size());
		
		while(iterator.hasNext())
			roles.add(iterator.next().getAuthority());
		
		for (String role : roles) {
			
			if (role.contains(methodauth)) {
				return true;
			}
		}
	
		return false;
	}
}
