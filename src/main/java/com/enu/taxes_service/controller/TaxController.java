package com.enu.taxes_service.controller;

import com.enu.taxes_service.domain.model.Tax;
import com.enu.taxes_service.service.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/taxes")
@RequiredArgsConstructor
public class TaxController {
    private final TaxService taxService;

    @GetMapping("/calculate/{username}")
    public List<Tax> calculateTax(@PathVariable String username) {
        return taxService.calculateTaxes(username);
    }
}
