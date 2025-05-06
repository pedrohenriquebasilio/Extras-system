package com.ourominas.freelancers.controllers.extracontroller;


import com.ourominas.freelancers.domain.Extra;
import com.ourominas.freelancers.domain.Users;
import com.ourominas.freelancers.domain.dto.request.ExtraRequestDTO;
import com.ourominas.freelancers.domain.dto.request.UserRequestDTO;
import com.ourominas.freelancers.domain.dto.response.ExtraResponseDTO;
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
    public List<Extra> getAlluser(){
        return extraservices.getAllUsers();
    }

    @PostMapping("/cadastra-extras")
    public void addUser(@RequestBody ExtraRequestDTO ExtraRequestDTO){
        extraservices.addExtra(ExtraRequestDTO);
    }

    @DeleteMapping("/extras/{id}")
    public ResponseEntity<Void> deletarExtra(@PathVariable UUID id) {
        extraservices.DeleteByid(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/atualiza-extras/{id}")
    public ResponseEntity<ExtraResponseDTO> atualizarExtra(
            @PathVariable UUID id,
            @RequestBody ExtraRequestDTO dto) {

        ExtraResponseDTO atualizado = extraservices.atualizarUsuario(id, dto);
        return ResponseEntity.ok(atualizado);
    }
}
