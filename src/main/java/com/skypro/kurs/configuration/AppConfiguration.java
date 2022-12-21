package com.skypro.kurs.configuration;

import com.skypro.kurs.service.UtilService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class AppConfiguration {
    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public UtilService utilService() {
        return new UtilService();
    }
}
