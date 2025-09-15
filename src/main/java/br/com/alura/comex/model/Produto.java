package br.com.alura.comex.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produto")
@Getter
@Setter
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2)
    private String nome;
    
    private String descricao;

    @NotNull
    @Positive
    @Column(name = "preco")
    private Double preco;
    
    @NotNull
    @Min(0)
    @Column(name = "quantidade_estoque")
    private Integer quantidadeEstoque;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @NotNull
    private Categoria categoria;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.ATIVO;

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", quantidadeEstoque=" + quantidadeEstoque +
                ", categoria=" + categoria +
                '}';
    }
}