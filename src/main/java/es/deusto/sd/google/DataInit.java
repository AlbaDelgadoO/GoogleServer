package es.deusto.sd.google;

import es.deusto.sd.google.entity.GoogleUser;
import es.deusto.sd.google.repository.GoogleUserRepository;
import es.deusto.sd.google.service.GoogleAuthService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInit {

    @Bean
    CommandLineRunner initData(GoogleAuthService authService) {
        return args -> {
            try {
                authService.register(new GoogleUser("user1@gmail.com", "password1"));
                authService.register(new GoogleUser("user2@gmail.com", "password2"));
            } catch (Exception e) {
                System.err.println("Error initializing data: " + e.getMessage());
            }
        };
    }
}
