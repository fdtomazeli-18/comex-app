package br.com.alura.comex.service;

import br.com.alura.comex.dto.CategoriaCreateDto;
import br.com.alura.comex.dto.CategoriaResponseDto;
import br.com.alura.comex.dto.CategoriaUpdateDto;
import br.com.alura.comex.exception.CategoriaJaExisteException;
import br.com.alura.comex.exception.EntidadeNaoEncontradaException;
import br.com.alura.comex.mapper.CategoriaMapper;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private CategoriaMapper categoriaMapper;

    public CategoriaResponseDto criar(CategoriaCreateDto dto) {
        log.info("Criando categoria com nome: {}", dto.getNome());
        
        if (categoriaRepository.existsByNomeAndAtivo(dto.getNome(), true)) {
            log.warn("Tentativa de criar categoria já existente: {}", dto.getNome());
            throw new CategoriaJaExisteException("Categoria com nome '" + dto.getNome() + "' já existe");
        }
        
        Categoria categoria = categoriaMapper.toEntity(dto);
        Categoria salva = categoriaRepository.save(categoria);
        
        log.info("Categoria criada com sucesso. ID: {}", salva.getId());
        return categoriaMapper.toResponseDto(salva);
    }

    public List<CategoriaResponseDto> listarTodas() {
        log.info("Listando todas as categorias ativas");
        return categoriaRepository.findByAtivo(true).stream()
                .map(categoriaMapper::toResponseDto)
                .toList();
    }
    
    public CategoriaResponseDto buscarPorId(Long id) {
        log.info("Buscando categoria por ID: {}", id);
        Categoria categoria = categoriaRepository.findByIdAndAtivo(id, true)
                .orElseThrow(() -> {
                    log.warn("Categoria não encontrada com ID: {}", id);
                    return new EntidadeNaoEncontradaException("Categoria não encontrada com ID: " + id);
                });
        return categoriaMapper.toResponseDto(categoria);
    }
    
    public Page<CategoriaResponseDto> listarComPaginacao(Pageable pageable) {
        log.info("Listando categorias com paginação: {}", pageable);
        return categoriaRepository.findByAtivo(true, pageable)
                .map(categoriaMapper::toResponseDto);
    }
    
    public CategoriaResponseDto atualizar(Long id, CategoriaUpdateDto dto) {
        log.info("Atualizando categoria ID: {} com nome: {}", id, dto.getNome());
        
        Categoria categoria = categoriaRepository.findByIdAndAtivo(id, true)
                .orElseThrow(() -> {
                    log.warn("Categoria não encontrada para atualização com ID: {}", id);
                    return new EntidadeNaoEncontradaException("Categoria não encontrada com ID: " + id);
                });
        
        if (categoriaRepository.existsByNomeAndAtivo(dto.getNome(), true) && 
            !categoria.getNome().equals(dto.getNome())) {
            log.warn("Tentativa de atualizar para nome já existente: {}", dto.getNome());
            throw new CategoriaJaExisteException("Categoria com nome '" + dto.getNome() + "' já existe");
        }
        
        categoriaMapper.updateEntity(categoria, dto);
        Categoria salva = categoriaRepository.save(categoria);
        
        log.info("Categoria atualizada com sucesso. ID: {}", salva.getId());
        return categoriaMapper.toResponseDto(salva);
    }
    
    public void deletar(Long id) {
        log.info("Realizando soft delete da categoria ID: {}", id);
        
        Categoria categoria = categoriaRepository.findByIdAndAtivo(id, true)
                .orElseThrow(() -> {
                    log.warn("Categoria não encontrada para deleção com ID: {}", id);
                    return new EntidadeNaoEncontradaException("Categoria não encontrada com ID: " + id);
                });
        
        categoria.setAtivo(false);
        categoriaRepository.save(categoria);
        
        log.info("Categoria desativada com sucesso. ID: {}", id);
    }
    
    public CategoriaResponseDto reativar(Long id) {
        log.info("Reativando categoria ID: {}", id);
        
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Categoria não encontrada para reativação com ID: {}", id);
                    return new EntidadeNaoEncontradaException("Categoria não encontrada com ID: " + id);
                });
        
        categoria.setAtivo(true);
        Categoria salva = categoriaRepository.save(categoria);
        
        log.info("Categoria reativada com sucesso. ID: {}", id);
        return categoriaMapper.toResponseDto(salva);
    }


}