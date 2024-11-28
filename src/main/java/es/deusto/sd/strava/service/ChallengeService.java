package es.deusto.sd.strava.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import es.deusto.sd.strava.dao.ChallengeRepository;
import es.deusto.sd.strava.dao.UserRepository;
import es.deusto.sd.strava.dto.ChallengeDTO;
import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.TrainingSession;
import es.deusto.sd.strava.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private UserRepository userRepository;

    public void createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = new Challenge(
            challengeDTO.getName(),
            challengeDTO.getStartDate(),
            challengeDTO.getEndDate(),
            challengeDTO.getTargetDistance(),
            challengeDTO.getTargetTime(),
            challengeDTO.getSport()
        );
        challengeRepository.save(challenge);
    }

    public List<ChallengeDTO> getActiveChallenges(Date startDate, Date endDate, String sport) {
        List<Challenge> challenges;

        if (sport != null && startDate != null && endDate != null) {
            challenges = challengeRepository.findByStartDateBetweenAndSport(startDate, endDate, sport);
        } else if (startDate != null && endDate != null) {
            challenges = challengeRepository.findByStartDateBetween(startDate, endDate);
        } else if (sport != null) {
            challenges = challengeRepository.findBySport(sport);
        } else {
            challenges = challengeRepository.findAll();
        }

        return challenges.stream().map(ChallengeDTO::new).collect(Collectors.toList());
    }

    public boolean acceptChallenge(String email, String challengeName) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        Optional<Challenge> challengeOpt = challengeRepository.findByName(challengeName);

        if (userOpt.isPresent() && challengeOpt.isPresent()) {
            User user = userOpt.get();
            Challenge challenge = challengeOpt.get();
            challenge.getAcceptedUsers().add(user);
            challengeRepository.save(challenge);
            return true;
        }
        return false;
    }

    public List<ChallengeDTO> getAcceptedChallenges(String email) {
        return challengeRepository.findByAcceptedUsersEmail(email)
            .stream()
            .map(ChallengeDTO::new)
            .collect(Collectors.toList());
    }
    
    public double checkChallengeStatus(String email, String challengeName, List<TrainingSession> sessions) {
        // Buscar el desafío por su nombre en el repositorio
        Optional<Challenge> challengeOpt = challengeRepository.findByName(challengeName);

        if (challengeOpt.isPresent()) {
            Challenge challenge = challengeOpt.get();

            // Verificar si el usuario ha aceptado el desafío
            boolean isAccepted = challenge.getAcceptedUsers().stream()
                    .anyMatch(user -> user.getEmail().equals(email));

            if (!isAccepted) {
                throw new IllegalArgumentException("Challenge not accepted by user.");
            }

            // Filtrar las sesiones de entrenamiento relevantes
            List<TrainingSession> relevantSessions = sessions.stream()
                    .filter(session -> session.getSport().equalsIgnoreCase(challenge.getSport())) // Coincidencia por deporte
                    .filter(session -> !session.getStartDate().before(challenge.getStartDate())
                            && !session.getStartDate().after(challenge.getEndDate())) // Rango de fechas
                    .collect(Collectors.toList());

            double progress = 0.0;

            if (challenge.getTargetDistance() != null) {
                // Acumular la distancia total
                double totalDistance = relevantSessions.stream()
                        .mapToDouble(TrainingSession::getDistanceKm)
                        .sum();

                if (challenge.getTargetDistance() != 0) {
                    progress = (totalDistance / challenge.getTargetDistance()) * 100;
                } else {
                    throw new IllegalArgumentException("Challenge target distance cannot be zero.");
                }
            } else if (challenge.getTargetTime() != null) {
                // Acumular la duración total
                double totalTime = relevantSessions.stream()
                        .mapToDouble(TrainingSession::getDurationMin)
                        .sum();

                if (challenge.getTargetTime() != 0) {
                    progress = (totalTime / challenge.getTargetTime()) * 100;
                } else {
                    throw new IllegalArgumentException("Challenge target time cannot be zero.");
                }
            } else {
                // Configuración inválida del desafío
                throw new IllegalStateException("Invalid challenge configuration: both targetDistance and targetTime are null.");
            }

            // Asegurarse de que el progreso no supere el 100%
            return Math.min(progress, 100.0);

        } else {
            throw new IllegalArgumentException("Challenge not found.");
        }
    }

}