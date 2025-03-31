package com.enu.taxes_service.service.impl;

import com.enu.taxes_service.domain.dto.TaxView;
import com.enu.taxes_service.service.TaxExportService;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaxExportServiceImpl implements TaxExportService {
    public byte[] generatePdf(List<TaxView> taxes) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Tax Report"));

            for (TaxView tax : taxes) {
                document.add(new Paragraph(tax.getDescription() + ": " + tax.getAmount()));
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }
}
