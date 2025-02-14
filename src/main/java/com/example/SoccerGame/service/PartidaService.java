package com.example.SoccerGame.service;

import com.example.SoccerGame.models.Partida;
import com.example.SoccerGame.models.Time;
import com.example.SoccerGame.repository.PartidaRepository;
import com.example.SoccerGame.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private TimeRepository timeRepository;

    public List<Partida> getPartidas(){
        return partidaRepository.findAll();
    }

    public List<Partida> getPartidasComData(LocalDate data){
        return partidaRepository.findPartidaByData(data);
    }

    public List<Partida> getPartidasDoTime(Long timeId){

        Time timeEncontrado = timeRepository.findByTimeId(timeId);

        return partidaRepository.findPartidaByTimeMandante(timeEncontrado);
    }

    public void savePartida(Partida partida){
        partidaRepository.save(partida);
    }
}
