package es.deusto.sd.strava.service;

import java.util.ArrayList;
import java.util.List;

import es.deusto.sd.strava.dto.ChallengeDTO;
import es.deusto.sd.strava.entity.Challenge;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {

    private final List<Challenge> challenges = new ArrayList<>();
    private final List<Challenge> acceptedChallenges = new ArrayList<>();

    public void createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = new Challenge(
            challengeDTO.getName(),
            challengeDTO.getStartDate(),
            challengeDTO.getEndDate(),
            challengeDTO.getTargetDistance(),
            challengeDTO.getTargetTime(),
            challengeDTO.getSport()
        );
        challenges.add(challenge);
    }

    public List<Challenge> getAcceptedChallenges(String token) {
        // Aggregate logic to filter challenges by token
        return acceptedChallenges;
    }
}
