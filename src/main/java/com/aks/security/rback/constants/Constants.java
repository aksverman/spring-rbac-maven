package com.aks.security.rback.constants;

import org.springframework.beans.factory.annotation.Value;

public class Constants {

	/*
	 * JSP view page constants
	 */
	public static final String INDEX_PAGE = "indexjsp";
	public static final String HOME_PAGE = "welcome";
	public static final String REGISTER_FORM = "registeruser";
	public static final String UPDATE_FORM = "updateuser";
	public static final String ROLE_FORM =  "addroles";
	public static final String ACCESSDENIED_PAGE = "accessdenied";
	public static final String USERSLIST_PAGE = "userslist";
	public static final String CUSTOM_LOGIN_PAGE = "customlogin";
	public static final String RESET_PASSWORD_PAGE = "forgotpassword";
	public static final String CHANGE_PASSWORD_PAGE = "changepassword";
	
	/*
	 * Java Mail Constants
	 * @see com.rudra.aks.service.UserService#resetPassword(java.lang.String)
	 */
	public static final	String FROM_ADDRESS = "vermanjava@gmail.com";
	public static final	String RESET_TOKEN_SUB = "Password Reset Link";
	
	/*
	 * Sets default expire time for password reset tokens
	 * Default time in minutes is 2 days long [ 2d * 24 hrs * 60 mnts ]
	 */
	public static final int	TOKEN_EXPIRE_TIME = 5; //2*24*60;
	
	@Value("${Permission_VIEW}")
	public static String VIEW_ACCESS;
	
	
}
