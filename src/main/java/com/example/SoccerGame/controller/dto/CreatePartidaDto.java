package com.example.SoccerGame.controller.dto;


import java.time.LocalDate;
import java.time.LocalTime;

public record CreatePartidaDto(Long campeonatoId, String timeMandante, String timeVisitante, LocalDate data, LocalTime hora) {
}
