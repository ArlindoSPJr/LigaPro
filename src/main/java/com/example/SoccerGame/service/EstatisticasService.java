package com.example.SoccerGame.service;

import com.example.SoccerGame.controller.dto.UpdateEstatisticasDto;
import com.example.SoccerGame.exceptions.ResourceNotFoundException;
import com.example.SoccerGame.models.Estatisticas;
import com.example.SoccerGame.models.Jogador;
import com.example.SoccerGame.repository.EstatisticasRepository;
import com.example.SoccerGame.repository.JogadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstatisticasService {

    @Autowired
    private EstatisticasRepository EstatisticasRepository;

    @Autowired
    private JogadoresRepository jogadoresRepository;

    public void addGolParaJogador(UpdateEstatisticasDto dto, Long jogadorId){
        Jogador jogadorEncontrado = jogadoresRepository.findByJogadorId(jogadorId);

        if (jogadorEncontrado == null){
            throw new ResourceNotFoundException("Jogador não encontrado");
        }

        jogadorEncontrado.getEstatisticas().setAssistencias(dto.assistencia());
        jogadorEncontrado.getEstatisticas().setGols(dto.gol());

    }
}
