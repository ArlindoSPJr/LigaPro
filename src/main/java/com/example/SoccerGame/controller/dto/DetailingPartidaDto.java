package com.example.SoccerGame.controller.dto;

import com.example.SoccerGame.models.Partida;

import java.time.LocalDate;
import java.time.LocalTime;

public record DetailingPartidaDto(String timeMandante, String timeVisitante, LocalDate data, LocalTime hora) {
    public DetailingPartidaDto(Partida partida) {
        this(partida.getTimeMandante().getNome(), partida.getTimeVisitante().getNome(), partida.getData(), partida.getHora());
    }
}
