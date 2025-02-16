package com.example.SoccerGame.models;

import com.example.SoccerGame.controller.dto.CreateCampeonatoDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "campeonato")
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campeonatoId;

    private String nome;

    private LocalDate dataDeInicio;

    private LocalDate dataDeFim;

    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL)
    @JsonManagedReference //Colocada no lado "pai" da relação. Esse lado será serializado normalmente.
    private List<Partida> partidas;

    public Campeonato() {}

    public Campeonato(CreateCampeonatoDto dto){
        this.nome = dto.nome();
        this.dataDeInicio = dto.dataInicio();
        this.dataDeFim = dto.dataFim();
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
