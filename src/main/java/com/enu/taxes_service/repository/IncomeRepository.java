package com.enu.taxes_service.repository;

import com.enu.taxes_service.domain.IncomeType;
import com.enu.taxes_service.domain.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findIncomesByUserId(Long userId);

    List<Income> findAllByUserIdAndType(Long userId, IncomeType type);
}
