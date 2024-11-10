package es.deusto.sd.strava.service;

import org.springframework.stereotype.Service;

import es.deusto.sd.strava.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    // Simulate a user repository
    private final Map<String, User> userRepository = new HashMap<>();

    // Store tokens and associated users
    private final Map<String, User> tokenStore = new HashMap<>();

    // Method to register a user
    public Optional<User> register(User user) {
        if (user == null || user.getEmail() == null) {
            return Optional.empty();
        }
        if (userRepository.containsKey(user.getEmail())) {
            // User already exists
            return Optional.empty();
        } else {
            // Add the user to the simulated repository
            userRepository.put(user.getEmail(), user);
            return Optional.of(user);
        }
    }

    // Login method that validates email and password
    public Optional<String> login(String email, String password) {
        User user = userRepository.get(email);

        if (user != null && user.getPassword().equals(password)) {
            String token = generateToken();
            tokenStore.put(token, user);
            return Optional.of(token);
        } else {
            return Optional.empty();
        }
    }

    // Method to log out
    public boolean logout(String token) {
        if (tokenStore.containsKey(token)) {
            tokenStore.remove(token);
            return true;
        } else {
            return false;
        }
    }

    // Get user by token
    public Optional<User> getUserByToken(String token) {
        return Optional.ofNullable(tokenStore.get(token));
    }

    // Get user by email
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.get(email));
    }

    // Generate a unique token
    private synchronized String generateToken() {
        return Long.toHexString(System.currentTimeMillis()) + Double.toHexString(Math.random());
    }
}
