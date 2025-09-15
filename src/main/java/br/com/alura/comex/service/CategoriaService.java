package br.com.alura.comex.service;

import br.com.alura.comex.dto.CategoriaCreateDto;
import br.com.alura.comex.dto.CategoriaResponseDto;
import br.com.alura.comex.exception.CategoriaJaExisteException;
import br.com.alura.comex.exception.EntidadeNaoEncontradaException;
import br.com.alura.comex.mapper.CategoriaMapper;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}