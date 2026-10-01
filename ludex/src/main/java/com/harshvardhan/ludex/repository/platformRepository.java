package com.harshvardhan.ludex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshvardhan.ludex.model.Platform;

public interface platformRepository extends JpaRepository<Platform, Integer> {
    
}
