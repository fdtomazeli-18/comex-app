package br.com.alura.comex.controller;

import br.com.alura.comex.dto.CategoriaCreateDto;
import br.com.alura.comex.dto.CategoriaResponseDto;
import br.com.alura.comex.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public CategoriaResponseDto cadastrar(@Valid @RequestBody CategoriaCreateDto dto) {
        return categoriaService.criar(dto);
    }

    @GetMapping
    public List<CategoriaResponseDto> listar() {
        return categoriaService.listarTodas();
    }
}