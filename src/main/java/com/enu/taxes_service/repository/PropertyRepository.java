package com.enu.taxes_service.repository;

import com.enu.taxes_service.domain.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findAllByUserId(Long userId);

    List<Property> findByUserId(Long userId);
}
