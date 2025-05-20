package com.ourominas.freelancers.controllers.usercontroller;


import com.ourominas.freelancers.domain.Users;
import com.ourominas.freelancers.domain.dto.request.UserRequestDTO;
import com.ourominas.freelancers.domain.dto.response.UserResponseDTO;
import com.ourominas.freelancers.services.userservices.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Users> get(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/cadastra-user")
    public ResponseEntity addUser(@RequestBody UserRequestDTO userRequestDTO){
        userService.addUsers(userRequestDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
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
