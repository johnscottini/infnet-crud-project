package com.spring.crud.controller;

import com.spring.crud.dto.FuncionarioDTO;
import com.spring.crud.dto.FuncionarioRequestDTO;
import com.spring.crud.service.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listarTodos() {
        return ResponseEntity.ok(funcionarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(funcionarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> salvar(@RequestBody FuncionarioRequestDTO dto) {
        return ResponseEntity.ok(funcionarioService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> atualizar(@PathVariable Long id, @RequestBody FuncionarioRequestDTO dto) {
        return ResponseEntity.ok(funcionarioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}