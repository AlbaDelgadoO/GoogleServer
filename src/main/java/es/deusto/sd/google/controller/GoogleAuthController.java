package es.deusto.sd.google.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.deusto.sd.google.entity.GoogleUser;
import es.deusto.sd.google.repository.GoogleUserRepository;

@RestController
@RequestMapping("/google")
public class GoogleAuthController {

    private final GoogleUserRepository userRepository;

    public GoogleAuthController(GoogleUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestParam String email, @RequestParam String password) {
    	Optional<GoogleUser> user = userRepository.findByEmail(email);
    	if (user.isPresent() && user.get().getPassword().equals(password)) {
    	    return ResponseEntity.ok(true);
    	}
        return ResponseEntity.ok(false);
        
    }
}//hola