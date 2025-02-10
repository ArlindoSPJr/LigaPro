package com.example.SoccerGame.service;

import com.example.SoccerGame.exceptions.ResourceNotFoundException;
import com.example.SoccerGame.models.AdminTime;
import com.example.SoccerGame.repository.AdminTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminTimeService {

    @Autowired
    private AdminTimeRepository adminTimeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<AdminTime> getAll() {
        return adminTimeRepository.findAll();
    }

    public AdminTime getById(Long id) {
        return adminTimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User nao encontrado com id" + id));
    }

    public void save(AdminTime adminTime) {

        if (adminTimeRepository.existsAdminTimesByLogin(adminTime.getLogin())){
            throw new ResourceNotFoundException("Admin com login ja cadastrado!");
        }

        String senhaCriptografada = passwordEncoder.encode(adminTime.getPassword());
        adminTime.setPassword(senhaCriptografada);
        adminTimeRepository.save(adminTime);
    }

    public void deleteById(Long id) {
        adminTimeRepository.deleteById(id);
    }
}
