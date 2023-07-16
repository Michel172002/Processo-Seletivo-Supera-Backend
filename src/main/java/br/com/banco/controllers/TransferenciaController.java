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

import java.math.BigDecimal;
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
            @PageableDefault(size = 4, sort = "dataTransferencia") Pageable pageable)
    {
        Page<TransferenciaModel> transferencias = transferenciaService.findTransferencias(id, startData, endData, nomeOperadorTransacao, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(transferencias);
    }

    @GetMapping("/{id}/somaValor")
    public ResponseEntity<BigDecimal> somarValoresTransferencias(
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "startData", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startData,
            @RequestParam(value = "endData", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endData,
            @RequestParam(value = "opTransf", required = false) String nomeOperadorTransacao,
            @PageableDefault(size = 4) Pageable pageable)
    {
        return ResponseEntity.status(HttpStatus.OK).body(transferenciaService.somarValoresTransferencias(id,startData,endData,nomeOperadorTransacao));
    }
}

