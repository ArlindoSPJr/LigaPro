package com.example.SoccerGame.service;

import com.example.SoccerGame.models.Jogador;
import com.example.SoccerGame.repository.JogadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {

    @Autowired
    private JogadoresRepository jogadoresRepository;

    public List<Jogador> getAll(){
        return jogadoresRepository.findAll();
    }

    public Jogador getById(Long id){
        return jogadoresRepository.findByJogadorId(id);
    }

    public Jogador findByName(String name){
        return jogadoresRepository.findByNome(name);
    }

    public void save(Jogador jogador){
        jogadoresRepository.save(jogador);
    }



}
