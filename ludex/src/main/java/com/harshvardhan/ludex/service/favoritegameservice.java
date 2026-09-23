package com.harshvardhan.ludex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.harshvardhan.ludex.model.game;
import com.harshvardhan.ludex.repository.GameRepository;

@Service
public class favoritegameservice {

    private final GameRepository gameRepository;

    public favoritegameservice(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public String addfavorites(int id) {
        game g = gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));

        g.setSection("Favorite");

        gameRepository.save(g);

        return "Success";
    }

    public List<game> getallfavorite() {
        return gameRepository.findBySection(("Favorite"));
    }

    public boolean deletefavgame(int id) {

        game g = gameRepository.findById(id).orElse(null);

        if (g == null) {
            return false;
        }

        if (!"Favorite".equals(g.getSection())) {
            return false;
        }

        g.setSection("Home");

        gameRepository.save(g);

        return true;

    }
}
