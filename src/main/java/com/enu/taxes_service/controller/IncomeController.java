package com.enu.taxes_service.controller;

import com.enu.taxes_service.domain.dto.IncomeDto;
import com.enu.taxes_service.domain.dto.IncomeView;
import com.enu.taxes_service.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
public class IncomeController {
    private final IncomeService incomeService;

    //Ендпоинт для добавление дохода
    @PostMapping("/{username}")
    public IncomeView addIncome(@RequestBody IncomeDto income,
                                @PathVariable String username) {
        return incomeService.addIncome(income, username);
    }

    //Ендпоинт для получения всех доходов по username
    @GetMapping("/{username}")
    public List<IncomeView> getAllIncomeByUsername(@PathVariable String username){
        return incomeService.getAllByUsername(username);
    }
}
