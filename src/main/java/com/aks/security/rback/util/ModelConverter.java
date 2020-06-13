package com.aks.security.rback.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.aks.security.rback.model.UserBO;
import com.aks.security.rback.model.UserDTO;

@Component
public class ModelConverter {

	
	private static Logger logger = Logger.getLogger(ModelConverter.class);
	
	public List<UserDTO> convertTo(List<UserBO> userlist) {
		List<UserDTO>  userdto = new ArrayList<UserDTO>( userlist.size() );
		UserDTO tmp = new UserDTO();
		for( UserBO user : userlist) {
			tmp.setUserid(user.getUserid());
			tmp.setUsername(user.getUsername());
			tmp.setEmailid(user.getEmailid());
			tmp.setRole(user.getRole().getName());
			userdto.add(tmp);
			logger.info("Adding user to dto : " + tmp);
		}
		return userdto;
	}
	
	public UserDTO  converUserToDto(UserBO userBo) {
		UserDTO	usertoreturn = new UserDTO();
		usertoreturn.setUserid(userBo.getUserid());
		usertoreturn.setUsername(userBo.getUsername());
		usertoreturn.setEmailid(userBo.getEmailid());
		usertoreturn.setRole(userBo.getRole().getName());
		return usertoreturn;
	}
}
