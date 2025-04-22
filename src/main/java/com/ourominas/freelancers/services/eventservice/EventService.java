package com.ourominas.freelancers.services.eventservice;

import com.ourominas.freelancers.domain.Event;
import com.ourominas.freelancers.domain.Extra;
import com.ourominas.freelancers.domain.Users;
import com.ourominas.freelancers.domain.dto.request.EventRequestDTO;
import com.ourominas.freelancers.domain.dto.response.EventResponseDTO;
import com.ourominas.freelancers.repositories.EventRepository;
import com.ourominas.freelancers.services.extraservices.Extraservices;
import com.ourominas.freelancers.services.userservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {


    private EventRepository eventRepository;

    @Autowired
    private  final Extraservices extraservices;

    @Autowired
    private  final UserService userService;

    public EventService(EventRepository eventRepository, Extraservices extraservices, UserService userService) {
        this.eventRepository = eventRepository;
        this.extraservices = extraservices;
        this.userService = userService;
    }

    public EventResponseDTO criar(EventRequestDTO request) {
        // Valida campos não nulos
        if (Objects.isNull(request.extraId()) ||
                Objects.isNull(request.usuarioId()) ||
                Objects.isNull(request.nome()) ||
                Objects.isNull(request.dataInicio()) ||
                Objects.isNull(request.dataFim()) ||
                Objects.isNull(request.funcao()) ||
                Objects.isNull(request.preco())) {
            throw new IllegalArgumentException("Campos obrigatórios não podem ser nulos.");
        }
        verificaLimiteSemanal(request.extraId(), request.dataInicio());



        // Valida limite de 2x por semana
        LocalDateTime dataInicio = request.dataInicio();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        LocalDateTime inicioSemana = dataInicio.with(weekFields.dayOfWeek(), 1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime fimSemana = inicioSemana.plusDays(6).withHour(23).withMinute(59).withSecond(59);




        // Cria entidade Event
        Event event = new Event();
        event.setExtra(new Extra(request.extraId()));
        event.setUsuario(new Users(request.usuarioId()));
        event.setNome(request.nome());
        event.setDataInicio(request.dataInicio());
        event.setDataFim(request.dataFim());
        event.setLocal(request.local());
        event.setDescricao(request.descricao());
        event.setFuncao(request.funcao());
        event.setPreco(request.preco());

        // Salva no banco
        Event salvo = eventRepository.save(event);

        // Retorna DTO de resposta
        return new EventResponseDTO(
                salvo.getId(),
                salvo.getExtra().getId(),
                salvo.getUsuario().getId(),
                salvo.getNome(),
                salvo.getDataInicio(),
                salvo.getDataFim(),
                salvo.getLocal(),
                salvo.getDescricao(),
                salvo.getFuncao(),
                salvo.getPreco(),
                salvo.getAtivo(),
                salvo.getCriadoEm(),
                salvo.getAtualizadoEm()
        );
    }

    private void verificaLimiteSemanal(UUID extraId, LocalDateTime dataInicio) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        LocalDateTime inicioSemana = dataInicio.with(weekFields.dayOfWeek(), 1)
                .withHour(0).withMinute(0).withSecond(0);
        LocalDateTime fimSemana = inicioSemana.plusDays(6)
                .withHour(23).withMinute(59).withSecond(59);

        long trabalhosNaSemana = eventRepository.countByExtraIdAndDataInicioBetween(extraId, inicioSemana, fimSemana);

        if (trabalhosNaSemana >= 2) {
            throw new IllegalStateException("Extra já tem 2 trabalhos nesta semana.");
        }
    }

}