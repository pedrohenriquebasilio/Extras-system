package com.ourominas.freelancers.controllers.eventcontroller;

import com.ourominas.freelancers.domain.Event;
import com.ourominas.freelancers.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;


    @GetMapping
    public ResponseEntity<List<Event>> listarEventos() {
        return ResponseEntity.ok(eventService.listarEventos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> buscarEventoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(eventService.buscarEventoPorId(id));
    }

    @PostMapping
    public ResponseEntity<Event> criarEvento(@RequestBody Event evento) {
        return ResponseEntity.ok(eventService.criarEvento(evento));
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
}
