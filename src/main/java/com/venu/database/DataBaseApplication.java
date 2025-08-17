package com.venu.database;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@Log
@EnableJpaRepositories(basePackages = "com.venu.database.repositories") // <-- Add this line
class DataBaseApplication{

    public static void main(String[] args) {
        SpringApplication.run(DataBaseApplication.class, args);
    }
}