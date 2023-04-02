package com.gogreen.apis.user.repository;

import com.gogreen.models.auth.entities.GoGreenUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GoGreenUserRepository extends JpaRepository<GoGreenUserEntity, Long>,
		JpaSpecificationExecutor<GoGreenUserEntity> {

	Optional<GoGreenUserEntity> findByEmailAndDeletedIsFalse(String email);

	@Query("SELECT NEW com.gogreen.apis.user.repository.GoGreenCommunityUserWrapper(u , cu) from GoGreenUserEntity u LEFT JOIN CommunityUserEntity cu ON cu.id = u.userDetailsId WHERE u.id = :id ")
	GoGreenCommunityUserWrapper findByIdAndDeletedFalse(Long id);
}
