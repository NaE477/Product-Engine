package com.portfolio.naeim;

import org.springframework.boot.SpringApplication;

public class TestNaeimApplication {

    public static void main(String[] args) {
        SpringApplication.from(ProductEngineApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
