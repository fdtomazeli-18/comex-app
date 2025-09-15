package br.com.alura.comex.controller;

import br.com.alura.comex.dto.CategoriaCreateDto;
import br.com.alura.comex.dto.CategoriaResponseDto;
import br.com.alura.comex.response.ApiResponse;
import br.com.alura.comex.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoriaResponseDto>> cadastrar(@Valid @RequestBody CategoriaCreateDto dto) {
        CategoriaResponseDto categoria = categoriaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Categoria criada com sucesso", categoria));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoriaResponseDto>>> listar() {
        List<CategoriaResponseDto> categorias = categoriaService.listarTodas();
        return ResponseEntity.ok(ApiResponse.success("Categorias listadas com sucesso", categorias));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaResponseDto>> buscarPorId(@PathVariable Long id) {
        CategoriaResponseDto categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(ApiResponse.success("Categoria encontrada", categoria));
    }
}