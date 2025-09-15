package br.com.alura.comex.service;

import br.com.alura.comex.dto.CategoriaCreateDto;
import br.com.alura.comex.dto.CategoriaResponseDto;
import br.com.alura.comex.exception.CategoriaJaExisteException;
import br.com.alura.comex.exception.EntidadeNaoEncontradaException;
import br.com.alura.comex.model.Categoria;

import br.com.alura.comex.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaResponseDto criar(CategoriaCreateDto dto) {
        if (categoriaRepository.existsByNomeAndAtivo(dto.getNome(), true)) {
            throw new CategoriaJaExisteException("Categoria com nome '" + dto.getNome() + "' já existe");
        }
        
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        
        Categoria salva = categoriaRepository.save(categoria);
        return toResponseDto(salva);
    }

    public List<CategoriaResponseDto> listarTodas() {
        return categoriaRepository.findByAtivo(true).stream()
                .map(this::toResponseDto)
                .toList();
    }
    
    public CategoriaResponseDto buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findByIdAndAtivo(id, true)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria não encontrada com ID: " + id));
        return toResponseDto(categoria);
    }

    private CategoriaResponseDto toResponseDto(Categoria categoria) {
        CategoriaResponseDto dto = new CategoriaResponseDto();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());
        dto.setAtivo(categoria.isAtivo());
        return dto;
    }
}