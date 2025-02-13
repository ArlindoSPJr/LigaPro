package com.example.SoccerGame.service;

import com.example.SoccerGame.controller.dto.UpdateEstatisticasDto;
import com.example.SoccerGame.exceptions.ResourceNotFoundException;
import com.example.SoccerGame.models.Estatisticas;
import com.example.SoccerGame.models.Jogador;
import com.example.SoccerGame.repository.EstatisticasRepository;
import com.example.SoccerGame.repository.JogadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EstatisticasService {

    @Autowired
    private EstatisticasRepository estatisticasRepository;

    @Autowired
    private JogadoresRepository jogadoresRepository;

    public boolean addGolParaJogador(UpdateEstatisticasDto dto, Long jogadorId) {
        Jogador jogadorEncontrado = jogadoresRepository.findByJogadorId(jogadorId);

        if (jogadorEncontrado == null) {
            throw new ResourceNotFoundException("Jogador não encontrado com o ID: " + jogadorId);
        }

        if (jogadorEncontrado.getEstatisticas() == null) {
            Estatisticas novasEstatisticas = new Estatisticas();
            novasEstatisticas.setAssistencias(dto.assistencia());
            novasEstatisticas.setGols(dto.gol());

            Estatisticas estatisticasSalvas = estatisticasRepository.save(novasEstatisticas);

            jogadorEncontrado.setEstatisticas(estatisticasSalvas);
        } else {
            jogadorEncontrado.getEstatisticas().setAssistencias(dto.assistencia());
            jogadorEncontrado.getEstatisticas().setGols(dto.gol());
        }

        jogadoresRepository.save(jogadorEncontrado);

        return true;
    }



}