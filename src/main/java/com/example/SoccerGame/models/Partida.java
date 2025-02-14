package com.example.SoccerGame.models;

import com.example.SoccerGame.controller.dto.CreatePartidaDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "partida")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partidaId;

    private LocalDate data;

    private LocalTime hora;

    public Partida() {}

    public Partida(Time timeMandante, Time timeVisitante, CreatePartidaDto dto){
        this.timeMandante = timeMandante;
        this.timeVisitante = timeVisitante;
        this.data = dto.data();
        this.hora = dto.hora();
    }

    public Partida(Time timeVisitante, Time timeMandante, LocalTime hora, LocalDate data) {
        this.timeVisitante = timeVisitante;
        this.timeMandante = timeMandante;
        this.hora = hora;
        this.data = data;
    }

    public Long getPartidaId() {
        return partidaId;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
        return hora;
    }

    @ManyToOne
    @JoinColumn(name = "time_mandante_time_id")
    private Time timeMandante;

    @ManyToOne
    @JoinColumn(name = "time_visitante_time_id")
    private Time timeVisitante;

    public Time getTimeVisitante() {
        return timeVisitante;
    }

    public void setTimeVisitante(Time timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    public Time getTimeMandante() {
        return timeMandante;
    }

    public void setTimeMandante(Time timeMandante) {
        this.timeMandante = timeMandante;
    }
}
