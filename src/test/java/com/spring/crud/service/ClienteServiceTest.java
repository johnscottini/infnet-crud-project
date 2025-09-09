package com.spring.crud.service;

import com.spring.crud.dto.ClienteDTO;
import com.spring.crud.dto.ClienteRequestDTO;
import com.spring.crud.entity.Cliente;
import com.spring.crud.mapper.ClienteMapper;
import com.spring.crud.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;
    private ClienteDTO clienteDTO;
    private ClienteRequestDTO clienteRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente = Cliente.builder()
                .id(1L)
                .nome("Ana Clara")
                .cpf("12345678901")
                .endereco("Avenida Paulista")
                .email("ana@example.com")
                .build();

        clienteDTO = ClienteDTO.builder()
                .id(1L)
                .nome("Ana Clara")
                .cpf("12345678901")
                .endereco("Avenida Paulista")
                .email("ana@example.com")
                .build();

        clienteRequestDTO = ClienteRequestDTO.builder()
                .nome("Ana Clara")
                .cpf("12345678901")
                .endereco("Avenida Paulista")
                .email("ana@example.com")
                .build();
    }

    @Test
    void listarTodos_DeveRetornarListaDeClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        when(clienteMapper.toDTO(cliente)).thenReturn(clienteDTO);

        List<ClienteDTO> resultado = clienteService.listarTodos();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNome()).isEqualTo("Ana Clara");

        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_QuandoExiste_DeveRetornarCliente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteMapper.toDTO(cliente)).thenReturn(clienteDTO);

        ClienteDTO resultado = clienteService.buscarPorId(1L);

        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getNome()).isEqualTo("Ana Clara");

        verify(clienteRepository).findById(1L);
    }

    @Test
    void buscarPorId_QuandoNaoExiste_DeveLancarExcecao() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> clienteService.buscarPorId(1L));

        assertThat(ex.getMessage()).isEqualTo("Cliente não encontrado com ID: 1");
        verify(clienteRepository).findById(1L);
    }

    @Test
    void salvar_DevePersistirENotificarMapper() {
        when(clienteMapper.toEntity(clienteRequestDTO)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.toDTO(cliente)).thenReturn(clienteDTO);

        ClienteDTO resultado = clienteService.salvar(clienteRequestDTO);

        assertThat(resultado.getNome()).isEqualTo("Ana Clara");

        verify(clienteMapper).toEntity(clienteRequestDTO);
        verify(clienteRepository).save(cliente);
        verify(clienteMapper).toDTO(cliente);
    }

    @Test
    void atualizar_QuandoExiste_DeveAtualizarEVoltarDTO() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        doAnswer(invocation -> {
            ClienteRequestDTO dto = invocation.getArgument(0);
            Cliente c = invocation.getArgument(1);
            c.setNome(dto.getNome());
            return null;
        }).when(clienteMapper).updateEntityFromDto(any(ClienteRequestDTO.class), any(Cliente.class));
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.toDTO(cliente)).thenReturn(clienteDTO);

        ClienteDTO resultado = clienteService.atualizar(1L, clienteRequestDTO);

        assertThat(resultado.getNome()).isEqualTo("Ana Clara");
        verify(clienteMapper).updateEntityFromDto(clienteRequestDTO, cliente);
        verify(clienteRepository).save(cliente);
    }

    @Test
    void atualizar_QuandoNaoExiste_DeveLancarExcecao() {
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> clienteService.atualizar(99L, clienteRequestDTO));

        assertThat(ex.getMessage()).isEqualTo("Cliente não encontrado com ID: 99");
    }

    @Test
    void deletar_DeveChamarRepository() {
        clienteService.deletar(1L);

        verify(clienteRepository, times(1)).deleteById(1L);
    }
}
