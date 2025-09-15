package br.com.alura.comex.service;

import br.com.alura.comex.dto.CategoriaCreateDto;
import br.com.alura.comex.dto.CategoriaResponseDto;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Status;
import br.com.alura.comex.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaResponseDto criar(CategoriaCreateDto dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        
        Categoria salva = categoriaRepository.save(categoria);
        return toResponseDto(salva);
    }

    public List<CategoriaResponseDto> listarTodas() {
        return categoriaRepository.findByStatus(Status.ATIVO).stream()
                .map(this::toResponseDto)
                .toList();
    }

    private CategoriaResponseDto toResponseDto(Categoria categoria) {
        CategoriaResponseDto dto = new CategoriaResponseDto();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());
        dto.setStatus(categoria.getStatus());
        return dto;
    }
}