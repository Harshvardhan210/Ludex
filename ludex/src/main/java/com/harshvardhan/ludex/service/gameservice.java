package com.harshvardhan.ludex.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.harshvardhan.ludex.model.game;

@Service 
public class gameservice {

   public List<game> Games = new ArrayList<>();

   public List<game> getAllgame(){
    return Games;
   }

   public game addGames(game g){
    Games.add(g);
    return g;
   }
 
   public boolean deletegames(int id){

    for(game game : Games){
        if(game.getGame_id() == id){
            Games.remove(game);
            return true;
        }
    }
    return false;
   }
}
