package es.deusto.sd.strava.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import es.deusto.sd.strava.dto.ChallengeDTO;
import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.TrainingSession;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {

    private final List<Challenge> challenges = new ArrayList<>();

    // Map to store challenges accepted by user (email)
    private final Map<String, List<Challenge>> acceptedChallengesByUser = new HashMap<>();

    // Create a challenge
    public void createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = new Challenge(
            challengeDTO.getName(),
            challengeDTO.getStartDate(),
            challengeDTO.getEndDate(),
            challengeDTO.getTargetDistance(),
            challengeDTO.getTargetTime(),
            challengeDTO.getSport()
        );
        double target = challenge.getTargetDistance() != null ? challenge.getTargetDistance() : challenge.getTargetTime();
        if (target <= 0) {
            throw new IllegalArgumentException("Challenge target must be greater than 0.");
        }
        challenges.add(challenge);
    }

    // Get active challenges
    public List<Challenge> getActiveChallenges() {
        Date now = new Date();
        return challenges.stream()
                .filter(challenge -> challenge.getEndDate().after(now))
                .collect(Collectors.toList());
    }

    // Get active challenges with optional filters
    public List<Challenge> getActiveChallenges(Date startDate, Date endDate, String sport) {
        Date now = new Date();
        return challenges.stream()
                .filter(challenge -> challenge.getEndDate().after(now))
                .filter(challenge -> startDate == null || !challenge.getStartDate().before(startDate))
                .filter(challenge -> endDate == null || !challenge.getEndDate().after(endDate))
                .filter(challenge -> sport == null || challenge.getSport().equalsIgnoreCase(sport))
                .collect(Collectors.toList());
    }

    // Accept a challenge
    public boolean acceptChallenge(String email, String challengeName) {
        Challenge challenge = challenges.stream()
                .filter(ch -> ch.getName().equals(challengeName))
                .findFirst()
                .orElse(null);
        if (challenge != null) {
            acceptedChallengesByUser
                    .computeIfAbsent(email, k -> new ArrayList<>())
                    .add(challenge);
            return true;
        } else {
            return false;
        }
    }

    // View challenges accepted by the user
    public List<Challenge> getAcceptedChallenges(String email) {
        return acceptedChallengesByUser.getOrDefault(email, new ArrayList<>());
    }

    // Check the status of an accepted challenge
    public double checkChallengeStatus(String email, String challengeName, List<TrainingSession> sessions) {
        Challenge challenge = challenges.stream()
                .filter(ch -> ch.getName().equals(challengeName))
                .findFirst()
                .orElse(null);

        if (challenge != null && acceptedChallengesByUser.getOrDefault(email, new ArrayList<>()).contains(challenge)) {
            // Filter relevant sessions
            List<TrainingSession> relevantSessions = sessions.stream()
                    .filter(session -> session.getSport().equalsIgnoreCase(challenge.getSport()))
                    .filter(session -> !session.getStartDate().before(challenge.getStartDate()) && !session.getStartDate().after(challenge.getEndDate()))
                    .collect(Collectors.toList());

            double progress = 0.0;

            if (challenge.getTargetDistance() != null) {
                // Accumulate distance
                double totalDistance = relevantSessions.stream()
                        .mapToDouble(TrainingSession::getDistanceKm)
                        .sum();

                if (challenge.getTargetDistance() != 0) {
                    progress = (totalDistance / challenge.getTargetDistance()) * 100;
                } else {
                    throw new IllegalArgumentException("Challenge target distance cannot be zero.");
                }
            } else if (challenge.getTargetTime() != null) {
                // Accumulate time
                double totalTime = relevantSessions.stream()
                        .mapToDouble(TrainingSession::getDurationMin)
                        .sum();

                if (challenge.getTargetTime() != 0) {
                    progress = (totalTime / challenge.getTargetTime()) * 100;
                } else {
                    throw new IllegalArgumentException("Challenge target time cannot be zero.");
                }
            } else {
                // Invalid challenge configuration
                throw new IllegalStateException("Invalid challenge configuration: both targetDistance and targetTime are null.");
            }

            return Math.min(progress, 100.0);
        } else {
        	// Challenge not found or not accepted by user
        	throw new IllegalArgumentException("Challenge not found or not accepted by user.");
        }
    }
}
