package com.harshvardhan.ludex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshvardhan.ludex.model.game;

public interface GameRepository extends JpaRepository<game, Integer>{

    List<game> findBySection(String section);


    

    
}
