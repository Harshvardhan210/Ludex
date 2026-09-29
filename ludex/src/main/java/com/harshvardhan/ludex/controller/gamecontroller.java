package com.harshvardhan.ludex.controller;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshvardhan.ludex.dto.GameRequestDTO;
import com.harshvardhan.ludex.dto.GameResponseDTO;

import com.harshvardhan.ludex.service.gameservice;

import jakarta.validation.Valid;

/**
 * REST Controller for managing game-related API endpoints.
 * Base URL: /game
 */



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
    public List<GameResponseDTO> getAllGames() {

        return gameService.getAllGames();
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