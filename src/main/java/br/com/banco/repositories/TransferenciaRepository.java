package br.com.banco.repositories;

import br.com.banco.models.TransferenciaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends JpaRepository<TransferenciaModel, Long> {
    Page<TransferenciaModel> findAllByContaId(Long id, Pageable pageable);
}
