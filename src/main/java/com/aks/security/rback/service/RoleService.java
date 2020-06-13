package com.aks.security.rback.service;

import java.util.Optional;

import com.aks.security.rback.model.Role;

public interface RoleService {

	boolean 	addRole(Role role);
	Optional<Role> 		findRoleWithRoleName(String roleName);
	Role		findRoleById(int roleid);
}
