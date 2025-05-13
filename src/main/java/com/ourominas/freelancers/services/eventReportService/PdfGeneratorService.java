package com.ourominas.freelancers.services.eventReportService;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.ourominas.freelancers.domain.Event;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;



@Service
public class PdfGeneratorService {

    public void gerarPdf(List<Event> eventos, LocalDate data) {
        String pastaDestino = "C:\\relatorios";
        String nomeArquivo = String.format("presenca-%s.pdf", data.toString());
        Path caminho = Paths.get(pastaDestino, nomeArquivo);

        try {

            Files.createDirectories(caminho.getParent());


            PdfWriter writer = new PdfWriter(caminho.toString());
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);


            document.add(new Paragraph("Relatório de Presença - " + data.toString())
                    .setFontSize(16)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20));


            Table tabela = new Table(UnitValue.createPercentArray(new float[]{3, 7}))
                    .useAllAvailableWidth();

            tabela.addHeaderCell("ID");
            tabela.addHeaderCell("Título");

            for (Event evento : eventos) {
                tabela.addCell(evento.getId().toString());
                tabela.addCell(evento.getTitle());
            }

            document.add(tabela);
            document.close();

            System.out.println("PDF gerado em: " + caminho);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }
}
