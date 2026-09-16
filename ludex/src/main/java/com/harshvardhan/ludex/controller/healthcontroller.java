package com.harshvardhan.ludex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health check controller to verify the application is running.
 * Base URL: /home
 */
@RestController
@RequestMapping("/home")
public class healthcontroller {

    /**
     * Simple health check endpoint.
     *
     * @return a confirmation message indicating the server is up
     */
    @GetMapping
    public String getMessage() {
        return "Hi, this program works!";
    }
}
