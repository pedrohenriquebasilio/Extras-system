package com.ourominas.freelancers.services.userservices;


import com.ourominas.freelancers.domain.Users;
import com.ourominas.freelancers.domain.dto.request.UserRequestDTO;
import com.ourominas.freelancers.domain.dto.response.UserResponseDTO;
import com.ourominas.freelancers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<Users> getAllUsers(){
        return repository.findAll();
    }

    public Users addUsers(Users users){
        return repository.save(users);
    }

    public void deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }

    public UserResponseDTO atualizarUsuario(UUID id, UserRequestDTO dto) {
        Users user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setRole(dto.role());
        user.setDepartment(dto.department());


        Users salvo = repository.save(user);

        return new UserResponseDTO(
                salvo.getName(),
                salvo.getEmail(),
                salvo.getDepartment(),
                salvo.getRole()
        );
    }

    //PREAUTHORIZE
    public UserResponseDTO atualizaSenhaUsuario(UUID id, UserRequestDTO dto){
        Users user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        user.setPassword(dto.password());

        Users salvo = repository.save(user);

        return new UserResponseDTO(
                salvo.getName(),
                salvo.getEmail(),
                salvo.getDepartment(),
                salvo.getRole()
        );

    }



}
