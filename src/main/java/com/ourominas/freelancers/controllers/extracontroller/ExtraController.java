package com.ourominas.freelancers.controllers.extracontroller;


import com.ourominas.freelancers.domain.Users;
import com.ourominas.freelancers.domain.dto.request.ExtraRequestDTO;
import com.ourominas.freelancers.domain.dto.request.UserRequestDTO;
import com.ourominas.freelancers.domain.dto.response.UserResponseDTO;
import com.ourominas.freelancers.services.extraservices.Extraservices;
import com.ourominas.freelancers.services.userservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Controller
public class ExtraController {

    @Autowired
    private Extraservices extraservices;

    @GetMapping("/lista-extras")
    public List<Users> getAlluser(){
        return extraservices.getAllUsers();
    }
    @PostMapping("/cadastra-extras")
    public void addUser(@RequestBody ExtraRequestDTO ExtraRequestDTO){
        Users users = new Users();
        users.setName(ExtraRequestDTO.name());
        users.setEmail(ExtraRequestDTO.email());
        users.setRole(ExtraRequestDTO.role());
        users.setDepartment(ExtraRequestDTO.department());
        users.setPassword(ExtraRequestDTO.password());
        extraservices.addExtra(ExtraRequestDTO);
    }
    @DeleteMapping("/extras/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id) {
        extraservices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/atualiza-extras/{id}")
    public ResponseEntity<UserResponseDTO> atualizarUsuario(
            @PathVariable UUID id,
            @RequestBody UserRequestDTO dto) {

        UserResponseDTO atualizado = extraservices.atualizarUsuario(id, dto);
        return ResponseEntity.ok(atualizado);
    }
}
