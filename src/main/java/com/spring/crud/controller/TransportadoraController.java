package com.spring.crud.controller;

import com.spring.crud.dto.TransportadoraDTO;
import com.spring.crud.dto.TransportadoraRequestDTO;
import com.spring.crud.service.TransportadoraService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transportadoras")
public class TransportadoraController {

    private final TransportadoraService transportadoraService;

    public TransportadoraController(TransportadoraService transportadoraService) {
        this.transportadoraService = transportadoraService;
    }

    @GetMapping
    public ResponseEntity<List<TransportadoraDTO>> listarTodos() {
        return ResponseEntity.ok(transportadoraService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportadoraDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(transportadoraService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TransportadoraDTO> salvar(@Valid @RequestBody TransportadoraRequestDTO dto) {
        return ResponseEntity.ok(transportadoraService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransportadoraDTO> atualizar(@PathVariable Long id, @Valid @RequestBody TransportadoraRequestDTO dto) {
        return ResponseEntity.ok(transportadoraService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        transportadoraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}