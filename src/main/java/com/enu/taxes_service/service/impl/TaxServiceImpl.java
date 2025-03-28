package com.enu.taxes_service.service.impl;

import com.enu.taxes_service.domain.model.Income;
import com.enu.taxes_service.domain.model.Tax;
import com.enu.taxes_service.domain.model.User;
import com.enu.taxes_service.repository.IncomeRepository;
import com.enu.taxes_service.repository.TaxRepository;
import com.enu.taxes_service.repository.UserRepository;
import com.enu.taxes_service.service.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaxServiceImpl implements TaxService {
    private final TaxRepository taxRepository;
    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    @Override
    public List<Tax> calculateTaxes(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        List<Income> incomes = incomeRepository.findIncomesByUserId(user.getId());
        double totalTax = 0;
        for (Income income : incomes) {
            if(taxRepository.existsById(income.getId())) {
                continue;
            }
            double taxRate = income.getType().getTaxRate();
            double taxAmount = income.getAmount() * taxRate;
            Tax tax = new Tax(null, income.getUser(), taxAmount,income.getId(), "Tax for " + income.getType(), LocalDateTime.now());
            taxRepository.save(tax);
            totalTax += taxAmount;
        }
        return taxRepository.findByUserId(user.getId());
    }
}
