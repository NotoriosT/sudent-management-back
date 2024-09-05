package com.escola.service;

import com.escola.entidades.Participante;
import com.escola.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService {

    private final ParticipanteRepository repositorio;

    @Autowired
    public ParticipanteService(ParticipanteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Participante> listarTodos() {
        return repositorio.findAll();
    }

    public Optional<Participante> buscarPorId(Long id) {
        return repositorio.findById(id);
    }

    // Método responsável por calcular a média antes de salvar
    public Participante salvar(Participante participante) {
        double media = calcularMedia(participante.getNotaPrimeiroSemestre(), participante.getNotaSegundoSemestre());
        participante.setMediaFinal(media);
        return repositorio.save(participante);
    }

    public void deletarPorId(Long id) {
        repositorio.deleteById(id);
    }

    // Função auxiliar para calcular a média
    private double calcularMedia(double notaPrimeiroSemestre, double notaSegundoSemestre) {
        return (notaPrimeiroSemestre + notaSegundoSemestre) / 2;
    }
}
