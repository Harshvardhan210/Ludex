package com.harshvardhan.ludex.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController 
@RequestMapping("/home")
public class healthcontroller {
    
   
    @GetMapping 
    public String getmassage(){
        return "hi this program works!";
    }
}
