package es.deusto.sd.strava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.deusto.sd.strava.dao.UserRepository;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.external.AuthenticationInterface;
import es.deusto.sd.strava.external.FacebookGateway;
import es.deusto.sd.strava.external.GoogleGateway;
import es.deusto.sd.strava.external.factory.AuthenticationProviderFactory;

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
    
    public AuthenticationInterface createProviderGateway(String providerType) {
        AuthenticationInterface gateway;

        switch (providerType.toLowerCase()) {
            case "google":
                gateway = googleGateway;
                break;
            case "facebook":
                gateway = facebookGateway;
                break;
            default:
                throw new IllegalArgumentException("Unsupported provider type: " + providerType);
        }

        return gateway;
    }
    
    public String login(String email, String password) {
        // Fetch the user from the database
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Use the factory to get the appropriate gateway
        AuthenticationInterface gateway = AuthenticationProviderFactory.createProviderGateway(user.getAccountType());

        // Validate credentials
        if (!gateway.validate(email, password)) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        // Generate token
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