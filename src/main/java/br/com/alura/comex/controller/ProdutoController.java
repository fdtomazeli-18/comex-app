package br.com.alura.comex.controller;

import br.com.alura.comex.dto.*;
import br.com.alura.comex.model.*;
import br.com.alura.comex.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ProdutoResponseDto cadastrar(@Valid @RequestBody ProdutoRequestDto dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produto.setCategoria(categoria);
        
        Produto salvo = produtoRepository.save(produto);
        
        return toResponseDto(salvo);
    }

    @GetMapping
    public List<ProdutoResponseDto> listar() {
        return produtoRepository.findAll().stream()
                .map(this::toResponseDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDto buscarPorId(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(this::toResponseDto)
                .orElse(null);
    }
    
    private ProdutoResponseDto toResponseDto(Produto produto) {
        ProdutoResponseDto dto = new ProdutoResponseDto();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        dto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        
        CategoriaResponseDto categoriaDto = new CategoriaResponseDto();
        categoriaDto.setId(produto.getCategoria().getId());
        categoriaDto.setNome(produto.getCategoria().getNome());
        categoriaDto.setStatus(produto.getCategoria().getStatus());
        dto.setCategoria(categoriaDto);
        
        return dto;
    }
}