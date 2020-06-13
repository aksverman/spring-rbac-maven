package com.aks.security.rback.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aks.security.rback.model.Role;

public interface RoleRepository extends JpaRepository<Role, Serializable>{

	Optional<Role>	findRoleByName(String name);
}
