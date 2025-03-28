package com.enu.taxes_service.controller;

import com.enu.taxes_service.service.TaxExportService;
import com.enu.taxes_service.service.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/export")
@RequiredArgsConstructor
public class ExportController {
    private final TaxExportService exportService;
    private final TaxService taxService;

    @GetMapping("/pdf/{username}")
    public ResponseEntity<byte[]> exportPdf(@PathVariable String username) {
        byte[] pdf = exportService.generatePdf(taxService.calculateTaxes(username));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tax_report.pdf")
                .body(pdf);
    }
}
