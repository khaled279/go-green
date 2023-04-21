package com.gogreen.apis.admin.vendor.user.repository;

import com.gogreen.models.user.entities.VendorUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorUserRepository extends JpaRepository<VendorUserEntity, Long> {
	Optional<VendorUserEntity> findByIdAndDeletedFalseAndEnabledTrue(@Param("id") Long id);
}
