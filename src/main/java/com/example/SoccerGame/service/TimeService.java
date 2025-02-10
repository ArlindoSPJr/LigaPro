package com.example.SoccerGame.service;

import com.example.SoccerGame.exceptions.ResourceNotFoundException;
import com.example.SoccerGame.models.Time;
import com.example.SoccerGame.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    public List<Time> getAllTimes() {
        return timeRepository.findAll();
    }

    public Time getTimeByName(String nomeTime){
        return timeRepository.findByNome(nomeTime);
    }

    public Time getTimeById(Long id){
        return timeRepository.findByTimeId(id);
    }

    public void save(Time time){
        timeRepository.save(time);
    }

    public void deleteById(long id){
        timeRepository.deleteByTimeId(id);
    }

}
