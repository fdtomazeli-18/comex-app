package br.com.alura.comex.controller;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.model.Categoria;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private CategoriaDao categoriaDao = new CategoriaDao();

    @PostMapping
    public Categoria cadastrar(@RequestBody Categoria categoria) {
        categoriaDao.cadastra(categoria);
        return categoria;
    }

    @GetMapping
    public java.util.List<Categoria> listar() {
        return categoriaDao.listaTodos();
    }
}