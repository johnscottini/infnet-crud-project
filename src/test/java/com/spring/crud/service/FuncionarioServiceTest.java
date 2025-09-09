package com.spring.crud.service;

import com.spring.crud.dto.FuncionarioDTO;
import com.spring.crud.dto.FuncionarioRequestDTO;
import com.spring.crud.entity.Funcionario;
import com.spring.crud.entity.enums.Departamento;
import com.spring.crud.mapper.FuncionarioMapper;
import com.spring.crud.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private FuncionarioMapper funcionarioMapper;

    @InjectMocks
    private FuncionarioService funcionarioService;

    private Funcionario funcionario;
    private FuncionarioDTO funcionarioDTO;
    private FuncionarioRequestDTO funcionarioRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        funcionario = Funcionario.builder()
                .id(1L)
                .nome("Funcionario Teste")
                .departamento(Departamento.TI)
                .cargo("Desenvolvedor")
                .salario(5000.0)
                .build();

        funcionarioDTO = FuncionarioDTO.builder()
                .id(1L)
                .nome("Funcionario Teste")
                .departamento(Departamento.RH)
                .cargo("Desenvolvedor")
                .salario(5000.0)
                .build();

        funcionarioRequestDTO = FuncionarioRequestDTO.builder()
                .nome("Funcionario Teste")
                .departamento(Departamento.FINANCEIRO)
                .cargo("Desenvolvedor")
                .salario(5000.0)
                .build();
    }

    @Test
    void listarTodos_DeveRetornarListaDeFuncionarios() {
        when(funcionarioRepository.findAll()).thenReturn(List.of(funcionario));
        when(funcionarioMapper.toDTO(funcionario)).thenReturn(funcionarioDTO);

        List<FuncionarioDTO> result = funcionarioService.listarTodos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Funcionario Teste", result.get(0).getNome());
        verify(funcionarioRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_Existente_DeveRetornarFuncionarioDTO() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        when(funcionarioMapper.toDTO(funcionario)).thenReturn(funcionarioDTO);

        FuncionarioDTO result = funcionarioService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals("Funcionario Teste", result.getNome());
        verify(funcionarioRepository, times(1)).findById(1L);
    }

    @Test
    void buscarPorId_Inexistente_DeveLancarExcecao() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> funcionarioService.buscarPorId(1L));

        assertEquals("Funcionário não encontrado com ID: 1", exception.getMessage());
    }

    @Test
    void salvar_DeveRetornarFuncionarioSalvo() {
        when(funcionarioMapper.toEntity(funcionarioRequestDTO)).thenReturn(funcionario);
        when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
        when(funcionarioMapper.toDTO(funcionario)).thenReturn(funcionarioDTO);

        FuncionarioDTO result = funcionarioService.salvar(funcionarioRequestDTO);

        assertNotNull(result);
        assertEquals("Funcionario Teste", result.getNome());
        verify(funcionarioRepository, times(1)).save(funcionario);
    }

    @Test
    void atualizar_Existente_DeveRetornarFuncionarioAtualizado() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        doAnswer(invocation -> {
            FuncionarioRequestDTO dto = invocation.getArgument(0);
            Funcionario entity = invocation.getArgument(1);
            entity.setNome(dto.getNome());
            entity.setDepartamento(dto.getDepartamento());
            entity.setCargo(dto.getCargo());
            entity.setSalario(dto.getSalario());
            return null;
        }).when(funcionarioMapper).updateEntityFromDto(funcionarioRequestDTO, funcionario);

        when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
        when(funcionarioMapper.toDTO(funcionario)).thenReturn(funcionarioDTO);

        FuncionarioDTO result = funcionarioService.atualizar(1L, funcionarioRequestDTO);

        assertNotNull(result);
        assertEquals("Funcionario Teste", result.getNome());
        verify(funcionarioRepository, times(1)).save(funcionario);
    }

    @Test
    void atualizar_Inexistente_DeveLancarExcecao() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> funcionarioService.atualizar(1L, funcionarioRequestDTO));

        assertEquals("Funcionário não encontrado com ID: 1", exception.getMessage());
    }

    @Test
    void deletar_DeveChamarDeleteById() {
        doNothing().when(funcionarioRepository).deleteById(1L);

        funcionarioService.deletar(1L);

        verify(funcionarioRepository, times(1)).deleteById(1L);
    }
}
