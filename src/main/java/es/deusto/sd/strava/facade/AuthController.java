package es.deusto.sd.strava.facade;
import java.awt.print.Printable;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.deusto.sd.strava.dto.LoginDTO;
import es.deusto.sd.strava.dto.UserDTO;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Controller", description = "User authentication operations")
public class AuthController {

    @Autowired
    private AuthService authService;
    /***
    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
    	System.out.println("Received userDTO: " + userDTO.toString());
    	User user = new User(userDTO);
        Optional<User> registeredUser = authService.register(user);
        System.out.println(registeredUser.isEmpty());
        if (registeredUser.isPresent()) {
            return new ResponseEntity<>("User registered successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Registration failed. User may already exist.", HttpStatus.BAD_REQUEST);
        }
    }
    ***/
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Parameter(name = "name", description = "Name of the user", required = true, example = "Luis")
            @RequestParam("name") String name,
            @Parameter(name = "email", description = "Email of the user", required = true, example = "luis@example.com")
            @RequestParam("email") String email,
            @Parameter(name = "password", description = "Password for the user", required = true, example = "pass1")
            @RequestParam("password") String password,
            @Parameter(name = "birthdate", description = "Birthdate of the user", required = true, example = "1990-05-20")
            @RequestParam("birthdate") String birthdate,
            @Parameter(name = "weight", description = "Weight of the user", required = true, example = "70kg")
            @RequestParam("weight") String weight,
            @Parameter(name = "height", description = "Height of the user", required = true, example = "1.75m")
            @RequestParam("height") String height,
            @Parameter(name = "maxHR", description = "Maximum heart rate", required = true, example = "180")
            @RequestParam("maxHR") int maxHR,
            @Parameter(name = "restHR", description = "Resting heart rate", required = true, example = "60")
            @RequestParam("restHR") int restHR){
        
        try {
        	User user = new User(name, birthdate, height, weight, maxHR, restHR, email, password);
            Optional<User> registeredUser = authService.register(user);
            if (registeredUser.isPresent()) {
                return new ResponseEntity<>("User registered successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Registration failed. User may already exist.", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /***
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
	***/
    @PostMapping("/login")
    @Operation(summary = "User login")
    public ResponseEntity<String> login(
    		            @Parameter(name = "email", description = "Email of the user", required = true, example = "luis@example.com")
    		            @RequestParam("email") String email,
    		            @Parameter(name = "password", description = "Password for the user", required = true, example = "pass1")
    		            @RequestParam("password") String password) {
    	
        Optional<String> tokenOpt = authService.login(email, password);
        if (tokenOpt.isPresent()) {
            return new ResponseEntity<>("User has login successfully. Token: " + tokenOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login failed. Invalid credentials.", HttpStatus.UNAUTHORIZED);
        }
    }
    /*
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
    */
    @PostMapping("/logout")
    @Operation(summary = "User logout")
    public ResponseEntity<String> logout(
			@Parameter(name = "token", description = "Token for the user", required = true, example = "Bearer token") 
			@RequestHeader("token") String token
    		) {
        boolean result = authService.logout(token);
        if (result) {
            return new ResponseEntity<>("Logout successful.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid token.", HttpStatus.UNAUTHORIZED);
        }
    }
}
