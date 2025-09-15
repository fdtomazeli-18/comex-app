package br.com.alura.comex.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoCreateDto {
    
    @NotBlank
    @Size(min = 2)
    private String nome;
    
    private String descricao;
    
    @NotNull
    @Positive
    private Double preco;
    
    @NotNull
    @Min(0)
    private Integer quantidadeEstoque;
    
    @NotNull
    private Long categoriaId;
}