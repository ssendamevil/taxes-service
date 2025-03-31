package com.enu.taxes_service.controller;

import com.enu.taxes_service.domain.TaxType;
import com.enu.taxes_service.domain.dto.TaxFilter;
import com.enu.taxes_service.domain.dto.TaxView;

import com.enu.taxes_service.service.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/taxes")
@RequiredArgsConstructor
public class TaxController {
    private final TaxService taxService;

    @GetMapping("/calculate/{username}")
    public List<TaxView> calculateTax(@PathVariable String username) {
        return taxService.calculateTaxes(username);
    }

    @GetMapping("/{username}")
    public List<TaxView> getTaxes(@PathVariable String username,
                                  @RequestParam(required = false)TaxType type,
                                  @RequestParam(required = false)LocalDateTime from,
                                  @RequestParam(required = false)LocalDateTime to,
                                  @RequestParam(required = false)Double min,
                                  @RequestParam(required = false)Double max){
        TaxFilter taxFilter = TaxFilter.builder()
                .from(from)
                .type(type)
                .to(to)
                .max(max)
                .min(min)
                .build();
        return taxService.getTaxesByFilters(username, taxFilter);
    }
}
