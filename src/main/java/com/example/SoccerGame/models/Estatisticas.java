package com.example.SoccerGame.models;

import jakarta.persistence.*;

@Entity
@Table(name = "estatisticas")
public class Estatisticas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long estatisticaId;

    @ManyToOne
    @JoinColumn(name = "jogadorId")
    private Jogador jogador;

    private int gols;

    private int assistencias;

    public Estatisticas() {}

    public Estatisticas(Jogador jogador, int gols, int assistencias) {
        this.jogador = jogador;
        this.gols = gols;
        this.assistencias = assistencias;
    }

    public Long getEstatisticaId() {
        return estatisticaId;
    }

    public void setEstatisticaId(Long estatisticaId) {
        this.estatisticaId = estatisticaId;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public int getGols() {
        return gols;
    }

    public void setGols(int gols) {
        this.gols += gols;
    }

    public int getAssistencias() {
        return assistencias;
    }

    public void setAssistencias(int assistencias) {
        this.assistencias += assistencias;
    }
}
