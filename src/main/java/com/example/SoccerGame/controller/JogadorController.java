package com.example.SoccerGame.controller;

import com.example.SoccerGame.controller.dto.CreateJogador;
import com.example.SoccerGame.controller.dto.DetailingJogadorDto;
import com.example.SoccerGame.models.Jogador;
import com.example.SoccerGame.models.Time;
import com.example.SoccerGame.service.JogadorService;
import com.example.SoccerGame.service.TimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/jogador")
public class JogadorController {

    private final JogadorService jogadorService;


    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public List<Jogador> getJogadores() {
        return jogadorService.getAll();
    }

    @GetMapping("/nome")
    public ResponseEntity<DetailingJogadorDto> findByNome(@RequestBody CreateJogador dto) {
        Jogador jogador = jogadorService.findByName(dto.nome());

        if (jogador == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DetailingJogadorDto(jogador));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailingJogadorDto> findById(@PathVariable long id) {
        Jogador jogador = jogadorService.getById(id);

        if (jogador == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DetailingJogadorDto(jogador));
    }

    @PostMapping
    public ResponseEntity<DetailingJogadorDto> create(@RequestBody CreateJogador dto, UriComponentsBuilder uriBuilder) {
        Jogador jogador = new Jogador(dto);
        jogadorService.save(jogador);

        var uri = uriBuilder.path("/jogador/{id}").buildAndExpand(jogador.getJogadorId()).toUri();

        return ResponseEntity.created(uri).body(new DetailingJogadorDto(jogador));

    }




}
