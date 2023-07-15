package br.com.banco.controllers;

import br.com.banco.models.TransferenciaModel;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tranferencias")
public class TransferenciaController {
    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping("/{id}")
    public ResponseEntity<Page<TransferenciaModel>> findAllByIdConta(
            @PathVariable(value = "id") Long id,
            @PageableDefault(size = 4) Pageable pageable) {
        Page<TransferenciaModel> transferencias = transferenciaService.findAllByContaId(id, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(transferencias);
    }
}

