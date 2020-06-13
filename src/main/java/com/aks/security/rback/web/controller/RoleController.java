package com.aks.security.rback.web.controller;

import static com.aks.security.rback.constants.Constants.ROLE_FORM;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aks.security.rback.model.Role;
import com.aks.security.rback.service.impl.RoleServiceImpl;


@Controller
@RequestMapping( "/role" )	
public class RoleController {

	@Autowired
	private RoleServiceImpl		roleService;
	
	private static Logger logger = Logger.getLogger(RoleController.class);
	
	@RequestMapping("/roleform")
	public String getRoleForm() {
		return ROLE_FORM;
	}
	
	@RequestMapping("/addRole")
	public String		addRole(@ModelAttribute("roleform") Role role) {
		logger.info("Start : " + getClass().getName() + " : addRole()");
		boolean roleAdded = false;
		if ( role != null) 
			roleAdded = roleService.addRole(role);
		logger.info("Roles added : " + roleAdded);
		if(roleAdded)
			return "redirect:/home";
		return "redirect:/index";
	}
}
