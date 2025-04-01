package com.enu.taxes_service.service.impl;

import com.enu.taxes_service.domain.dto.TaxView;
import com.enu.taxes_service.service.TaxExportService;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

            // Title - centered, bold font, large size
            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            PdfPTable titleTable = new PdfPTable(1);
            PdfPCell titleCell = new PdfPCell(new Phrase("Отчет по налогам", titleFont));
            titleCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            titleCell.setBorder(0);
            titleTable.addCell(titleCell);
            document.add(titleTable);

            // Add some space before the table
            document.add(new Phrase(" "));

            // Creating the table with 3 columns: Description, Amount, Date
            PdfPTable table = new PdfPTable(3); // 3 columns
            table.setWidthPercentage(100);

            // Add table headers
            PdfPCell cell = new PdfPCell(new Phrase("Описание"));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Сумма"));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Дата добавления"));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(cell);

            // Decimal format for amounts
            DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
            // Date format for tax date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Add rows for each tax record
            double totalAmount = 0.0;
            for (TaxView tax : taxes) {
                table.addCell(tax.getDescription());
                table.addCell(decimalFormat.format(tax.getAmount()) + " ₸");
                table.addCell(tax.getDate().toString());
                totalAmount += tax.getAmount();
            }

            // Add the table to the document
            document.add(table);

            // Add some space before the summary
            document.add(new Phrase(" "));

            // Add a summary section at the end (total amount and date)
            Font summaryFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            PdfPTable summaryTable = new PdfPTable(1);
            PdfPCell summaryTitleCell = new PdfPCell(new Phrase("Итоги", summaryFont));
            summaryTitleCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            summaryTitleCell.setBorder(0);
            summaryTable.addCell(summaryTitleCell);

            // Add the total tax amount and date
            PdfPCell totalAmountCell = new PdfPCell(new Phrase("Общая сумма налогов: " + decimalFormat.format(totalAmount).concat(" ₸")));
            totalAmountCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            totalAmountCell.setBorder(0);
            summaryTable.addCell(totalAmountCell);

            PdfPCell dateCell = new PdfPCell(new Phrase("Дата: " + LocalDate.now().toString()));
            dateCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            dateCell.setBorder(0);
            summaryTable.addCell(dateCell);

            document.add(summaryTable);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }
}
