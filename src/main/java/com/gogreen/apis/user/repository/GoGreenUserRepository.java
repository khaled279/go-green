package com.gogreen.apis.user.repository;

import com.gogreen.models.user.dtos.UserSearchDto;
import com.gogreen.models.user.entities.VendorUserWrapper;
import com.gogreen.models.auth.entities.GoGreenUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface GoGreenUserRepository extends JpaRepository<GoGreenUserEntity, Long>,
		JpaSpecificationExecutor<GoGreenUserEntity> {

	Optional<GoGreenUserEntity> findByEmailAndDeletedIsFalse(String email);

	@Query("SELECT NEW com.gogreen.apis.user.repository.GoGreenCommunityUserWrapper(u , cu) from GoGreenUserEntity u  JOIN CommunityUserEntity cu ON cu.id = u.userDetailsId WHERE u.id = :id AND u.userTypeEnum= 'COMMUNITY' ")
	GoGreenCommunityUserWrapper findByIdAndDeletedFalse(@Param("id") Long id);

	@Query("SELECT NEW com.gogreen.apis.user.repository.GoGreenCommunityUserWrapper(u , cu)  from GoGreenUserEntity u  JOIN   CommunityUserEntity cu ON cu.id = u.userDetailsId WHERE u.userTypeEnum= 'COMMUNITY' AND  ( :email = null OR u.email LIKE :email) AND ( :name = null OR u.name LIKE :name)   AND (:points = null OR :points > cu.points )   AND (:id =null OR :id = u.id )")
	Page<GoGreenCommunityUserWrapper> listAll(@Param("id") Long id,@Param("points") BigDecimal points,@Param("email") String email,
			String name, Pageable pageable);

	@Query("SELECT NEW com.gogreen.models.user.entities.VendorUserWrapper(u , vu) from GoGreenUserEntity u  JOIN VendorUserEntity vu ON vu.id = u.userDetailsId WHERE vu.vendorId = :id  AND u.userTypeEnum= 'VENDOR'")
	Page<VendorUserWrapper> findByVendorUser(@Param("id") Long idm, Pageable pageable);
}
