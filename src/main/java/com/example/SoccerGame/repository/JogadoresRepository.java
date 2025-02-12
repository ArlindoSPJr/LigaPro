package com.example.SoccerGame.repository;

import com.example.SoccerGame.models.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadoresRepository extends JpaRepository<Jogador, Long> {

    Jogador findByJogadorId(Long id);

    Jogador findByNome(String name);

}
