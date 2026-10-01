package com.harshvardhan.ludex.controller;

import java.util.List;

import com.harshvardhan.ludex.dto.GameRequestDTO;
import com.harshvardhan.ludex.dto.GameResponseDTO;

import com.harshvardhan.ludex.service.gameservice;

import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/games")
public class gamecontroller {

    private final gameservice gameService;


    public gamecontroller(gameservice gameService) {
        this.gameService = gameService;
    }


    // CREATE
    @PostMapping
    public GameResponseDTO addGame(
            @Valid @RequestBody GameRequestDTO dto) {

        return gameService.addGame(dto);
    }


    // GET ALL
    @GetMapping
    public List<GameResponseDTO> getAllGames(Pageable pageable) {

        return gameService.getAllGames(pageable);
    }


    // GET BY ID
    @GetMapping("/{id}")
    public GameResponseDTO getGameById(
            @PathVariable int id) {

        return gameService.getGameById(id);
    }


    // UPDATE
    @PutMapping("/{id}")
    public GameResponseDTO updateGame(
            @PathVariable int id,
            @Valid @RequestBody GameRequestDTO dto) {

        return gameService.updateGame(id, dto);
    }
}