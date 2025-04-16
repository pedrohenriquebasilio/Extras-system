package com.ourominas.freelancers.services.extraservices;


import com.ourominas.freelancers.domain.Extra;
import com.ourominas.freelancers.domain.Users;
import com.ourominas.freelancers.domain.dto.request.ExtraRequestDTO;
import com.ourominas.freelancers.domain.dto.request.UserRequestDTO;
import com.ourominas.freelancers.domain.dto.response.ExtraResponseDTO;
import com.ourominas.freelancers.domain.dto.response.UserResponseDTO;
import com.ourominas.freelancers.repositories.ExtraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class Extraservices {

    private ExtraRepository repository;

    public List<Extra> getAllUsers(){
        return repository.findAll();
    }

    public Extra addExtra(Extra extra){
        return repository.save(extra);
    }

    public void DeleteByid(UUID id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Usuario não encontrado");
        }
        repository.deleteById(id);
    }

    public ExtraResponseDTO atualizarUsuario(UUID id, ExtraRequestDTO dto) {
        Extra extra = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        extra.setName(dto.name());
        extra.setEmail(dto.email());
        extra.setRg(dto.rg());
        extra.setPis(dto.pis());
        extra.setSefip(dto.Sefip());
        extra.setESocial(dto.esocial());
        extra.setTelefone(dto.telefone());
        extra.setSindicate(dto.sindicate());
        extra.setDateBirth(dto.date_birth());


        Extra salvo = repository.save(extra);

        return new ExtraResponseDTO(
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
                salvo.isAvaliable()
        );
    }



}
