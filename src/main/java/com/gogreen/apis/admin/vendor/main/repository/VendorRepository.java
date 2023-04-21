package com.gogreen.apis.admin.vendor.main.repository;

import com.gogreen.models.vendor.dtos.VendorDto;
import com.gogreen.models.vendor.entities.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Long> {
	Optional<VendorEntity> findByIdAndDeletedFalse(@Param("id") Long id);
}
