package com.gogreen.apis.user.repository;

import com.gogreen.models.user.entities.CommunityUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityUserRepository
		extends JpaRepository<CommunityUserEntity, Long> {
	Optional<CommunityUserEntity> findByIdAndDeletedFalse(@Param("id") Long id);
}
