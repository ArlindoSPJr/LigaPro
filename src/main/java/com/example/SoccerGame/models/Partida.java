package com.example.SoccerGame.models;

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
