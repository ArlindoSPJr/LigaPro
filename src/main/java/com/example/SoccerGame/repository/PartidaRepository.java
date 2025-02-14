package com.example.SoccerGame.repository;

import com.example.SoccerGame.models.Partida;
import com.example.SoccerGame.models.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findPartidaByData(LocalDate data);

    List<Partida> findPartidaByTimeMandante(Time timeEncontrado);
}
