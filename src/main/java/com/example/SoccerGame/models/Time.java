package com.example.SoccerGame.models;

import com.example.SoccerGame.controller.dto.CreateTimeDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "time")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long timeId;

    private String nome;

    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL)
    private List<Jogador> jogadores;

    public Time(CreateTimeDto dto){
        this.nome = dto.nome();
    }

    public Time() {}

    public Time(String nome) {
        this.nome = nome;
    }

    public Long getTimeId() {
        return timeId;
    }

    public void setTimeId(Long timeId) {
        this.timeId = timeId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}
