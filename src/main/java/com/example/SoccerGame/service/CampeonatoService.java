package com.example.SoccerGame.service;

import com.example.SoccerGame.models.Campeonato;
import com.example.SoccerGame.repository.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampeonatoService {

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    public List<Campeonato> getAllCampeonato() {
        return campeonatoRepository.findAll();
    }

    public Campeonato getCampeonatoById(Long id) {
        return campeonatoRepository.findById(id).get();
    }

    public void saveCampeonato(Campeonato campeonato) {
        campeonatoRepository.save(campeonato);
    }
}
