package br.com.alura.comex.controller;

import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.model.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutoDao produtoDao = new ProdutoDao();

    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto) {
        produtoDao.cadastra(produto);
        return produto;
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoDao.listaTodos();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoDao.consulta(id);
    }
}