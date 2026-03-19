package com.felipe.produto.service;

import com.felipe.produto.dto.request.ProdutoRequestDTO;
import com.felipe.produto.dto.response.ProdutoResponseDTO;
import com.felipe.produto.exception.ProdutoNaoEncontradoException;
import com.felipe.produto.model.Produto;
import com.felipe.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());

        Produto salvo = produtoRepository.save(produto);

        return new ProdutoResponseDTO(salvo);
    }

    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        return new ProdutoResponseDTO(produto);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());

        Produto atualizado = produtoRepository.save(produto);

        return new ProdutoResponseDTO(produto);
    }

    public void deletar(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        produtoRepository.delete(produto);
    }


}
