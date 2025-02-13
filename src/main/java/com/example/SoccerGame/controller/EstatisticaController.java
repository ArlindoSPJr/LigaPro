package com.example.SoccerGame.controller;

import com.example.SoccerGame.controller.dto.UpdateEstatisticasDto;
import com.example.SoccerGame.exceptions.ResourceNotFoundException;
import com.example.SoccerGame.service.EstatisticasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final EstatisticasService estatisticasService;

    public EstatisticaController(EstatisticasService estatisticasService) {
        this.estatisticasService = estatisticasService;
    }

    @PostMapping("/{jogadorId}")
    public ResponseEntity addEstatisticaParaJogador(@PathVariable Long jogadorId, @RequestBody UpdateEstatisticasDto dto, UriComponentsBuilder uriComponentsBuilder) {

        if (!estatisticasService.addGolParaJogador(dto, jogadorId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
        
    }
}
