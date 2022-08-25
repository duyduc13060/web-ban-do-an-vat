package com.junkfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AssimentJava6Application {

    public static void main(String[] args) {
        SpringApplication.run(AssimentJava6Application.class, args);
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncode(){
        return new BCryptPasswordEncoder();
    }

}
