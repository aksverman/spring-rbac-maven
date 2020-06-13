package com.aks.security.rback.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aks.security.rback.model.Role;
import com.aks.security.rback.repository.RoleRepository;
import com.aks.security.rback.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository		roleRepository;
	
	@Override
	public boolean addRole(Role role) {
		Role newRole = roleRepository.save(role);
		return newRole != null;
	}

	@Override
	public Optional<Role> findRoleWithRoleName(String roleName) {
		return roleRepository.findRoleByName(roleName);
	}

	@Override
	public Role findRoleById(int roleid) {
		return roleRepository.findOne(roleid);
	}
}
