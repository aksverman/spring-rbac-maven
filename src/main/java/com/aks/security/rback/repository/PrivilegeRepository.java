package com.aks.security.rback.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aks.security.rback.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Serializable>{

}
