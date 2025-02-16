package com.example.SoccerGame.controller.dto;

import java.time.LocalDate;

public record CreateCampeonatoDto(String nome, LocalDate dataInicio, LocalDate dataFim) {
}
