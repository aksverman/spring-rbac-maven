package com.aks.security.rback.web.controller;

import static com.aks.security.rback.constants.Constants.CHANGE_PASSWORD_PAGE;
import static com.aks.security.rback.constants.Constants.INDEX_PAGE;
import static com.aks.security.rback.constants.Constants.RESET_PASSWORD_PAGE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aks.security.rback.model.CurrentUser;
import com.aks.security.rback.service.UserService;

@Controller
@RequestMapping("/admin")	
public class AdminController {
	
	static Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired
	UserService		userService;
	
	@RequestMapping("/resetpasswordform")
	public	ModelAndView	getResetPasswordPage() {
		return new ModelAndView(RESET_PASSWORD_PAGE);
	}
	
	@RequestMapping("/resetpassowrd")
	public ModelAndView		resetPassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("username") String username) {
		logger.info("Reset password request for user : " + username);
		if ( username == null)
			return new ModelAndView(INDEX_PAGE);
		String contextPath = "http://" + request.getHeader("HOST") + request.getContextPath();
		//String urlPath = request.getHeader("REFERER");
		boolean mailSent = userService.resetPassword(username, contextPath);
		logger.info("Reset request " + mailSent);
		
		return new ModelAndView(INDEX_PAGE, "msg", "Password Reset Link Sent to your mail.");
	}
	
	@RequestMapping(path = "/changePasswordform", method = RequestMethod.GET)
	public ModelAndView		changePasswordForm(@RequestParam("userid") String userid, @RequestParam("token") String token) {
		logger.info("Password change requested ... [ " + userid + ", " + token + " ]");
		String msg = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ( "anonymousUser" != principal ) 
			return new ModelAndView(CHANGE_PASSWORD_PAGE, "userid", ((CurrentUser)principal).getUserId());
		
		if (userid != null && token != null ) {
			msg = userService.validatePasswordChangeRequest(Integer.parseInt(userid), token);
			if ( "token validated" == msg)
			return new ModelAndView(CHANGE_PASSWORD_PAGE, "userid", userid);
		}
		return new ModelAndView(INDEX_PAGE, "msg", msg);
	}
	
	@RequestMapping(path = "/changepassowrd", method = RequestMethod.POST)
	public	ModelAndView	changePassword(HttpServletRequest request, HttpServletResponse response ) {
		int userid = Integer.parseInt(request.getParameter("userid"));
		String pass1 = request.getParameter("password");
		//String pass2 = request.getParameter("repassword");
		String msg = "";
		if ( userid > 0 && pass1 != null & pass1.length() > 0)
			msg = userService.updatePassword(userid, pass1);
		logger.info("Password updated for user : " + userid + " : " + msg);
		return new ModelAndView(INDEX_PAGE, "msg", "password updated successfully");
	}
	
	@PreAuthorize("@customAuthorizationUtil.isAuthorize(authentication, 'DELETE')")
	@RequestMapping(path="/test", method = RequestMethod.GET)
	public	String	testForSessionExpire() {
		logger.info(" " + getClass().getName());
		return INDEX_PAGE;
	}
}
