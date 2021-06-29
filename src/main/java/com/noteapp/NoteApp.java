package com.noteapp;

import com.noteapp.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NoteApp {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class);
    }
}
