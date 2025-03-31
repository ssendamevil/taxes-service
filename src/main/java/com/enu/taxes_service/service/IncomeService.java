package com.enu.taxes_service.service;

import com.enu.taxes_service.domain.dto.IncomeDto;
import com.enu.taxes_service.domain.dto.IncomeView;
import com.enu.taxes_service.domain.model.Income;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IncomeService {
    IncomeView addIncome(IncomeDto incomeDto, String username);

    List<IncomeView> getAllByUsername(String username);
}
