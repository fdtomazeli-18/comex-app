package br.com.alura.comex.controller;

import br.com.alura.comex.dto.CategoriaRequestDto;
import br.com.alura.comex.dto.CategoriaResponseDto;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public CategoriaResponseDto cadastrar(@Valid @RequestBody CategoriaRequestDto dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        
        Categoria salva = categoriaRepository.save(categoria);
        
        CategoriaResponseDto response = new CategoriaResponseDto();
        response.setId(salva.getId());
        response.setNome(salva.getNome());
        response.setStatus(salva.getStatus());
        
        return response;
    }

    @GetMapping
    public List<CategoriaResponseDto> listar() {
        return categoriaRepository.findAll().stream()
                .map(categoria -> {
                    CategoriaResponseDto dto = new CategoriaResponseDto();
                    dto.setId(categoria.getId());
                    dto.setNome(categoria.getNome());
                    dto.setStatus(categoria.getStatus());
                    return dto;
                })
                .toList();
    }
}