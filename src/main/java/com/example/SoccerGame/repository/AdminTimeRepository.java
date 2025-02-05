package com.example.SoccerGame.repository;

import com.example.SoccerGame.models.AdminTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminTimeRepository extends JpaRepository<AdminTime, Long> {

    UserDetails findByLogin(String subject);
}
