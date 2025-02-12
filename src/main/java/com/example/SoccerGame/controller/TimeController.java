package com.example.SoccerGame.controller;

import com.example.SoccerGame.controller.dto.CreateTimeDto;
import com.example.SoccerGame.controller.dto.DetailingTimeDto;
import com.example.SoccerGame.models.Jogador;
import com.example.SoccerGame.models.Time;
import com.example.SoccerGame.service.TimeService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/time")
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping
    public List<Time> getTimes() {
        return timeService.getAllTimes();
    }

    @GetMapping("/nome")
    public ResponseEntity<DetailingTimeDto> getTimeByName(@RequestBody CreateTimeDto dto){
        Time time = timeService.getTimeByName(dto.nome());
        if (time == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DetailingTimeDto(time));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailingTimeDto> getTimeById(@PathVariable Long id){
        Time time = timeService.getTimeById(id);
        if (time == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DetailingTimeDto(time));
    }

    @PostMapping
    public ResponseEntity<DetailingTimeDto> createTime(@RequestBody CreateTimeDto dto, UriComponentsBuilder uriBuilder){
        Time time = new Time(dto);
        timeService.save(time);

        var uri = uriBuilder.path("/time/{id}").buildAndExpand(time.getTimeId()).toUri();

        return ResponseEntity.created(uri).body(new DetailingTimeDto(time));
    }

    @PostMapping("/{timeId}/{jogadorId}")
    public ResponseEntity<DetailingTimeDto> addPlayerForTeam(@PathVariable Long timeId, @PathVariable Long jogadorId, UriComponentsBuilder uriBuilder) {
        Time time = timeService.adicionarJogadorAoTime(timeId, jogadorId);

        var uri = uriBuilder.path("/time/{id}").buildAndExpand(time.getTimeId()).toUri();

        return ResponseEntity.created(uri).body(new DetailingTimeDto(time));
    }

}
