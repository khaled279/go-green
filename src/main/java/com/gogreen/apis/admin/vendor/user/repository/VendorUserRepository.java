package com.gogreen.apis.admin.vendor.user.repository;

import com.gogreen.models.user.entities.VendorUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorUserRepository extends JpaRepository<VendorUserEntity, Long> {
}
