package es.deusto.sd.google.repository;

import es.deusto.sd.google.entity.GoogleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoogleUserRepository extends JpaRepository<GoogleUser, Long> {
    Optional<GoogleUser> findByEmail(String email); // Para buscar solo por email
    Optional<GoogleUser> findByEmailAndPassword(String email, String password); // Opcional según el uso
}