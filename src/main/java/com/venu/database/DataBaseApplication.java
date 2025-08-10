package com.venu.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
class DataBaseApplication{
    private final DataSource dataSource;

    public DataBaseApplication(DataSource dataSource) {this.dataSource = dataSource;}

    public static void main(String[] args) {
        SpringApplication.run(DataBaseApplication.class, args);
    }
}