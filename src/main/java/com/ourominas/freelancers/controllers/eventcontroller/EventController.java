package com.ourominas.freelancers.controllers.eventcontroller;

import com.ourominas.freelancers.domain.Event;
import com.ourominas.freelancers.domain.dto.request.EventRequestDTO;
import com.ourominas.freelancers.domain.dto.response.EventResponseDTO;
import com.ourominas.freelancers.domain.dto.response.ExtraResponseDTO;

import com.ourominas.freelancers.services.eventReportService.EventReportService;
import com.ourominas.freelancers.services.eventservice.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventController {

    @Autowired
    private  EventService eventService;

    @Autowired
    private EventReportService eventReportService;


    @GetMapping
    public ResponseEntity<List<Event>> listarEventos() {
        return ResponseEntity.ok(eventService.listarEventos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> buscarEventoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(eventService.buscarEventoPorId(id));
    }

    @PostMapping
    public ResponseEntity<EventResponseDTO> criarEvento(@RequestBody EventRequestDTO requestDTO) {
        EventResponseDTO createdEvent = eventService.criarEvento(requestDTO);
        return ResponseEntity.ok(createdEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> atualizarEvento(@PathVariable UUID id, @RequestBody Event evento) {
        return ResponseEntity.ok(eventService.atualizarEvento(id, evento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable UUID id) {
        eventService.deletarEvento(id);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/{eventId}/extras/{extraId}")
    public ResponseEntity<Void> adicionarExtraNoEvento(@PathVariable UUID eventId, @PathVariable UUID extraId) {
        eventService.adicionarExtraNoEvento(eventId, extraId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{eventId}/extras/{extraId}")
    public ResponseEntity<Void> removerExtraDoEvento(@PathVariable UUID eventId, @PathVariable UUID extraId) {
        eventService.removerExtraDoEvento(eventId, extraId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/gerar-relatorio")
    public ResponseEntity<String> gerarRelatorio(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        eventReportService.gerarRelatorioPresencaDiaria(data);
        return ResponseEntity.ok("Relatório gerado com sucesso!");
    }

}
