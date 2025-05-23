package com.ourominas.freelancers.services.eventReportService;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.ourominas.freelancers.domain.Event;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Service
public class PdfGeneratorService {

    public Path gerarPdf(List<Event> eventos, LocalDate data) {
        String pastaDestino = "C:\\relatorios";
        String nomeArquivo = String.format("presenca-%s.pdf", data);
        Path caminho = Paths.get(pastaDestino, nomeArquivo);

        try {
            Files.createDirectories(caminho.getParent());

            PdfWriter writer = new PdfWriter(caminho.toString());
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Relatório de Presença DIARIO -" + data)
                    .setFontSize(16)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20));

            Table tabela = new Table(UnitValue.createPercentArray(new float[]{3, 4, 5, 5}))
                    .useAllAvailableWidth();

            tabela.addHeaderCell("Título");
            tabela.addHeaderCell("freelancers");
            tabela.addHeaderCell("Horario inicio");
            tabela.addHeaderCell("Horario termino");

            for (Event evento : eventos) {
                if (evento.getExtras().isEmpty()) {
                    tabela.addCell(evento.getTitle());
                    tabela.addCell(String.valueOf(evento.getStartDate()));
                    tabela.addCell(String.valueOf(evento.getEndDate()));
                    tabela.addCell("Sem freelancers");
                } else {
                    evento.getExtras().forEach(extra -> {
                        tabela.addCell(evento.getTitle());
                        tabela.addCell(String.valueOf(evento.getStartDate()));
                        tabela.addCell(String.valueOf(evento.getEndDate()));
                        tabela.addCell(extra.getName());
                    });
                }
            }

            document.add(tabela);
            document.close();

            return caminho;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }
}
