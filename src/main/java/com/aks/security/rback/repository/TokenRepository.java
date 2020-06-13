package com.aks.security.rback.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aks.security.rback.model.PasswordResetToken;

public interface TokenRepository extends JpaRepository<PasswordResetToken, Serializable>{

	Optional<PasswordResetToken> findByToken(String token);

}
