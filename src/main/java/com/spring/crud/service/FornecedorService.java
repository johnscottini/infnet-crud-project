package com.spring.crud.service;

import com.spring.crud.dto.FornecedorDTO;
import com.spring.crud.dto.FornecedorRequestDTO;
import com.spring.crud.entity.Fornecedor;
import com.spring.crud.mapper.FornecedorMapper;
import com.spring.crud.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper fornecedorMapper;

    public FornecedorService(FornecedorRepository fornecedorRepository, FornecedorMapper fornecedorMapper) {
        this.fornecedorRepository = fornecedorRepository;
        this.fornecedorMapper = fornecedorMapper;
    }

    public List<FornecedorDTO> listarTodos() {
        return fornecedorRepository.findAll()
                .stream()
                .map(fornecedorMapper::toDTO)
                .toList();
    }

    public FornecedorDTO buscarPorId(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com ID: " + id));
        return fornecedorMapper.toDTO(fornecedor);
    }

    public FornecedorDTO salvar(FornecedorRequestDTO dto) {
        Fornecedor fornecedor = fornecedorMapper.toEntity(dto);
        return fornecedorMapper.toDTO(fornecedorRepository.save(fornecedor));
    }

    public FornecedorDTO atualizar(Long id, FornecedorRequestDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com ID: " + id));

        fornecedorMapper.updateEntityFromDto(dto, fornecedor);
        return fornecedorMapper.toDTO(fornecedorRepository.save(fornecedor));
    }

    public void deletar(Long id) {
        fornecedorRepository.deleteById(id);
    }
}