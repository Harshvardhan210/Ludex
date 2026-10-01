package com.harshvardhan.ludex.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import com.harshvardhan.ludex.service.platformService;

import com.harshvardhan.ludex.model.Platform;

@RestController
@RequestMapping("/platforms")
public class PlatformController {

    private final platformService platformService;

    public PlatformController(platformService platformService) {
        this.platformService = platformService;
    }

    @PostMapping
    public Platform addPlatform(@RequestBody Platform platform) {
        return platformService.addPlatform(platform);
    }

    @GetMapping
    public List<Platform> getAllPlatforms() {
        return platformService.getAllPlatforms();
    }
    
}