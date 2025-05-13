package com.ourominas.freelancers.services.eventReportService;


import com.ourominas.freelancers.domain.Event;
import com.ourominas.freelancers.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class EventReportService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    public void gerarRelatorioPresencaDiaria(LocalDate data) {

        LocalDateTime startOfDay = data.atStartOfDay();
        LocalDateTime endOfDay = data.atTime(LocalTime.MAX);

        Date startDate = Date.from(startOfDay.atZone(ZoneId.of("UTC")).toInstant());
        Date endDate = Date.from(endOfDay.atZone(ZoneId.of("UTC")).toInstant());

        List<Event> eventosDoDia = eventRepository.findByDateBetween(startDate, endDate);

        if (eventosDoDia.isEmpty()) {
            throw new RuntimeException("Nenhum evento encontrado para o dia: " + data);
        }

        pdfGeneratorService.gerarPdf(eventosDoDia, data); // ainda vamos implementar
    }
}
