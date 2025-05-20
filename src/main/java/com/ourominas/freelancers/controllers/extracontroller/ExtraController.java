package com.ourominas.freelancers.controllers.extracontroller;

import com.ourominas.freelancers.domain.Extra;
import com.ourominas.freelancers.domain.dto.request.ExtraRequestDTO;
import com.ourominas.freelancers.domain.dto.response.ExtraResponseDTO;
import com.ourominas.freelancers.services.extraservices.Extraservices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/extras")
public class ExtraController {

    private static final Logger log = LoggerFactory.getLogger(ExtraController.class);

    @Autowired
    private Extraservices extraservices;

    @GetMapping
    public ResponseEntity<List<ExtraResponseDTO>> getAllExtras() {
        log.info("Buscando todos os extras");
        List<ExtraResponseDTO> extras = extraservices.getAllExtras();
        log.info("Extras encontrados: {}", extras);
        return ResponseEntity.ok(extras);
    }

    @PostMapping
    public ResponseEntity<ExtraResponseDTO> addExtra(@RequestBody ExtraRequestDTO extraRequestDTO) {
        log.info("Cadastrando novo extra: {}", extraRequestDTO);
        ExtraResponseDTO createdExtra = extraservices.addExtra(extraRequestDTO);
        return ResponseEntity.ok(createdExtra);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExtra(@PathVariable UUID id) {
        log.info("Deletando extra com ID: {}", id);
        extraservices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExtraResponseDTO> updateExtra(
            @PathVariable UUID id,
            @RequestBody ExtraRequestDTO dto) {
        log.info("Atualizando extra com ID: {}, dados: {}", id, dto);
        ExtraResponseDTO updatedExtra = extraservices.atualizarUsuario(id, dto);
        return ResponseEntity.ok(updatedExtra);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Extra> buscarExtraPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(extraservices.buscaExtraporId(id));
    }
}