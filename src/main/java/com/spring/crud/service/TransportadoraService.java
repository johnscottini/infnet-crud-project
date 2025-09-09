package com.spring.crud.service;

import com.spring.crud.dto.TransportadoraDTO;
import com.spring.crud.dto.TransportadoraRequestDTO;
import com.spring.crud.entity.Transportadora;
import com.spring.crud.mapper.TransportadoraMapper;
import com.spring.crud.repository.TransportadoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportadoraService {

    private final TransportadoraRepository transportadoraRepository;
    private final TransportadoraMapper transportadoraMapper;

    public TransportadoraService(TransportadoraRepository transportadoraRepository, TransportadoraMapper transportadoraMapper) {
        this.transportadoraRepository = transportadoraRepository;
        this.transportadoraMapper = transportadoraMapper;
    }

    public List<TransportadoraDTO> listarTodos() {
        return transportadoraRepository.findAll()
                .stream()
                .map(transportadoraMapper::toDTO)
                .toList();
    }

    public TransportadoraDTO buscarPorId(Long id) {
        Transportadora transportadora = transportadoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportadora não encontrada com ID: " + id));
        return transportadoraMapper.toDTO(transportadora);
    }

    public TransportadoraDTO salvar(TransportadoraRequestDTO dto) {
        Transportadora transportadora = transportadoraMapper.toEntity(dto);
        return transportadoraMapper.toDTO(transportadoraRepository.save(transportadora));
    }

    public TransportadoraDTO atualizar(Long id, TransportadoraRequestDTO dto) {
        Transportadora transportadora = transportadoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportadora não encontrada com ID: " + id));

        transportadoraMapper.updateEntityFromDto(dto, transportadora);
        return transportadoraMapper.toDTO(transportadoraRepository.save(transportadora));
    }

    public void deletar(Long id) {
        transportadoraRepository.deleteById(id);
    }
}
