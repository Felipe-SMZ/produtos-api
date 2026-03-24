package com.felipe.produto.service;

import com.felipe.produto.dto.request.ProdutoRequestDTO;
import com.felipe.produto.dto.response.ProdutoResponseDTO;
import com.felipe.produto.exception.ProdutoNaoEncontradoException;
import com.felipe.produto.model.Produto;
import com.felipe.produto.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    private Produto produto;
    private ProdutoRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        produto = new Produto();
        produto.setId(1L);
        produto.setNome("Teclado Mecânico");
        produto.setPreco(299.90);
        produto.setQuantidade(10);

        requestDTO = new ProdutoRequestDTO();
        requestDTO.setNome("Teclado Mecânico");
        requestDTO.setPreco(299.90);
        requestDTO.setQuantidade(10);
    }

    @Test
    void deveSalvarProdutoComSucesso() {
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        ProdutoResponseDTO response = produtoService.salvar(requestDTO);

        assertNotNull(response);
        assertEquals("Teclado Mecânico", response.getNome());
        assertEquals(299.90, response.getPreco());
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void deveListarTodosProdutos() {
        when(produtoRepository.findAll()).thenReturn(List.of(produto));

        List<ProdutoResponseDTO> lista = produtoService.listarTodos();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals("Teclado Mecânico", lista.get(0).getNome());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    void deveBuscarProdutoPorIdComSucesso() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        ProdutoResponseDTO response = produtoService.buscarPorId(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Teclado Mecânico", response.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoEncontrado() {
        when(produtoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoException.class,
                () -> produtoService.buscarPorId(99L));

        verify(produtoRepository, times(1)).findById(99L);
    }

    @Test
    void deveDeletarProdutoComSucesso() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        doNothing().when(produtoRepository).delete(produto);

        assertDoesNotThrow(() -> produtoService.deletar(1L));

        verify(produtoRepository, times(1)).delete(produto);
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setId(1L);
        produtoAtualizado.setNome("Produto Atualizado");

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoAtualizado);

        ProdutoResponseDTO response = produtoService.atualizar(1L, requestDTO);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Produto Atualizado", response.getNome());

        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

}