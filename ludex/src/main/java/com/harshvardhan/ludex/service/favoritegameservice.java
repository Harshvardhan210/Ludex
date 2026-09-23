package com.harshvardhan.ludex.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.harshvardhan.ludex.model.game;
import com.harshvardhan.ludex.repository.GameRepository;


@Service 
public class favoritegameservice {

    
    private final gameservice gameservice;
    private final GameRepository gameRepository;

    public favoritegameservice(gameservice gameservice, GameRepository gameRepository){
        this.gameservice = gameservice;
        this.gameRepository = gameRepository;
    }



public List<game> FavoriteGame = new ArrayList<>();

public String addfavorites(int id){
    for(game g : gameservice.getallgames()){
        if (g.getGame_id() == id){
            g.setSection("Favorite");
            FavoriteGame.add(g);
            break;
        }
    }
    return "Succes";

    }


    public List<game> getallfavorite(){
        return FavoriteGame;
    }

    public boolean deletefavgame(int id){
        for (game g : FavoriteGame){

            if (g.getGame_id() == id) {
                FavoriteGame.remove(g);
                g.setSection("Home");
                return true;    
            }
        }
        return false;
    }
   
}

    

