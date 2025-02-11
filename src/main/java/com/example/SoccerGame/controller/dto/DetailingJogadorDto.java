package com.example.SoccerGame.controller.dto;

import com.example.SoccerGame.models.Jogador;

public record DetailingJogadorDto(String nome, String email) {
    public DetailingJogadorDto(Jogador jogador) {
        this(jogador.getNome(), jogador.getEmail());
    }
}
