package com.aks.security.rback.web.controller;

import static com.aks.security.rback.constants.Constants.HOME_PAGE;
import static com.aks.security.rback.constants.Constants.INDEX_PAGE;
import static com.aks.security.rback.constants.Constants.REGISTER_FORM;
import static com.aks.security.rback.constants.Constants.UPDATE_FORM;
import static com.aks.security.rback.constants.Constants.USERSLIST_PAGE;
import static com.aks.security.rback.constants.Constants.VIEW_ACCESS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aks.security.rback.exception.CustomException;
import com.aks.security.rback.model.CurrentUser;
import com.aks.security.rback.model.Privilege;
import com.aks.security.rback.model.UserBO;
import com.aks.security.rback.service.UserService;

/**
 * Controller handles request for creating new user,
 * updating an existing user, deleting an existing user & listing all the users.
 * 
 * * Handles & populate users details to UI after successfull login. 
 * 
 * @author Ankush.Verman
 *
 */

@Controller
@RequestMapping("/user")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService		userService;
	
	/*
	 * Comment this method, only for calls test purpose.
	 */
	@RequestMapping("/")
	public String test() {
		logger.info("Controller accessed...");
		return "hellow security";
	}
	
	@ModelAttribute("userform")
	public UserBO	userModel() {
		UserBO userbo = new UserBO();
		return userbo;
	}
	
	
	@RequestMapping( value="/addUser", method = RequestMethod.POST) 
	public ModelAndView	addUser( @ModelAttribute("userform") UserBO user, BindingResult bindingResult) {
		logger.info("Adding new user : " + user);
		if( bindingResult.hasErrors())
			return new ModelAndView(INDEX_PAGE, "bindingerrors", bindingResult.getAllErrors());
			
		boolean isUserCreated = false;
		if(user != null)
			isUserCreated = userService.saveUser(user);
		logger.info("User added : " + isUserCreated);
		if(!isUserCreated) 
			return new ModelAndView(INDEX_PAGE);
		return new ModelAndView(HOME_PAGE);
	} 
	
	
	@PreAuthorize("@customAuthorizationUtil.isAuthorize(authentication, 'DELETE')")
	//@PreAuthorize("@customAuthorizationUtil.isAuthorize(principal, #header)")
	//@PreAuthorize("hasAuthority('DELETE')")
	@RequestMapping( value = "/deleteUser", method = RequestMethod.GET) 
	public ModelAndView	deleteUser( @ModelAttribute("userform") UserBO user,@RequestHeader HttpHeaders header) throws Exception {
		logger.info("Deleting user info : " + user);
		if( user != null)
			userService.deleteUser(user.getUsername());
		//return HOME_PAGE;
		return login();
	}
	
	@PreAuthorize("@customAuthorizationUtil.isAuthorize(authentication, 'EDIT')")
	@RequestMapping( value = "/updateUser", method = RequestMethod.POST) 
	public ModelAndView	updateUser( @ModelAttribute("userform") UserBO user, BindingResult bindingResult) throws CustomException {
		logger.info("Updating user info : " + user);
		if( bindingResult.hasErrors())
			return new ModelAndView(INDEX_PAGE, "bindingerrors", bindingResult.getAllErrors());
			
		if( user != null )
			user = userService.updateUser(user);
		logger.info("User updated : " + user);
		//return new ModelAndView(HOME_PAGE);
		return login();
	}
	
	@PreAuthorize("@customAuthorizationUtil.isAuthorize(authentication, 'VIEW')")
	@RequestMapping( value = "/listUsers", method = RequestMethod.GET) 
	public ModelAndView	listUser() {
		logger.info("Listing all users ");
		List<UserBO> usersList = userService.usersList();
		return new ModelAndView(USERSLIST_PAGE, "userlistmodel", usersList);
	}
	
	@RequestMapping(value="/registerForm")
	public String registrationForm() {
		logger.info("Registration form accessed...");
		return REGISTER_FORM;
	}
	
	@PreAuthorize("@customAuthorizationUtil.isAuthorize(authentication, 'EDIT')")
	@RequestMapping(value = "/updateForm")
	public ModelAndView updateUserForm() {
		logger.info("User updataion form accessed...Fetching users to update.");
		
		List<String> updatableUsersList = userService.getUpdatableUsersList();
		logger.info("List of users for update : " + updatableUsersList);
		return new ModelAndView( UPDATE_FORM, "updatableUsersList", updatableUsersList );
	}
	
	@RequestMapping(value="/successlogin")
	public ModelAndView login() {
		logger.info("User logged in successfully." + VIEW_ACCESS);
		ModelAndView model = new ModelAndView();
		List<String> deleteUsersList = userService.getUpdatableUsersList();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    CurrentUser currentUser = (CurrentUser) auth.getPrincipal();
	    Set<Privilege> privilegesSet = new HashSet<Privilege>(currentUser.getRole().getPrivileges());

	    model.setViewName(HOME_PAGE);
	    model.addObject("deleteUsersList", deleteUsersList);
	    model.addObject("privileges", privilegesSet);
	    model.addObject("userid", currentUser.getUserId());
	    model.addObject("username", currentUser.getUsername());
	    logger.info("List of users for update : " + deleteUsersList);
		//return new ModelAndView( "customhome", "deleteUsersList", deleteUsersList );
		//return new ModelAndView(HOME_PAGE, "privileges", privilegesSet);
	    return model;
	}
}
