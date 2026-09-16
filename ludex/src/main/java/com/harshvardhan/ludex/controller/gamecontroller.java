package com.harshvardhan.ludex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshvardhan.ludex.model.game;
import com.harshvardhan.ludex.service.gameservice;

/**
 * REST Controller for managing game-related API endpoints.
 * Base URL: /game
 */
@RestController
@RequestMapping("/game")
public class gamecontroller {

  /** Injects the game service to handle business logic. */
  @Autowired
  private gameservice gameservice;

  /**
   * Retrieves all games stored in the in-memory list.
   *
   * @return a list of all {@link game} objects
   */
  @GetMapping("/allgames")
  public List<game> getAllGames() {
    return gameservice.getallgames();
  }

  /**
   * Adds a new game to the in-memory list.
   *
   * @param g the {@link game} object received in the request body
   * @return the added {@link game} object
   */
  @PostMapping("/addgames")
  public game addgames(@RequestBody game g) {
    gameservice.addGames(g);
    return g;
  }

  /**
   * Deletes a game by its ID.
   *
   * @param id the ID of the game to be deleted (from the URL path)
   * @return {@code true} if the game was deleted, {@code false} otherwise
   */
  @DeleteMapping("/{id}")
  public boolean deletegame(@PathVariable int id) {
    return gameservice.deletegame(id);
  }

}
