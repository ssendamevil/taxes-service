package com.enu.taxes_service.repository;

import com.enu.taxes_service.domain.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxRepository extends JpaRepository<Tax, Long> {
    List<Tax> findByUserId(Long userId);

    Boolean existsByIncomeId(Long incomeId);
}
