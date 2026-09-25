package com.harshvardhan.ludex.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshvardhan.ludex.dto.GameRequestDTO;
import com.harshvardhan.ludex.dto.GameResponseDTO;
import com.harshvardhan.ludex.service.favoritegameservice;
import com.harshvardhan.ludex.service.gameservice;

import jakarta.validation.Valid;

/**
 * REST Controller for managing game-related API endpoints.
 * Base URL: /game
 */
@RestController
@RequestMapping("/game")
public class gamecontroller {

  /** Injects the game service to handle business logic. */
  private final gameservice gameservice;

  private final favoritegameservice fav_game;

  gamecontroller(gameservice gameservice, favoritegameservice fav_game) {
    this.gameservice = gameservice;
    this.fav_game = fav_game;
  }

  /** Returns all games. */
  @GetMapping("/allgames")
  public ResponseEntity<List<GameResponseDTO>> getAllGames() {
    return ResponseEntity.ok(gameservice.getallgames());
  }

  /** Adds a new game. */
  @PostMapping("/addgames")
  public ResponseEntity<GameResponseDTO> addgames(@Valid @RequestBody GameRequestDTO dto) {
    GameResponseDTO saved = gameservice.addGames(dto);
    return ResponseEntity.ok(saved);
  }

  /** Gets a single game by ID. */
  @GetMapping("/{id}")
  public ResponseEntity<GameResponseDTO> getgamebyid(@PathVariable int id) {
    return ResponseEntity.ok(gameservice.findgame(id));
  }

  /** Deletes a game by ID. */
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletegame(@PathVariable int id) {
    boolean deleted = gameservice.deleteGame(id);
    if (deleted) {
      return ResponseEntity.ok("Game is deleted");
    }
    return ResponseEntity.badRequest().body("Failed");
  }

  /** Returns all favorite games. */
  @GetMapping("/favorites")
  public ResponseEntity<List<GameResponseDTO>> getallfavgames() {
    return ResponseEntity.ok(fav_game.getallfavorite());
  }

  /** Adds a game to favorites by ID. */
  @PostMapping("/addfavorites/{game_id}")
  public ResponseEntity<String> addfavgame(@PathVariable("game_id") int id) {
    fav_game.addfavorites(id);
    return ResponseEntity.ok("Game added to Favorites");
  }

  /** Removes a game from favorites by ID. */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deletefavgames(@PathVariable int id) {
    boolean deleted = fav_game.deletefavgame(id);
    if (deleted) {
      return ResponseEntity.ok("Game removed from Favorites");
    }
    return ResponseEntity.badRequest().body("Failed");
  }
}
