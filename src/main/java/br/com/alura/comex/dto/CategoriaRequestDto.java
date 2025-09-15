package br.com.alura.comex.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaRequestDto {
    
    @NotBlank
    @Size(min = 2)
    private String nome;
}