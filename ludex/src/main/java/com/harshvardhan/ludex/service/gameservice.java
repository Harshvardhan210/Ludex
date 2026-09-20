package com.harshvardhan.ludex.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;

import com.harshvardhan.ludex.model.game;

/**
 * Service class containing the business logic for game management.
 * Uses an in-memory ArrayList to store game data (no database required).
 */
@Service
public class gameservice {

    /** In-memory list that stores all game entries. */
    public List<game> Games = new ArrayList<>();

    /**
     * Returns all games in the in-memory list.
     *
     * @return a {@link List} of all {@link game} objects
     */
    public List<game> getallgames() {
        return Games;
    }

    /**
     * Adds a new game to the in-memory list.
     *
     * @param g the {@link game} object to add
     * @return the added {@link game} object
     */
    public game addGames(game g) {
        Games.add(g);
        return g;
    }

  public boolean deleteGame(int id) {

    for (game g : Games) {

        if (g.getGame_id() == id) {
            Games.remove(g);
            return true;
        }
    }

    return false;
}



}
