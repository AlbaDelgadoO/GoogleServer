package es.deusto.sd.strava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.deusto.sd.strava.dao.UserRepository;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.external.FacebookGateway;
import es.deusto.sd.strava.external.GoogleGateway;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FacebookGateway facebookGateway;

    @Autowired
    private GoogleGateway googleGateway;

    private final Map<String, User> tokenStore = new HashMap<>();

    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        return userRepository.save(user);
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        boolean isValid = switch (user.getAccountType().toLowerCase()) {
            case "google" -> googleGateway.validate(email, password);
            case "facebook" -> facebookGateway.validate(email, password);
            default -> throw new IllegalArgumentException("Unsupported account type");
        };

        if (!isValid) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = generateToken();
        tokenStore.put(token, user);
        return token;
    }

    public boolean logout(String token) {
        return tokenStore.remove(token) != null;
    }

    public Optional<User> getUserByToken(String token) {
        return Optional.ofNullable(tokenStore.get(token));
    }

    private synchronized String generateToken() {
        return Long.toHexString(System.currentTimeMillis()) + Double.toHexString(Math.random());
    }
}