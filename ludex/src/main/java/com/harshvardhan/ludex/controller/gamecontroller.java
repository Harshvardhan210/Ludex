package com.harshvardhan.ludex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshvardhan.ludex.model.game;
import com.harshvardhan.ludex.service.favoritegameservice;
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

  @Autowired 
  private favoritegameservice fav_game;

  @GetMapping("/allgames")
  public List<game> getAllGames() {
    return gameservice.getallgames();
  }

  @PostMapping("/addgames")
  public game addgames(@RequestBody game g) {
    gameservice.addGames(g);
    return g;
  }

  @DeleteMapping("/{id}")
  public String deletegame(@PathVariable int id) {
    boolean deleted = gameservice.deleteGame(id);
    if(deleted){
      return "Game is deleted";
    } 
    return "Failed";
  }


  @GetMapping("/favorites")
  public List<game> getallfavgames()
  {
    return fav_game.getallfavorite();
  }

  @PostMapping ("/addfavorites/{game_id}")
  public ResponseEntity<String> addfavgame(@PathVariable("game_id") int id){
    fav_game.addfavorites(id);
    return ResponseEntity.ok("Game Added to the favrite");

  }

  @DeleteMapping ("/delete/{id}")
  public String deletefavgames(@PathVariable int id){
    boolean deletedfav = fav_game.deletefavgame(id);
    if(deletedfav){
      return "game deleted in the Favorites";
    }
    return "Failed";
  }
  

  
 
}
