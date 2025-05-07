package com.ourominas.freelancers.service;

import com.ourominas.freelancers.domain.Event;
import com.ourominas.freelancers.domain.Extra;
import com.ourominas.freelancers.repositories.EventRepository;
import com.ourominas.freelancers.repositories.ExtraRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EventService {

    @Autowired
    private final EventRepository eventRepository;
    @Autowired
    private final ExtraRepository extraRepository;

    // ----- CRUD básico -----

    public List<Event> listarEventos() {
        return eventRepository.findAll();
    }

    public Event buscarEventoPorId(UUID eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    public Event criarEvento(Event evento) {
        return eventRepository.save(evento);
    }

    public Event atualizarEvento(UUID eventId, Event eventoAtualizado) {
        Event existente = buscarEventoPorId(eventId);

        existente.setTitle(eventoAtualizado.getTitle());
        existente.setDescription(eventoAtualizado.getDescription());
        existente.setDate(eventoAtualizado.getDate());

        return eventRepository.save(existente);
    }

    public void deletarEvento(UUID eventId) {
        Event existente = buscarEventoPorId(eventId);
        eventRepository.delete(existente);
    }

    // ----- Adicionar Extra com validação -----

    @Transactional
    public void adicionarExtraNoEvento(UUID eventId, UUID extraId) {
        Event evento = buscarEventoPorId(eventId);
        Extra extra = extraRepository.findById(extraId)
                .orElseThrow(() -> new RuntimeException("Extra não encontrado"));

        if (!podeTrabalharNaSemana(extra, evento.getDate())) {
            extra.setAvailable(false);
            extraRepository.save(extra);
            throw new RuntimeException("Este extra já trabalhou 2 vezes nesta semana");
        }

        evento.getExtras().add(extra);
        eventRepository.save(evento);

        atualizarDisponibilidade(extra);
    }

    // ----- Remover Extra do Evento -----

    @Transactional
    public void removerExtraDoEvento(UUID eventId, UUID extraId) {
        Event evento = buscarEventoPorId(eventId);
        Extra extra = extraRepository.findById(extraId)
                .orElseThrow(() -> new RuntimeException("Extra não encontrado"));

        evento.getExtras().remove(extra);
        eventRepository.save(evento);

        atualizarDisponibilidade(extra);
    }

    // ----- Regras de negócio -----

    private boolean podeTrabalharNaSemana(Extra extra, Date dataEvento) {
        LocalDate dataEventoLocal = converterParaLocalDate(dataEvento);
        int semanaDoAnoEvento = getSemanaDoAno(dataEventoLocal);
        int anoEvento = dataEventoLocal.getYear();

        long count = extra.getEvents().stream()
                .filter(e -> {
                    LocalDate data = converterParaLocalDate(e.getDate());
                    return getSemanaDoAno(data) == semanaDoAnoEvento
                            && data.getYear() == anoEvento;
                })
                .count();

        return count < 2;
    }

    private void atualizarDisponibilidade(Extra extra) {
        long totalEventos = extra.getEvents().size();
        extra.setAvailable(totalEventos < 2);
        extraRepository.save(extra);
    }

    private LocalDate converterParaLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private int getSemanaDoAno(LocalDate date) {
        return date.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
    }
}
