package es.deusto.sd.strava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.external.FacebookGateway;
import es.deusto.sd.strava.external.GoogleGateway;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
	
    // Simulate a user repository
    private final Map<String, User> userRepository = new HashMap<>();

    // Store tokens and associated users
    private final Map<String, User> tokenStore = new HashMap<>();
    
    // Facebook and Google gateway clients
    @Autowired
    private FacebookGateway facebookGateway;

    @Autowired
    private GoogleGateway googleGateway;

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
        boolean isValid = false;
        // Retrieve the user by email
        Optional<User> u = getUserByEmail(email);
        User us = u.get(); 
        String authProvider = us.getAccountType(); 
        // Check if the authentication provider is Facebook
        if (authProvider.equalsIgnoreCase("facebook")) {
            isValid = facebookGateway.validate(email, password);  // Validate with Facebook
        }
        // Check if the authentication provider is Google
        else if (authProvider.equalsIgnoreCase("google")) {
            isValid = googleGateway.validate(email, password);  // Validate with Google
        }

        // If validation is successful, generate and return the token
        if (isValid) {
            // Check if user exists in the repository (or create a new User object)
            User user = userRepository.get(email);
            if (user == null) {
                user = new User();
                user.setEmail(email);
                user.setAccountType(authProvider); // Set account type (facebook or google)
                // You can optionally populate other fields if needed
                userRepository.put(email, user);  // Store the user in the repository
            }
            // Generate the token
            String token = generateToken();
            tokenStore.put(token, user);  // Store the token with associated user
            return Optional.of(token);  // Return the generated token
        } else {
            // Return empty if validation failed
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
