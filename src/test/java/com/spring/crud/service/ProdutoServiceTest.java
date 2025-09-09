package com.spring.crud.service;

import com.spring.crud.dto.ProdutoDTO;
import com.spring.crud.dto.ProdutoRequestDTO;
import com.spring.crud.entity.Produto;
import com.spring.crud.mapper.ProdutoMapper;
import com.spring.crud.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoMapper produtoMapper;

    @InjectMocks
    private ProdutoService produtoService;

    private Produto produto;
    private ProdutoDTO produtoDTO;
    private ProdutoRequestDTO produtoRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        produto = Produto.builder()
                .id(1L)
                .nome("Produto Teste")
                .descricao("Descrição do produto")
                .preco(99.90)
                .quantidadeEstoque(10)
                .build();

        produtoDTO = ProdutoDTO.builder()
                .id(1L)
                .nome("Produto Teste")
                .descricao("Descrição do produto")
                .preco(99.90)
                .quantidadeEstoque(10)
                .build();

        produtoRequestDTO = ProdutoRequestDTO.builder()
                .nome("Produto Teste")
                .descricao("Descrição do produto")
                .preco(99.90)
                .quantidadeEstoque(10)
                .build();
    }

    @Test
    void listarTodos_DeveRetornarListaDeProdutos() {
        when(produtoRepository.findAll()).thenReturn(List.of(produto));
        when(produtoMapper.toDTO(produto)).thenReturn(produtoDTO);

        List<ProdutoDTO> result = produtoService.listarTodos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Produto Teste", result.get(0).getNome());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_Existente_DeveRetornarProdutoDTO() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(produtoMapper.toDTO(produto)).thenReturn(produtoDTO);

        ProdutoDTO result = produtoService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals("Produto Teste", result.getNome());
        verify(produtoRepository, times(1)).findById(1L);
    }

    @Test
    void buscarPorId_Inexistente_DeveLancarExcecao() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> produtoService.buscarPorId(1L));

        assertEquals("Produto não encontrado com ID: 1", exception.getMessage());
    }

    @Test
    void salvar_DeveRetornarProdutoSalvo() {
        when(produtoMapper.toEntity(produtoRequestDTO)).thenReturn(produto);
        when(produtoRepository.save(produto)).thenReturn(produto);
        when(produtoMapper.toDTO(produto)).thenReturn(produtoDTO);

        ProdutoDTO result = produtoService.salvar(produtoRequestDTO);

        assertNotNull(result);
        assertEquals("Produto Teste", result.getNome());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void atualizar_Existente_DeveRetornarProdutoAtualizado() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        doAnswer(invocation -> {
            ProdutoRequestDTO dto = invocation.getArgument(0);
            Produto entity = invocation.getArgument(1);
            entity.setNome(dto.getNome());
            entity.setDescricao(dto.getDescricao());
            entity.setPreco(dto.getPreco());
            entity.setQuantidadeEstoque(dto.getQuantidadeEstoque());
            return null;
        }).when(produtoMapper).updateEntityFromDto(produtoRequestDTO, produto);

        when(produtoRepository.save(produto)).thenReturn(produto);
        when(produtoMapper.toDTO(produto)).thenReturn(produtoDTO);

        ProdutoDTO result = produtoService.atualizar(1L, produtoRequestDTO);

        assertNotNull(result);
        assertEquals("Produto Teste", result.getNome());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void atualizar_Inexistente_DeveLancarExcecao() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> produtoService.atualizar(1L, produtoRequestDTO));

        assertEquals("Produto não encontrado com ID: 1", exception.getMessage());
    }

    @Test
    void deletar_DeveChamarDeleteById() {
        doNothing().when(produtoRepository).deleteById(1L);

        produtoService.deletar(1L);

        verify(produtoRepository, times(1)).deleteById(1L);
    }
}
