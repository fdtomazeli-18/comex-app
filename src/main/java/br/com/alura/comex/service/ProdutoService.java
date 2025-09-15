package br.com.alura.comex.service;

import br.com.alura.comex.dto.CategoriaResponseDto;
import br.com.alura.comex.dto.ProdutoCreateDto;
import br.com.alura.comex.dto.ProdutoResponseDto;
import br.com.alura.comex.exception.EntidadeNaoEncontradaException;
import br.com.alura.comex.model.*;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public ProdutoResponseDto criar(ProdutoCreateDto dto) {
        Categoria categoria = categoriaRepository.findByIdAndAtivo(dto.getCategoriaId(), true)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria não encontrada ou inativa com ID: " + dto.getCategoriaId()));
        
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produto.setCategoria(categoria);
        
        Produto salvo = produtoRepository.save(produto);
        return toResponseDto(salvo);
    }



    public List<ProdutoResponseDto> listarTodos() {
        return produtoRepository.findByAtivo(true).stream()
                .map(this::toResponseDto)
                .toList();
    }

    public ProdutoResponseDto buscarPorId(Long id) {
        Produto produto = produtoRepository.findByIdAndAtivo(id, true)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado com ID: " + id));
        return toResponseDto(produto);
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
        categoriaDto.setAtivo(produto.getCategoria().isAtivo());
        dto.setCategoria(categoriaDto);
        
        return dto;
    }
}