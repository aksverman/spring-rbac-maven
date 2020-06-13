package com.aks.security.rback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aks.security.rback.model.Privilege;
import com.aks.security.rback.repository.PrivilegeRepository;
import com.aks.security.rback.service.PrivilegeService;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

	@Autowired
	private PrivilegeRepository		privilegeRepository;

	@Override
	public boolean addPrivilege(Privilege privilege) {
		Privilege newPri = privilegeRepository.save(privilege);
		
		return newPri != null;
	}
	
	
}
