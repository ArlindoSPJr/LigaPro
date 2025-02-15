package com.example.SoccerGame.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "campeonato")
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campeonatoId;

    private String nome;

    private LocalDate dataDeInicio;

    private LocalDate dataDeFim;

    public Campeonato() {}

    public Campeonato(String nome, LocalDate dataDeInicio, LocalDate dataDeFim) {
        this.nome = nome;
        this.dataDeInicio = dataDeInicio;
        this.dataDeFim = dataDeFim;
    }

    public Long getCampeonatoId() {
        return campeonatoId;
    }

    public void setCampeonatoId(Long campeonatoId) {
        this.campeonatoId = campeonatoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(LocalDate dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public LocalDate getDataDeFim() {
        return dataDeFim;
    }

    public void setDataDeFim(LocalDate dataDeFim) {
        this.dataDeFim = dataDeFim;
    }
}
