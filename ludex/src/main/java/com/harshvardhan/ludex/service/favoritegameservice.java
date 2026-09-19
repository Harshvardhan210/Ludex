package com.harshvardhan.ludex.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harshvardhan.ludex.model.game;


@Service 
public class favoritegameservice {

    @Autowired 
    private gameservice gameservice;

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

   
}

    

