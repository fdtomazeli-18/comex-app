package br.com.alura.comex.service;

import br.com.alura.comex.dto.ProdutoCreateDto;
import br.com.alura.comex.dto.ProdutoResponseDto;
import br.com.alura.comex.exception.EntidadeNaoEncontradaException;
import br.com.alura.comex.mapper.ProdutoMapper;
import br.com.alura.comex.model.*;
import lombok.extern.slf4j.Slf4j;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private ProdutoMapper produtoMapper;

    public ProdutoResponseDto criar(ProdutoCreateDto dto) {
        log.info("Criando produto: {} para categoria ID: {}", dto.getNome(), dto.getCategoriaId());
        
        Categoria categoria = categoriaRepository.findByIdAndAtivo(dto.getCategoriaId(), true)
                .orElseThrow(() -> {
                    log.warn("Categoria não encontrada ou inativa com ID: {}", dto.getCategoriaId());
                    return new EntidadeNaoEncontradaException("Categoria não encontrada ou inativa com ID: " + dto.getCategoriaId());
                });
        
        Produto produto = produtoMapper.toEntity(dto, categoria);
        Produto salvo = produtoRepository.save(produto);
        
        log.info("Produto criado com sucesso. ID: {}", salvo.getId());
        return produtoMapper.toResponseDto(salvo);
    }



    public List<ProdutoResponseDto> listarTodos() {
        log.info("Listando todos os produtos ativos");
        return produtoRepository.findByAtivo(true).stream()
                .map(produtoMapper::toResponseDto)
                .toList();
    }

    public ProdutoResponseDto buscarPorId(Long id) {
        log.info("Buscando produto por ID: {}", id);
        Produto produto = produtoRepository.findByIdAndAtivo(id, true)
                .orElseThrow(() -> {
                    log.warn("Produto não encontrado com ID: {}", id);
                    return new EntidadeNaoEncontradaException("Produto não encontrado com ID: " + id);
                });
        return produtoMapper.toResponseDto(produto);
    }

}