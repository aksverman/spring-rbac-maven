package com.aks.security.rback.service;

import java.util.List;
import java.util.Optional;

import com.aks.security.rback.exception.CustomException;
import com.aks.security.rback.model.UserBO;

public interface UserService {

	Optional<UserBO> findUserByUserName(String username);
	
	UserBO findUserById(int userid);

	List<UserBO>	usersList();
	
	boolean	saveUser(UserBO user);
	
	UserBO	updateUser(UserBO user) throws CustomException;
	
	boolean deleteUser(String username) throws Exception;
	
	List<String>	getUpdatableUsersList();

	boolean resetPassword(String username, String contextPath);

	String validatePasswordChangeRequest(int userid, String token);

	String updatePassword(int userid, String pass1);

}
