package br.com.alura.comex.controller;

import br.com.alura.comex.dto.ProdutoCreateDto;
import br.com.alura.comex.dto.ProdutoResponseDto;
import br.com.alura.comex.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ProdutoResponseDto cadastrar(@Valid @RequestBody ProdutoCreateDto dto) {
        return produtoService.criar(dto);
    }

    @GetMapping
    public List<ProdutoResponseDto> listar() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDto buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }
}