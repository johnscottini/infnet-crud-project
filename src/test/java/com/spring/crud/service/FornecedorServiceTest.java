package com.spring.crud.service;

import com.spring.crud.dto.FornecedorDTO;
import com.spring.crud.dto.FornecedorRequestDTO;
import com.spring.crud.entity.Fornecedor;
import com.spring.crud.mapper.FornecedorMapper;
import com.spring.crud.repository.FornecedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FornecedorServiceTest {

    @Mock
    private FornecedorRepository fornecedorRepository;

    @Mock
    private FornecedorMapper fornecedorMapper;

    @InjectMocks
    private FornecedorService fornecedorService;

    private Fornecedor fornecedor;
    private FornecedorDTO fornecedorDTO;
    private FornecedorRequestDTO fornecedorRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        fornecedor = Fornecedor.builder()
                .id(1L)
                .nome("Fornecedor Teste")
                .contato("12345678000190")
                .endereco("Rua A, 123")
                .email("fornecedor@test.com")
                .build();

        fornecedorDTO = FornecedorDTO.builder()
                .id(1L)
                .nome("Fornecedor Teste")
                .contato("12345678000190")
                .endereco("Rua A, 123")
                .email("fornecedor@test.com")
                .build();

        fornecedorRequestDTO = FornecedorRequestDTO.builder()
                .nome("Fornecedor Teste")
                .contato("12345678000190")
                .endereco("Rua A, 123")
                .email("fornecedor@test.com")
                .build();
    }

    @Test
    void listarTodos_DeveRetornarListaDeFornecedores() {
        when(fornecedorRepository.findAll()).thenReturn(List.of(fornecedor));
        when(fornecedorMapper.toDTO(fornecedor)).thenReturn(fornecedorDTO);

        List<FornecedorDTO> result = fornecedorService.listarTodos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Fornecedor Teste", result.get(0).getNome());
        verify(fornecedorRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_Existente_DeveRetornarFornecedorDTO() {
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(fornecedor));
        when(fornecedorMapper.toDTO(fornecedor)).thenReturn(fornecedorDTO);

        FornecedorDTO result = fornecedorService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals("Fornecedor Teste", result.getNome());
        verify(fornecedorRepository, times(1)).findById(1L);
    }

    @Test
    void buscarPorId_Inexistente_DeveLancarExcecao() {
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> fornecedorService.buscarPorId(1L));

        assertEquals("Fornecedor não encontrado com ID: 1", exception.getMessage());
    }

    @Test
    void salvar_DeveRetornarFornecedorSalvo() {
        when(fornecedorMapper.toEntity(fornecedorRequestDTO)).thenReturn(fornecedor);
        when(fornecedorRepository.save(fornecedor)).thenReturn(fornecedor);
        when(fornecedorMapper.toDTO(fornecedor)).thenReturn(fornecedorDTO);

        FornecedorDTO result = fornecedorService.salvar(fornecedorRequestDTO);

        assertNotNull(result);
        assertEquals("Fornecedor Teste", result.getNome());
        verify(fornecedorRepository, times(1)).save(fornecedor);
    }

    @Test
    void atualizar_Existente_DeveRetornarFornecedorAtualizado() {
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(fornecedor));
        doAnswer(invocation -> {
            FornecedorRequestDTO dto = invocation.getArgument(0);
            Fornecedor entity = invocation.getArgument(1);
            entity.setNome(dto.getNome());
            entity.setContato(dto.getContato());
            entity.setEndereco(dto.getEndereco());
            entity.setEmail(dto.getEmail());
            return null;
        }).when(fornecedorMapper).updateEntityFromDto(fornecedorRequestDTO, fornecedor);

        when(fornecedorRepository.save(fornecedor)).thenReturn(fornecedor);
        when(fornecedorMapper.toDTO(fornecedor)).thenReturn(fornecedorDTO);

        FornecedorDTO result = fornecedorService.atualizar(1L, fornecedorRequestDTO);

        assertNotNull(result);
        assertEquals("Fornecedor Teste", result.getNome());
        verify(fornecedorRepository, times(1)).save(fornecedor);
    }

    @Test
    void atualizar_Inexistente_DeveLancarExcecao() {
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> fornecedorService.atualizar(1L, fornecedorRequestDTO));

        assertEquals("Fornecedor não encontrado com ID: 1", exception.getMessage());
    }

    @Test
    void deletar_DeveChamarDeleteById() {
        doNothing().when(fornecedorRepository).deleteById(1L);

        fornecedorService.deletar(1L);

        verify(fornecedorRepository, times(1)).deleteById(1L);
    }
}
