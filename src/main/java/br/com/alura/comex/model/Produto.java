package br.com.alura.comex.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {
    private Long id;

    private String nome;
    private String descricao;

    private double preco;
    private List<Categoria> categorias = new ArrayList<>();


    public void adicionaCategoria(Categoria categoria) {
        // verifica se a categoria já foi adicionada com base no id
        for (Categoria categoriaDaLista : categorias) {
            if (categoriaDaLista.getId().equals(categoria.getId())) {
                return;
            }
        }

        this.categorias.add(categoria);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", categorias=" + categorias +
                '}';
    }
}