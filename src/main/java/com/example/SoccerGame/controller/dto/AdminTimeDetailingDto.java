package com.example.SoccerGame.controller.dto;

import com.example.SoccerGame.models.AdminTime;

public record AdminTimeDetailingDto(String login, String password) {
    public AdminTimeDetailingDto(AdminTime adminTime) {
        this(adminTime.getLogin(), adminTime.getPassword());
    }
}
