package br.com.alura.comex.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResponseDto {
    
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidadeEstoque;
    private CategoriaResponseDto categoria;
}