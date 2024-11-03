package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;

@Configuration
public class config {

    @Bean
    CommandLineRunner commandLineRunner(
            studentRepository studentRepository){
        return args -> {
            student Awet = new student(
                    1L,
                    "Awet",
                    "awet@gmail.com",
                    LocalDate.of(2000, JANUARY,5)

            );
            student Tesfay = new student(
                    2L,
                    "Tesfay",
                    "tes@gmail.com",
                    LocalDate.of(1998,FEBRUARY,1)

            );
            studentRepository.saveAll(
                    List.of(Awet,Tesfay)
                    );
        };
    }
}
