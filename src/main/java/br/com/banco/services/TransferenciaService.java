package br.com.banco.services;

import br.com.banco.models.TransferenciaModel;
import br.com.banco.repositories.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
public class TransferenciaService {
    @Autowired
    private TransferenciaRepository transferenciaRepository;

    public Page<TransferenciaModel> findTransferencias(Long id, LocalDateTime startData, LocalDateTime endData, String nomeOperadorTransacao, Pageable pageable) {
        if (StringUtils.hasText(nomeOperadorTransacao)) {
            if (startData != null && endData != null) {
                return transferenciaRepository.findAllByContaIdAndDataTransferenciaBetweenAndNomeOperadorTransacao(id, startData, endData, nomeOperadorTransacao, pageable);
            } else if (startData != null) {
                LocalDateTime now = LocalDateTime.now();
                return transferenciaRepository.findAllByContaIdAndDataTransferenciaBetweenAndNomeOperadorTransacao(id, startData, now, nomeOperadorTransacao, pageable);
            } else if (endData != null) {
                LocalDateTime startDateTime = LocalDateTime.parse("1900-01-01T00:00:00");
                return transferenciaRepository.findAllByContaIdAndDataTransferenciaBetweenAndNomeOperadorTransacao(id, startDateTime, endData, nomeOperadorTransacao, pageable);
            } else {
                return transferenciaRepository.findAllByContaIdAndNomeOperadorTransacao(id, nomeOperadorTransacao, pageable);
            }
        } else {
            if (startData != null && endData != null) {
                return transferenciaRepository.findAllByContaIdAndDataTransferenciaBetween(id, startData, endData, pageable);
            } else if (startData != null) {
                LocalDateTime now = LocalDateTime.now();
                return transferenciaRepository.findAllByContaIdAndDataTransferenciaBetween(id, startData, now, pageable);
            } else if (endData != null) {
                LocalDateTime startDateTime = LocalDateTime.parse("1900-01-01T00:00:00");
                return transferenciaRepository.findAllByContaIdAndDataTransferenciaBetween(id, startDateTime, endData, pageable);
            } else {
                return transferenciaRepository.findAllByContaId(id, pageable);
            }
        }
    }
}
