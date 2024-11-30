package es.deusto.sd.google.service;

import es.deusto.sd.google.entity.GoogleUser;
import es.deusto.sd.google.repository.GoogleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoogleAuthService {

    @Autowired
    private GoogleUserRepository userRepository;
    
    public GoogleUser register(GoogleUser user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        return userRepository.save(user);
    }

    public String login(String email, String password) {
        Optional<GoogleUser> user = userRepository.findByEmailAndPassword(email, password);
        return user.isPresent() ? generateToken() : null;
    }

    public boolean validate(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).isPresent();
    }

    private String generateToken() {
        return Long.toHexString(System.currentTimeMillis()) + Double.toHexString(Math.random());
    }
}//hola