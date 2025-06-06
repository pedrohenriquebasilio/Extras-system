package com.ourominas.freelancers.services.extraservices;

import com.ourominas.freelancers.domain.Extra;
import com.ourominas.freelancers.domain.dto.request.ExtraRequestDTO;
import com.ourominas.freelancers.domain.dto.response.ExtraResponseDTO;
import com.ourominas.freelancers.infrastructure.exceptions.EventNotFoundException;
import com.ourominas.freelancers.infrastructure.exceptions.ExtraNotFoundException;
import com.ourominas.freelancers.repositories.ExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class Extraservices {

    @Autowired
    private ExtraRepository repository;

    public List<ExtraResponseDTO> getAllExtras() {
        return repository.findAll()
                .stream()
                .map(extra -> new ExtraResponseDTO(
                        extra.getId(),
                        extra.getName(),
                        extra.getCpf(),
                        extra.getRg(),
                        extra.getPis(),
                        extra.getDateBirth(),
                        extra.getEmail(),
                        extra.getTelefone(),
                        extra.getESocial(),
                        extra.getSefip(),
                        extra.getSindicate(),
                        extra.isAvailable()
                ))
                .collect(Collectors.toList());
    }

    public ExtraResponseDTO addExtra(ExtraRequestDTO dto) {
        Extra extra = new Extra();
        extra.setName(dto.name());
        extra.setEmail(dto.email());
        extra.setRg(dto.rg());
        extra.setPis(dto.pis());
        extra.setESocial(dto.esocial());
        extra.setSefip(dto.sefip());
        extra.setSindicate(dto.sindicate());
        extra.setDateBirth(dto.date_birth());
        extra.setTelefone(dto.telefone());
        extra.setCpf(dto.cpf());

        Extra saveExtra = repository.save(extra);

        return new ExtraResponseDTO(
                saveExtra.getId(),
                saveExtra.getName(),
                saveExtra.getCpf(),
                saveExtra.getRg(),
                saveExtra.getPis(),
                saveExtra.getDateBirth(),
                saveExtra.getEmail(),
                saveExtra.getTelefone(),
                saveExtra.getESocial(),
                saveExtra.getSefip(),
                saveExtra.getSindicate(),
                saveExtra.isAvailable()
        );
    }

    public void deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new ExtraNotFoundException("Extra não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    public ExtraResponseDTO atualizarUsuario(UUID id, ExtraRequestDTO dto) {
        Extra extra = repository.findById(id)
                .orElseThrow(() -> new ExtraNotFoundException("Extra não encontrado com ID: " + id));

        extra.setName(dto.name());
        extra.setEmail(dto.email());
        extra.setRg(dto.rg());
        extra.setPis(dto.pis());
        extra.setSefip(dto.sefip());
        extra.setESocial(dto.esocial());
        extra.setTelefone(dto.telefone());
        extra.setSindicate(dto.sindicate());
        extra.setDateBirth(dto.date_birth());

        Extra salvo = repository.save(extra);

        return new ExtraResponseDTO(
                salvo.getId(),
                salvo.getName(),
                salvo.getCpf(),
                salvo.getRg(),
                salvo.getPis(),
                salvo.getDateBirth(),
                salvo.getEmail(),
                salvo.getTelefone(),
                salvo.getESocial(),
                salvo.getSefip(),
                salvo.getSindicate(),
                salvo.isAvailable()
        );
    }

    public Extra buscaExtraporId(UUID extraid) {
        return repository.findById(extraid)
                .orElseThrow(() -> new EventNotFoundException("Evento não encontrado com ID: " + extraid));
    }
}
