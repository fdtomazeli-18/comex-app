package br.com.alura.comex.repository;

import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    List<Produto> findByStatus(Status status);
    
    Optional<Produto> findByIdAndStatus(Long id, Status status);
}