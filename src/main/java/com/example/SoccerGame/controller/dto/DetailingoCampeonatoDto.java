package com.example.SoccerGame.controller.dto;

import com.example.SoccerGame.models.Campeonato;

import java.time.LocalDate;

public record DetailingoCampeonatoDto(String nome, LocalDate dataInicio, LocalDate dataFim) {
    public DetailingoCampeonatoDto(Campeonato campeonato){
        this(campeonato.getNome(), campeonato.getDataDeInicio(), campeonato.getDataDeFim());
    }
}
