package com.example.SoccerGame.repository;

import com.example.SoccerGame.models.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
    Time findByNome(String nome);

    Time findByTimeId(Long timeId);

    void deleteByTimeId(long id);


}
