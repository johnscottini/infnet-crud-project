package com.spring.crud.service;

import com.spring.crud.dto.ClienteDTO;
import com.spring.crud.dto.ClienteRequestDTO;
import com.spring.crud.entity.Cliente;
import com.spring.crud.mapper.ClienteMapper;
import com.spring.crud.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDTO)
                .toList();
    }

    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
        return clienteMapper.toDTO(cliente);
    }

    public ClienteDTO salvar(ClienteRequestDTO dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        return clienteMapper.toDTO(clienteRepository.save(cliente));
    }

    public ClienteDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        clienteMapper.updateEntityFromDto(dto, cliente);
        return clienteMapper.toDTO(clienteRepository.save(cliente));
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}