package com.example.SoccerGame.models;

import com.example.SoccerGame.controller.dto.CreatePartidaDto;
import com.example.SoccerGame.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "partida")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partidaId;

    private LocalDate data;

    private LocalTime hora;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "campeonato_id")
    @JsonBackReference //Colocada no lado "filho" da relação. Esse lado não será serializado, evitando o ciclo.
    private Campeonato campeonato;

    public Partida() {}

    public Partida(Time timeMandante, Time timeVisitante, CreatePartidaDto dto){

        this.timeMandante = timeMandante;
        this.timeVisitante = timeVisitante;
        this.data = dto.data();
        this.hora = dto.hora();
    }

    public Partida(Campeonato campeonato,Time timeVisitante, Time timeMandante, LocalTime hora, LocalDate data) {
        this.campeonato = campeonato;
        this.timeVisitante = timeVisitante;
        this.timeMandante = timeMandante;
        setDataHora(data, hora);
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

    public Time getTimeMandante() {
        return timeMandante;
    }


    public void setDataHora(LocalDate data, LocalTime hora) {
        LocalDateTime dataHoraPartida = LocalDateTime.of(data, hora);
        LocalDateTime agora = LocalDateTime.now();

        if (dataHoraPartida.isBefore(agora)) {
            throw new ResourceNotFoundException("Data e hora inválidas: a partida não pode ser no passado.");
        }

        this.data = data;
        this.hora = hora;
    }

}
