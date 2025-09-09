package com.spring.crud.service;

import com.spring.crud.dto.ProdutoDTO;
import com.spring.crud.dto.ProdutoRequestDTO;
import com.spring.crud.entity.Produto;
import com.spring.crud.mapper.ProdutoMapper;
import com.spring.crud.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    public List<ProdutoDTO> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(produtoMapper::toDTO)
                .toList();
    }

    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        return produtoMapper.toDTO(produto);
    }

    public ProdutoDTO salvar(ProdutoRequestDTO dto) {
        Produto produto = produtoMapper.toEntity(dto);
        return produtoMapper.toDTO(produtoRepository.save(produto));
    }

    public ProdutoDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        produtoMapper.updateEntityFromDto(dto, produto);
        return produtoMapper.toDTO(produtoRepository.save(produto));
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
}