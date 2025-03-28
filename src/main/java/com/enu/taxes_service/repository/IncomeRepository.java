package com.enu.taxes_service.repository;

import com.enu.taxes_service.domain.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findIncomesByUserId(Long userId);
}
