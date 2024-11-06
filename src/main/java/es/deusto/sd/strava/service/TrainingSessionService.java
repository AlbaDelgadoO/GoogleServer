package es.deusto.sd.strava.service;

import java.util.ArrayList;
import java.util.List;

import es.deusto.sd.strava.dto.TrainingSessionDTO;
import es.deusto.sd.strava.entity.TrainingSession;
import org.springframework.stereotype.Service;

@Service
public class TrainingSessionService {

    private final List<TrainingSession> sessions = new ArrayList<>();

    // Simulates the creation of a trining session
    public void createTrainingSession(TrainingSessionDTO sessionDTO) {
        TrainingSession session = new TrainingSession(
            sessionDTO.getTitle(),
            sessionDTO.getSport(),
            sessionDTO.getDistance(),
            sessionDTO.getStartDate(),
            sessionDTO.getStartTime(),
            sessionDTO.getDuration()
        );
        sessions.add(session);
    }

    // Simulates a consult to the training sessions
    public List<TrainingSession> getTrainingSessions(String startDate, String endDate) {
        // Aggregate logic to filter sessions by date
        return sessions;
    }
}
