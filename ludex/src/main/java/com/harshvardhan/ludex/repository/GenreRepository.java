package com.harshvardhan.ludex.repository;

import com.harshvardhan.ludex.model.genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<genre, Integer> {
    
}
