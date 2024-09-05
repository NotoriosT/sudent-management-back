package com.escola.controller;

import com.escola.entidades.Participante;
import com.escola.service.ParticipanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteService servico;

    @GetMapping
    public List<Participante> listarTodosParticipantes() {
        return servico.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> buscarParticipantePorId(@PathVariable Long id) {
        return servico.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Participante> criarParticipante(@Valid @RequestBody Participante participante) {
        Participante criado = servico.salvar(participante);
        return ResponseEntity.ok(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participante> atualizarParticipante(@PathVariable Long id, @Valid @RequestBody Participante participanteAtualizado) {
        return servico.buscarPorId(id)
                .map(participante -> {
                    participante.setNome(participanteAtualizado.getNome());
                    participante.setIdade(participanteAtualizado.getIdade());
                    participante.setNotaPrimeiroSemestre(participanteAtualizado.getNotaPrimeiroSemestre());
                    participante.setNotaSegundoSemestre(participanteAtualizado.getNotaSegundoSemestre());

                    // A média será recalculada automaticamente no serviço
                    servico.salvar(participante);
                    return ResponseEntity.ok(participante);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarParticipante(@PathVariable Long id) {
        servico.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
