package com.gogreen.apis.user.repository;

import com.gogreen.models.auth.entity.GoGreenUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface GoGreenUserRepository extends JpaRepository<GoGreenUserEntity, Long>,
		JpaSpecificationExecutor<GoGreenUserEntity> {

	Optional<GoGreenUserEntity> findByEmailAndDeletedIsFalse(String email);

	Optional<GoGreenUserEntity> findByIdAndDeletedFalse(Long id);
}
