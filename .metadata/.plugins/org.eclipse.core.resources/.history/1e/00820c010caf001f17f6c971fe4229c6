package es.deusto.sd.strava.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.TrainingSession;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {
	//METHODS
	// Encontrar sesiones por usuario y rango de fechas
    List<TrainingSession> findByUserEmailAndStartDateBetween(String email, Date startDate, Date endDate);

    // Obtener las últimas 5 sesiones de un usuario
    List<TrainingSession> findTop5ByUserEmailOrderByStartDateDesc(String email);

    // Obtener todas las sesiones de un usuario
    List<TrainingSession> findByUserEmail(String email);
}
