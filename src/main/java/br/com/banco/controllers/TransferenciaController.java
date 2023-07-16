package br.com.banco.controllers;

import br.com.banco.models.TransferenciaModel;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/tranferencias")
public class TransferenciaController {
    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping("/{id}")
    public ResponseEntity<Page<TransferenciaModel>> getTransferencias(
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "startData", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startData,
            @RequestParam(value = "endData", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endData,
            @RequestParam(value = "opTransf", required = false) String nomeOperadorTransacao,
            @PageableDefault(size = 4) Pageable pageable) {

        Page<TransferenciaModel> transferencias;

        if(startData != null && endData != null) {
            transferencias = transferenciaService.findAllByContaIdAndDataTransferenciaBetween(id, startData, endData, pageable);
            return ResponseEntity.status(HttpStatus.OK).body(transferencias);

        } else if (startData != null && endData == null && nomeOperadorTransacao == null) {
            endData = LocalDateTime.now();
            transferencias = transferenciaService.findAllByContaIdAndDataTransferenciaBetween(id, startData, endData, pageable);
            return ResponseEntity.status(HttpStatus.OK).body(transferencias);

        } else if (startData == null && endData != null && nomeOperadorTransacao == null) {
            startData = LocalDateTime.parse("1900-01-01T00:00:00");
            transferencias = transferenciaService.findAllByContaIdAndDataTransferenciaBetween(id, startData, endData, pageable);
            return ResponseEntity.status(HttpStatus.OK).body(transferencias);

        } else if (startData == null && endData == null && nomeOperadorTransacao != null) {
            transferencias = transferenciaService.findAllByContaIdAndNomeOperadorTransacao(id, nomeOperadorTransacao, pageable);
            return ResponseEntity.status(HttpStatus.OK).body(transferencias);

        } else {
            transferencias = transferenciaService.findAllByContaId(id, pageable);
            return ResponseEntity.status(HttpStatus.OK).body(transferencias);
        }
    }
}

