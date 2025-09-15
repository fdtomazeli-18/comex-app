package br.com.alura.comex.dto;

import br.com.alura.comex.model.StatusCategoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaResponseDto {
    
    private Long id;
    private String nome;
    private StatusCategoria status;
}