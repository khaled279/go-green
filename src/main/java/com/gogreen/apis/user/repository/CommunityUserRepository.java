package com.gogreen.apis.user.repository;

import com.gogreen.models.user.entities.CommunityUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityUserRepository
		extends JpaRepository<CommunityUserEntity, Long> {

}
