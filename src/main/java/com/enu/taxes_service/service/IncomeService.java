package com.enu.taxes_service.service;

import com.enu.taxes_service.domain.dto.IncomeDto;
import com.enu.taxes_service.domain.dto.IncomeView;
import com.enu.taxes_service.domain.model.Income;
import org.springframework.stereotype.Service;

@Service
public interface IncomeService {
    IncomeView addIncome(IncomeDto incomeDto, String username);
}
