package com.example.SoccerGame.repository;

import com.example.SoccerGame.models.Estatisticas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstatisticasRepository extends JpaRepository<Long, Estatisticas> {
}
