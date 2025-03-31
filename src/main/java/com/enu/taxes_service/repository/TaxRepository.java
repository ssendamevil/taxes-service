package com.enu.taxes_service.repository;

import com.enu.taxes_service.domain.TaxType;
import com.enu.taxes_service.domain.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {
    List<Tax> findByUserId(Long userId);

    Boolean existsByIncomeId(Long incomeId);
    Boolean existsByPropertyId(Long propertyId);

    Boolean existsByTypeAndIncomeId(TaxType type, Long incomeId);
    Boolean existsByTypeAndPropertyId(TaxType type, Long propertyId);
}
