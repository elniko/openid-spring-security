package com.connect.test2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class TestApplication extends SpringBootServletInitializer {

    @Profile("user")
    @Bean
    CommandLineRunner lol(UserRepository ur) {
        return args -> {
            User user = new User();
            user.setSub("admin");
            user.setFirstName("Chuck");
            user.setLastName("Norris");
            user.getRoles().add("ROLE_ADMIN");
            user.getRoles().add("ROLE_USER");
            ur.save(user);
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
