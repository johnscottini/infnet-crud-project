package com.spring.crud.service;

import com.spring.crud.dto.TransportadoraDTO;
import com.spring.crud.dto.TransportadoraRequestDTO;
import com.spring.crud.entity.Transportadora;
import com.spring.crud.mapper.TransportadoraMapper;
import com.spring.crud.repository.TransportadoraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransportadoraServiceTest {

    @Mock
    private TransportadoraRepository transportadoraRepository;

    @Mock
    private TransportadoraMapper transportadoraMapper;

    @InjectMocks
    private TransportadoraService transportadoraService;

    private Transportadora transportadora;
    private TransportadoraDTO transportadoraDTO;
    private TransportadoraRequestDTO transportadoraRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        transportadora = Transportadora.builder()
                .id(1L)
                .nome("Transportadora Teste")
                .contato("12345678000190")
                .endereco("Rua A, 100")
                .valorBaseFrete(BigDecimal.valueOf(25))
                .build();

        transportadoraDTO = TransportadoraDTO.builder()
                .id(1L)
                .nome("Transportadora Teste")
                .contato("12345678000190")
                .endereco("Rua A, 100")
                .valorBaseFrete(BigDecimal.valueOf(25))
                .build();

        transportadoraRequestDTO = TransportadoraRequestDTO.builder()
                .nome("Transportadora Teste")
                .contato("12345678000190")
                .endereco("Rua A, 100")
                .valorBaseFrete(BigDecimal.valueOf(25))
                .build();
    }

    @Test
    void listarTodos_DeveRetornarListaDeTransportadoras() {
        when(transportadoraRepository.findAll()).thenReturn(List.of(transportadora));
        when(transportadoraMapper.toDTO(transportadora)).thenReturn(transportadoraDTO);

        List<TransportadoraDTO> result = transportadoraService.listarTodos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Transportadora Teste", result.get(0).getNome());
        verify(transportadoraRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_Existente_DeveRetornarTransportadoraDTO() {
        when(transportadoraRepository.findById(1L)).thenReturn(Optional.of(transportadora));
        when(transportadoraMapper.toDTO(transportadora)).thenReturn(transportadoraDTO);

        TransportadoraDTO result = transportadoraService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals("Transportadora Teste", result.getNome());
        verify(transportadoraRepository, times(1)).findById(1L);
    }

    @Test
    void buscarPorId_Inexistente_DeveLancarExcecao() {
        when(transportadoraRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> transportadoraService.buscarPorId(1L));

        assertEquals("Transportadora não encontrada com ID: 1", exception.getMessage());
    }

    @Test
    void salvar_DeveRetornarTransportadoraSalva() {
        when(transportadoraMapper.toEntity(transportadoraRequestDTO)).thenReturn(transportadora);
        when(transportadoraRepository.save(transportadora)).thenReturn(transportadora);
        when(transportadoraMapper.toDTO(transportadora)).thenReturn(transportadoraDTO);

        TransportadoraDTO result = transportadoraService.salvar(transportadoraRequestDTO);

        assertNotNull(result);
        assertEquals("Transportadora Teste", result.getNome());
        verify(transportadoraRepository, times(1)).save(transportadora);
    }

    @Test
    void atualizar_Existente_DeveRetornarTransportadoraAtualizada() {
        when(transportadoraRepository.findById(1L)).thenReturn(Optional.of(transportadora));
        doAnswer(invocation -> {
            TransportadoraRequestDTO dto = invocation.getArgument(0);
            Transportadora entity = invocation.getArgument(1);
            entity.setNome(dto.getNome());
            entity.setContato(dto.getContato());
            entity.setEndereco(dto.getEndereco());
            entity.setValorBaseFrete(dto.getValorBaseFrete());
            return null;
        }).when(transportadoraMapper).updateEntityFromDto(transportadoraRequestDTO, transportadora);

        when(transportadoraRepository.save(transportadora)).thenReturn(transportadora);
        when(transportadoraMapper.toDTO(transportadora)).thenReturn(transportadoraDTO);

        TransportadoraDTO result = transportadoraService.atualizar(1L, transportadoraRequestDTO);

        assertNotNull(result);
        assertEquals("Transportadora Teste", result.getNome());
        verify(transportadoraRepository, times(1)).save(transportadora);
    }

    @Test
    void atualizar_Inexistente_DeveLancarExcecao() {
        when(transportadoraRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> transportadoraService.atualizar(1L, transportadoraRequestDTO));

        assertEquals("Transportadora não encontrada com ID: 1", exception.getMessage());
    }

    @Test
    void deletar_DeveChamarDeleteById() {
        doNothing().when(transportadoraRepository).deleteById(1L);

        transportadoraService.deletar(1L);

        verify(transportadoraRepository, times(1)).deleteById(1L);
    }
}
