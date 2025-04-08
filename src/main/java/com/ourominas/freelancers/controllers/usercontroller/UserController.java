package com.ourominas.freelancers.controllers.usercontroller;


import com.ourominas.freelancers.domain.Users;
import com.ourominas.freelancers.domain.dto.request.UserRequestDTO;
import com.ourominas.freelancers.domain.dto.response.UserResponseDTO;
import com.ourominas.freelancers.services.userservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/lista-users")
    public List<Users> getAlluser(){
        return userService.getAllUsers();
    }

    @PostMapping("/cadastra-user")
    public void addUser(@RequestBody UserRequestDTO userRequestDTO){
        Users users = new Users();
        users.setName(userRequestDTO.name());
        users.setEmail(userRequestDTO.email());
        users.setRole(userRequestDTO.role());
        users.setDepartment(userRequestDTO.department());
        users.setPassword(userRequestDTO.password());
        userService.addUsers(users);
    }
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualiza-user/{id}")
    public ResponseEntity<UserResponseDTO> atualizarUsuario(
            @PathVariable UUID id,
            @RequestBody UserRequestDTO dto) {

        UserResponseDTO atualizado = userService.atualizarUsuario(id, dto);
        return ResponseEntity.ok(atualizado);
    }


}
