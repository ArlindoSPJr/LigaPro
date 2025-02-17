package com.example.SoccerGame.controller;

import com.example.SoccerGame.controller.dto.CreatePartidaDto;
import com.example.SoccerGame.controller.dto.DetailingPartidaDto;
import com.example.SoccerGame.exceptions.ResourceNotFoundException;
import com.example.SoccerGame.models.Campeonato;
import com.example.SoccerGame.models.Partida;
import com.example.SoccerGame.models.Time;
import com.example.SoccerGame.repository.TimeRepository;
import com.example.SoccerGame.service.CampeonatoService;
import com.example.SoccerGame.service.PartidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/partida")
public class PartidaController {

    private final PartidaService partidaService;
    private final TimeRepository timeRepository;
    private final CampeonatoService campeonatoService;
    public PartidaController(PartidaService partidaService, TimeRepository timeRepository, CampeonatoService campeonatoService) {
        this.partidaService = partidaService;
        this.timeRepository = timeRepository;
        this.campeonatoService = campeonatoService;
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
    public ResponseEntity<DetailingPartidaDto> savePartida(@RequestBody CreatePartidaDto partidaDto, UriComponentsBuilder uriBuilder) {

        Campeonato campeonato = campeonatoService.getCampeonatoById(partidaDto.campeonatoId());

        if (campeonato == null) {
            throw new ResourceNotFoundException("Campeonato não encontrado");
        }

        Time timeMandanteEncontrado = timeRepository.findByNome(partidaDto.timeMandante());
        Time timeVisitanteEncontrado = timeRepository.findByNome(partidaDto.timeVisitante());

        if (timeMandanteEncontrado == null || timeVisitanteEncontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        URI uri;
        Partida partida;

        try {
            partida = new Partida(campeonato, timeMandanteEncontrado, timeVisitanteEncontrado, partidaDto.hora(), partidaDto.data());
            partidaService.savePartida(partida);
            uri = uriBuilder.path("/partida/{partidaId}").buildAndExpand(partida.getPartidaId()).toUri();
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.created(uri).body(new DetailingPartidaDto(partida));
    }



}
