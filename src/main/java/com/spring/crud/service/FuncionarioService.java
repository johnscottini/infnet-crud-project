package com.spring.crud.service;

import com.spring.crud.dto.FuncionarioDTO;
import com.spring.crud.dto.FuncionarioRequestDTO;
import com.spring.crud.entity.Funcionario;
import com.spring.crud.mapper.FuncionarioMapper;
import com.spring.crud.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioMapper funcionarioMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
    }

    public List<FuncionarioDTO> listarTodos() {
        return funcionarioRepository.findAll()
                .stream()
                .map(funcionarioMapper::toDTO)
                .toList();
    }

    public FuncionarioDTO buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + id));
        return funcionarioMapper.toDTO(funcionario);
    }

    public FuncionarioDTO salvar(FuncionarioRequestDTO dto) {
        Funcionario funcionario = funcionarioMapper.toEntity(dto);
        return funcionarioMapper.toDTO(funcionarioRepository.save(funcionario));
    }

    public FuncionarioDTO atualizar(Long id, FuncionarioRequestDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + id));

        funcionarioMapper.updateEntityFromDto(dto, funcionario);
        return funcionarioMapper.toDTO(funcionarioRepository.save(funcionario));
    }

    public void deletar(Long id) {
        funcionarioRepository.deleteById(id);
    }
}