package com.felipe.produto.dto.response;

import com.felipe.produto.model.Produto;

public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidade;

    public ProdutoResponseDTO(Long id, String nome, Double preco, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    //Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    //Construtores
    public ProdutoResponseDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.quantidade = produto.getQuantidade();
    }
}
