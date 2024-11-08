package es.deusto.sd.strava.facade;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.deusto.sd.strava.dto.LoginDTO;
import es.deusto.sd.strava.dto.UserDTO;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Controller", description = "User authentication operations")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        User user = new User(userDTO);
        Optional<User> registeredUser = authService.register(user);
        if (registeredUser.isPresent()) {
            return new ResponseEntity<>("User registered successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Registration failed. User may already exist.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    @Operation(summary = "User login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        Optional<String> tokenOpt = authService.login(loginDTO.getEmail(), loginDTO.getPass());
        if (tokenOpt.isPresent()) {
            return new ResponseEntity<>(tokenOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login failed. Invalid credentials.", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    @Operation(summary = "User logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        boolean result = authService.logout(token);
        if (result) {
            return new ResponseEntity<>("Logout successful.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid token.", HttpStatus.UNAUTHORIZED);
        }
    }
}
