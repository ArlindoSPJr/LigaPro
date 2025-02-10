package com.example.SoccerGame.controller;

import com.example.SoccerGame.controller.dto.AdminTimeDetailingDto;
import com.example.SoccerGame.controller.dto.CreateAdminTime;
import com.example.SoccerGame.models.AdminTime;
import com.example.SoccerGame.service.AdminTimeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adminTime")
public class AdminTimeController {

    private final AdminTimeService adminTimeService;

    public AdminTimeController(AdminTimeService adminTimeService) {
        this.adminTimeService = adminTimeService;
    }

    @GetMapping
    public List<AdminTime> getAll() {
        return adminTimeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminTimeDetailingDto> getAdminTimeById(@PathVariable Long id) {
        AdminTime novoAdmin = adminTimeService.getById(id);
        if (novoAdmin == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new AdminTimeDetailingDto(novoAdmin));
    }

    @PostMapping
    public ResponseEntity<AdminTimeDetailingDto> createAdminTime(@RequestBody @Valid CreateAdminTime createAdminTime,
                                                                 UriComponentsBuilder uriBuilder) {
        AdminTime adminTime = new AdminTime(createAdminTime);
        adminTimeService.save(adminTime);

        var uri = uriBuilder.path("/adminTime/{id}").buildAndExpand(adminTime.getAdminId()).toUri();

        return ResponseEntity.created(uri).body(new AdminTimeDetailingDto(adminTime));

    }
}
