package com.harshvardhan.ludex.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.harshvardhan.ludex.exception.GameNotFoundException;
import com.harshvardhan.ludex.model.game;
import com.harshvardhan.ludex.repository.GameRepository;

/**
 * Service class containing the business logic for game management.
 * Uses an in-memory ArrayList to store game data (no database required).
 */
@Service
public class gameservice {

    private final GameRepository gameRepository;

    public gameservice(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // public List<game> Games = new ArrayList<>();

    public List<game> getallgames() {
        return gameRepository.findAll();
    }

    public game addGames(game g) {
        return gameRepository.save(g);
    }

    public game findgame(int id){
        
        return gameRepository.findById(id).orElseThrow(() -> 
          new GameNotFoundException( "Game with ID " + id + " not found") );
    }

    public boolean deleteGame(int id) {

        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return true;

        }
        return false;

    }
}