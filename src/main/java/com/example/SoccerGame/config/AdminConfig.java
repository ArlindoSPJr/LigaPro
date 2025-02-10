package com.example.SoccerGame.config;

import com.example.SoccerGame.models.AdminTime;
import com.example.SoccerGame.repository.AdminTimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminConfig implements CommandLineRunner {

    private final AdminTimeRepository adminTimeRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminConfig(AdminTimeRepository adminTimeRepository, PasswordEncoder passwordEncoder) {
        this.adminTimeRepository = adminTimeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var admin = adminTimeRepository.findByLogin("admin@gmail.com");
        if (admin == null) {
            var novoAdmin = new AdminTime("admin@gmail.com", passwordEncoder.encode("123"));
            adminTimeRepository.save(novoAdmin);
            System.out.println("Admin criado com sucesso!");
        } else {
            System.out.println("Admin ja existente!");
        }
    }

}
