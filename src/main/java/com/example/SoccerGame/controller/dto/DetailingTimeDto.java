package com.example.SoccerGame.controller.dto;

import com.example.SoccerGame.models.Time;

public record DetailingTimeDto(String login) {
    public DetailingTimeDto(Time time) {
        this(time.toString());
    }
}
