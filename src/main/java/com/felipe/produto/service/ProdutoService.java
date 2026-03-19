package com.felipe.produto.service;

import com.felipe.produto.exception.ProdutoNaoEncontradoException;
import com.felipe.produto.model.Produto;
import com.felipe.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(()-> new ProdutoNaoEncontradoException(id));
    }

    public Produto atualizar(Produto produto) {
        Produto produtoAtualizado = produtoRepository.findById(produto.getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoAtualizado.setNome(produto.getNome());
        produtoAtualizado.setPreco(produto.getPreco());
        produtoAtualizado.setQuantidade(produto.getQuantidade());

        return produtoRepository.save(produtoAtualizado);
    }

    public void deletar(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(()-> new ProdutoNaoEncontradoException(id));

        produtoRepository.delete(produto);
    }


}
