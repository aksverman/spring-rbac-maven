package com.aks.security.rback.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aks.security.rback.exception.CustomException;
import com.aks.security.rback.model.UserBO;
import com.aks.security.rback.model.UserDTO;
import com.aks.security.rback.service.UserService;
import com.aks.security.rback.util.ModelConverter;

@RestController
@RequestMapping("/rest-user")
public class UserRestController {

	private static Logger logger = Logger.getLogger(UserRestController.class);
	
	@Autowired
	private UserService		userService;
	
	@Autowired
	private ModelConverter	modelConverter;
	
	@Autowired
	private MessageSource		messageSource;
	
	@PreAuthorize("@customAuthorizationUtil.isAuthorize(authentication, 'VIEW')")
	@RequestMapping(path = "/", method = RequestMethod.GET, produces = "applcation/json")
	public ResponseEntity<?>	getAllUsers() {
		List<UserDTO> usersList = modelConverter.convertTo(userService.usersList());
		if(usersList == null )
			return new ResponseEntity<String>( messageSource.getMessage("E0001", new Object[] {}, LocaleContextHolder.getLocale()), HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Object>(usersList, HttpStatus.OK);
	}
	
	@RequestMapping( value="/addUser", method = RequestMethod.POST) 
	public ResponseEntity<?>	addUser( @RequestBody UserBO user, BindingResult bindingResult) {
		logger.info("Adding new user : " + user);
		if( bindingResult.hasErrors())
			return new ResponseEntity<>("user form incorrect", HttpStatus.BAD_REQUEST);
			
		boolean isUserCreated = false;
		if(user != null)
			isUserCreated = userService.saveUser(user);
		logger.info("User added : " + isUserCreated);
		if(!isUserCreated) 
			return new ResponseEntity<>("user not added", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>("user added" + user.getUsername(), HttpStatus.BAD_REQUEST);
	} 
	
	@PreAuthorize("@customAuthorizationUtil.isAuthorize(authentication, 'VIEW')")
	@RequestMapping( value = "/getUser/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<UserDTO>	getUserById( @PathVariable("id") int id) {
		UserDTO user = modelConverter.converUserToDto(userService.findUserById(id));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PreAuthorize("@customAuthorizationUtil.isAuthorize(authentication, 'UPDATE')")
	@RequestMapping( value = "/updateUser", method = RequestMethod.PUT)
	public ResponseEntity<?>	updateUser( @RequestBody UserBO user, BindingResult	bindinResult) {
		UserDTO userdto = null;
		try {
			userdto = modelConverter.converUserToDto(userService.updateUser(user));
		} catch (Exception e) {
			if ( e instanceof CustomException)
				new ResponseEntity<>(messageSource.getMessage
						(((CustomException) e).getErrorCode(), new Object[] { user.getUsername(), user.getEmailid() }, LocaleContextHolder.getLocale()), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(userdto, HttpStatus.OK);
	}
	
	@PreAuthorize("@customAuthorizationUtil.isAuthorize(authentication, 'DELETE')")
	@RequestMapping( value = "/deleteUser/{name}", method = RequestMethod.DELETE)
	public	 ResponseEntity<?>	deleteUserByUserName(@PathVariable ("name") String username) {
		try {
			if ( !userService.deleteUser(username))
				return new ResponseEntity<>("User not deleted with username : " + username, HttpStatus.BAD_REQUEST);
		} catch( Exception ex) {
			if ( ex instanceof CustomException)
				new ResponseEntity<>(messageSource.getMessage
						(((CustomException) ex).getErrorCode(), new Object[] { username }, LocaleContextHolder.getLocale()), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>("User deleted with username : " + username, HttpStatus.OK);
	}
	
}
