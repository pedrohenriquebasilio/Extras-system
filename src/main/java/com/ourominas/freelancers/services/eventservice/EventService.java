package com.ourominas.freelancers.services.eventservice;

import com.ourominas.freelancers.domain.Event;
import com.ourominas.freelancers.domain.Extra;
import com.ourominas.freelancers.domain.dto.request.EventRequestDTO;
import com.ourominas.freelancers.domain.dto.response.EventResponseDTO;
import com.ourominas.freelancers.infrastructure.exceptions.EventNotFoundException;
import com.ourominas.freelancers.infrastructure.exceptions.ExtraNaoDisponivelException;
import com.ourominas.freelancers.infrastructure.exceptions.ExtraNotFoundException;
import com.ourominas.freelancers.infrastructure.exceptions.GlobalExceptionHandler;
import com.ourominas.freelancers.repositories.EventRepository;
import com.ourominas.freelancers.repositories.ExtraRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Autowired
    private GlobalExceptionHandler exceptionHandler;

    public List<Event> listarEventos() {
        return eventRepository.findAll();
    }

    public Event buscarEventoPorId(UUID eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Evento não encontrado com ID: " + eventId));
    }

    public EventResponseDTO criarEvento(EventRequestDTO dto) {
        Event event = new Event();
        event.setTitle(dto.title());
        event.setDate(dto.date());
        event.setDescription(dto.description());
        event.setStartDate(dto.StartDate());
        event.setEndDate(dto.endDate());

        Event saveEvent = eventRepository.save(event);
        return new EventResponseDTO(
                saveEvent.getId(),
                saveEvent.getTitle(),
                saveEvent.getDate(),
                saveEvent.getStartDate(),
                saveEvent.getEndDate(),
                saveEvent.getDescription(),
                LocalDateTime.now()
        );
    }

    public Event atualizarEvento(UUID eventId, Event eventoAtualizado) {
        Event existente = buscarEventoPorId(eventId);

        existente.setTitle(eventoAtualizado.getTitle());
        existente.setDescription(eventoAtualizado.getDescription());
        existente.setDate(eventoAtualizado.getDate());
        existente.setStartDate(eventoAtualizado.getStartDate());
        existente.setEndDate(eventoAtualizado.getEndDate());

        return eventRepository.save(existente);
    }

    public void deletarEvento(UUID eventId) {
        Event existente = buscarEventoPorId(eventId);
        eventRepository.delete(existente);
    }

    @Transactional
    public void adicionarExtraNoEvento(UUID eventId, UUID extraId) {
        Event evento = buscarEventoPorId(eventId);
        Extra extra = extraRepository.findById(extraId)
                .orElseThrow(() -> new ExtraNotFoundException("Extra não encontrado com ID: " + extraId));

        if (!podeTrabalharNaSemana(extra, evento.getDate())) {
            extra.setAvailable(false);
            extraRepository.save(extra);
            throw new ExtraNaoDisponivelException("Este extra já trabalhou 2 vezes nesta semana");
        }

        evento.getExtras().add(extra);
        eventRepository.save(evento);

        atualizarDisponibilidade(extra);
    }

    @Transactional
    public void removerExtraDoEvento(UUID eventId, UUID extraId) {
        Event evento = buscarEventoPorId(eventId);
        Extra extra = extraRepository.findById(extraId)
                .orElseThrow(() -> new ExtraNotFoundException("Extra não encontrado com ID: " + extraId));

        evento.getExtras().remove(extra);
        eventRepository.save(evento);

        atualizarDisponibilidade(extra);
    }

    private boolean podeTrabalharNaSemana(Extra extra, Date dataEvento) {
        LocalDate dataEventoLocal = converterParaLocalDate(dataEvento);
        int semanaDoAnoEvento = getSemanaDoAno(dataEventoLocal);
        int anoEvento = dataEventoLocal.getYear();

        long count = extra.getEvents().stream()
                .filter(e -> {
                    LocalDate data = converterParaLocalDate(e.getDate());
                    return getSemanaDoAno(data) == semanaDoAnoEvento && data.getYear() == anoEvento;
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
