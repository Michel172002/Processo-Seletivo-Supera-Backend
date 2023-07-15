package br.com.banco.services;

import br.com.banco.models.TransferenciaModel;
import br.com.banco.repositories.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransferenciaService {
    @Autowired
    private TransferenciaRepository transferenciaRepository;

    public Page<TransferenciaModel> findAllByContaId(Long id, Pageable pageable){
        return transferenciaRepository.findAllByContaId(id, pageable);
    }

    public Page<TransferenciaModel> findAllByContaIdAndDataTransferenciaBetween(
            Long id, LocalDateTime startData, LocalDateTime endData, Pageable pageable){
        return transferenciaRepository.findAllByContaIdAndDataTransferenciaBetween(id, startData, endData, pageable);
    }
}
