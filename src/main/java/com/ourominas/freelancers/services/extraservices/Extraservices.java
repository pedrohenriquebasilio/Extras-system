package com.ourominas.freelancers.services.extraservices;


import com.ourominas.freelancers.domain.Extra;
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



}
