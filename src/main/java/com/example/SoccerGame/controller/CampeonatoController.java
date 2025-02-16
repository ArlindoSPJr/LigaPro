package com.example.SoccerGame.controller;

import com.example.SoccerGame.controller.dto.CreateCampeonatoDto;
import com.example.SoccerGame.controller.dto.DetailingJogadorDto;
import com.example.SoccerGame.controller.dto.DetailingoCampeonatoDto;
import com.example.SoccerGame.models.Campeonato;
import com.example.SoccerGame.service.CampeonatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/campeonato")
public class CampeonatoController {

    private final CampeonatoService campeonatoService;

    public CampeonatoController(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    @GetMapping
    public List<Campeonato> getCampeonatos() {
        return campeonatoService.getAllCampeonato();
    }

    @PostMapping
    public ResponseEntity<DetailingoCampeonatoDto> saveCampeonato(@RequestBody CreateCampeonatoDto dto, UriComponentsBuilder uriBuilder) {
        Campeonato campeonato = new Campeonato(dto);
        campeonatoService.saveCampeonato(campeonato);

        var uri = uriBuilder.path("/campeonato/{campeonatoId}").buildAndExpand(campeonato.getCampeonatoId()).toUri();

        return ResponseEntity.created(uri).body(new DetailingoCampeonatoDto(campeonato));

    }
}
