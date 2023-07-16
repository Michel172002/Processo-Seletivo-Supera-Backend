package br.com.banco.repositories;

import br.com.banco.models.TransferenciaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TransferenciaRepository extends JpaRepository<TransferenciaModel, Long> {
    Page<TransferenciaModel> findAllByContaId(Long id, Pageable pageable);

    Page<TransferenciaModel> findAllByContaIdAndDataTransferenciaBetween(
            Long id,
            LocalDateTime startDate,
            LocalDateTime endData,
            Pageable pageable);

    Page<TransferenciaModel> findAllByContaIdAndNomeOperadorTransacao(
            Long id,
            String nomeOperadorTransacao,
            Pageable pageable);
}

