package com.felipe.produto.controller;

import com.felipe.produto.dto.request.ProdutoRequestDTO;
import com.felipe.produto.dto.response.ProdutoResponseDTO;
import com.felipe.produto.model.Produto;
import com.felipe.produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@Valid @RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO response = produtoService.salvar(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> buscarTodos() {
        List<ProdutoResponseDTO> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable("id") Long id) {
        ProdutoResponseDTO produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarPorId(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO dto) {

        ProdutoResponseDTO produtoAtualizado = produtoService.atualizar(id, dto);

        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
