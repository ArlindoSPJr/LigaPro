package com.example.SoccerGame.service;

import com.example.SoccerGame.models.Jogador;
import com.example.SoccerGame.models.Time;
import com.example.SoccerGame.repository.JogadoresRepository;
import com.example.SoccerGame.repository.TimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private JogadoresRepository jogadorRepository;

    public List<Time> getAllTimes() {
        return timeRepository.findAll();
    }

    public Time getTimeByName(String nomeTime){
        return timeRepository.findByNome(nomeTime);
    }

    public Time getTimeById(Long id){
        return timeRepository.findByTimeId(id);
    }

    public void save(Time time){
        timeRepository.save(time);
    }

    public void deleteById(long id){
        timeRepository.deleteByTimeId(id);
    }

    @Transactional
    public Time adicionarJogadorAoTime(Long timeId, Long jogadorId) {
        Time time = timeRepository.findById(timeId)
                .orElseThrow(() -> new RuntimeException("Time não encontrado"));

        Jogador jogador = jogadorRepository.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        jogador.setTime(time);
        jogadorRepository.save(jogador);

        return time;
    }

    @Transactional
    public Time deleteJogadorForTheTeam(Long jogadorId){
        Jogador jogadorEncontrado = jogadorRepository.findByJogadorId(jogadorId);
        Time time = jogadorEncontrado.getTime();

        if (time != null) {
            // Remover apenas o jogador específico da lista
            time.getJogadores().removeIf(j -> j.getJogadorId().equals(jogadorId));

            // Também é necessário remover a referência ao time no jogador
            jogadorEncontrado.setTime(null);
            jogadorRepository.save(jogadorEncontrado);
        }

        return time;
    }

}
