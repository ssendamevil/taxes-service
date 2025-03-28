package com.enu.taxes_service.controller;

import com.enu.taxes_service.domain.dto.IncomeDto;
import com.enu.taxes_service.domain.dto.IncomeView;
import com.enu.taxes_service.domain.model.Income;
import com.enu.taxes_service.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
public class IncomeController {
    private final IncomeService incomeService;

    @PostMapping("/{username}")
    public IncomeView addIncome(@RequestBody IncomeDto income,
                                @PathVariable String username) {
        return incomeService.addIncome(income, username);
    }
}
