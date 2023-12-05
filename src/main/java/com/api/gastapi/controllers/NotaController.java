package com.api.gastapi.controllers;

import com.api.gastapi.dto.NotaDto;
import com.api.gastapi.models.NotaModel;
import com.api.gastapi.repositories.NotaModelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/nota")
public class NotaController {

    private final NotaModelRepository notaModelRepository;

    public NotaController(NotaModelRepository notaModelRepository) {
        this.notaModelRepository = notaModelRepository;
    }


    @GetMapping("/{nota}")
    public ResponseEntity<Object> buscaNota(@PathVariable (value = "nota") UUID nota){
        Optional<NotaModel> notaBuscada = notaModelRepository.findById(nota);

        if (notaBuscada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nota não encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(notaBuscada.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarNota(@RequestBody @Valid NotaDto dadosRecebidos) {
        if (notaModelRepository.findByNumeronota(dadosRecebidos.getNumeronota()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DOCUMENTO JÁ CADASTRADO");
        }

        NotaModel notaModel = new NotaModel();
        BeanUtils.copyProperties(dadosRecebidos, notaModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(notaModelRepository.save(notaModel));
    }

    @PutMapping("/{nota}")
    public ResponseEntity<Object> atualizarNota(@PathVariable Long nota, @RequestBody @Valid NotaDto dadosAtualizados) {
        Optional<NotaModel> notaBuscada = notaModelRepository.findById(nota);

        if (notaBuscada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nota não encontrada");
        }

        NotaModel notaModel = notaBuscada.get();
        BeanUtils.copyProperties(NotaDto, notaModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(notaModelRepository.save(notaModel));
    }

    @DeleteMapping("/{nota}")
    public ResponseEntity<Object> excluirNota(@PathVariable (value = "nota") UUID nota) {
        Optional<NotaModel> notaBuscada = notaModelRepository.findById(nota);

        if (notaBuscada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nota não encontrada");
        }

        notaModelRepository.deleteById(notaBuscada.get());
        return ResponseEntity.status(HttpStatus.OK).body("Nota excluída com sucesso");
    }

    @PutMapping("/{nota}")
    public ResponseEntity<Object> atualizarNota(@PathVariable Long nota, @RequestBody @Valid NotaDto dadosAtualizados) {
        Optional<NotaModel> notaBuscada = notaModelRepository.findById(nota);

        if (notaBuscada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nota não encontrada");
        }

        NotaModel notaExistente = notaBuscada.get();
        BeanUtils.copyProperties(dadosAtualizados, notaExistente);
        notaExistente.setId(nota);

        return ResponseEntity.status(HttpStatus.OK).body(notaModelRepository.save(notaExistente));
    }


}

