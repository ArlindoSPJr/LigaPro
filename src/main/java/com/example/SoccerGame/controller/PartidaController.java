package com.example.SoccerGame.controller;

import com.example.SoccerGame.controller.dto.CreatePartidaDto;
import com.example.SoccerGame.controller.dto.DetailingPartidaDto;
import com.example.SoccerGame.models.Partida;
import com.example.SoccerGame.models.Time;
import com.example.SoccerGame.repository.TimeRepository;
import com.example.SoccerGame.service.PartidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/partida")
public class PartidaController {

    private final PartidaService partidaService;
    private final TimeRepository timeRepository;
    public PartidaController(PartidaService partidaService, TimeRepository timeRepository) {
        this.partidaService = partidaService;
        this.timeRepository = timeRepository;
    }

    @GetMapping
    public List<Partida> getPartidas(){
        return partidaService.getPartidas();
    }

    @GetMapping("/data/{data}")
    public List<Partida> getPartidaComData(@PathVariable LocalDate data){
        return partidaService.getPartidasComData(data);
    }

    @GetMapping("/time/{timeId}")
    public List<Partida> getPartidaDoTime(@PathVariable Long timeId){

        return partidaService.getPartidasDoTime(timeId);
    }

    @PostMapping
    public ResponseEntity<DetailingPartidaDto> savePartida(@RequestBody CreatePartidaDto partidaDto, UriComponentsBuilder uriBuilder){

        Time timeMandanteEncontrado = timeRepository.findByNome(partidaDto.timeMandante());
        Time timeVisitanteEncontrado = timeRepository.findByNome(partidaDto.timeVisitante());

        if (timeMandanteEncontrado == null && timeVisitanteEncontrado == null) {
            return ResponseEntity.notFound().build();
        }

        Partida partida = new Partida(timeMandanteEncontrado,timeVisitanteEncontrado,partidaDto.hora(),partidaDto.data());
        partidaService.savePartida(partida);

        var uri = uriBuilder.path("/partida/{partidaId}").buildAndExpand(partida.getPartidaId()).toUri();

        return ResponseEntity.created(uri).body(new DetailingPartidaDto(partida));

    }


}
