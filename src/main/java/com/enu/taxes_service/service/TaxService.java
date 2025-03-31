package com.enu.taxes_service.service;

import com.enu.taxes_service.domain.dto.TaxFilter;
import com.enu.taxes_service.domain.dto.TaxView;
import com.enu.taxes_service.domain.model.Tax;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaxService {
    List<TaxView> calculateTaxes(String username);
    List<TaxView> getTaxesByFilters(String username, TaxFilter taxFilter);
}
