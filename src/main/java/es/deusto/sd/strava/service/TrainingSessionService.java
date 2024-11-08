package es.deusto.sd.strava.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import es.deusto.sd.strava.dto.TrainingSessionDTO;
import es.deusto.sd.strava.entity.TrainingSession;
import org.springframework.stereotype.Service;

@Service
public class TrainingSessionService {

    // Store sessions by user (email)
    private final Map<String, List<TrainingSession>> sessionsByUser = new HashMap<>();

    // Create a training session
    public void createTrainingSession(String email, TrainingSessionDTO sessionDTO) {
        TrainingSession session = new TrainingSession(
            sessionDTO.getTitle(),
            sessionDTO.getSport(),
            sessionDTO.getDistance(),
            sessionDTO.getStartDate(),
            sessionDTO.getStartTime(),
            sessionDTO.getDuration()
        );
        sessionsByUser.computeIfAbsent(email, k -> new ArrayList<>()).add(session);
    }

    // Get the user's last 5 sessions
    public List<TrainingSession> getRecentTrainingSessions(String email) {
        List<TrainingSession> sessions = sessionsByUser.getOrDefault(email, new ArrayList<>());
        return sessions.stream()
                .sorted((s1, s2) -> s2.getStartDate().compareTo(s1.getStartDate()))
                .limit(5)
                .collect(Collectors.toList());
    }

    // Get sessions within a date range
    public List<TrainingSession> getTrainingSessionsByDate(String email, Date startDate, Date endDate) {
        List<TrainingSession> sessions = sessionsByUser.getOrDefault(email, new ArrayList<>());
        return sessions.stream()
                .filter(session -> !session.getStartDate().before(startDate) && !session.getStartDate().after(endDate))
                .collect(Collectors.toList());
    }

    // Get all user sessions
    public List<TrainingSession> getAllTrainingSessions(String email) {
        return sessionsByUser.getOrDefault(email, new ArrayList<>());
    }
}
