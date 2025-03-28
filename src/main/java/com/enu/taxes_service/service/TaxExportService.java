package com.enu.taxes_service.service;

import com.enu.taxes_service.domain.model.Tax;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaxExportService {
    byte[] generatePdf(List<Tax> taxes);
}
