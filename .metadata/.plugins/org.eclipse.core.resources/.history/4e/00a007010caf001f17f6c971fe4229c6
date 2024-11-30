package es.deusto.sd.strava.service;

import es.deusto.sd.strava.dao.TrainingSessionRepository;
import es.deusto.sd.strava.dto.TrainingSessionDTO;
import es.deusto.sd.strava.entity.TrainingSession;
import es.deusto.sd.strava.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TrainingSessionService {

    @Autowired
    private TrainingSessionRepository trainingSessionRepository;

    public void createTrainingSession(User user, TrainingSessionDTO sessionDTO) {
        TrainingSession session = new TrainingSession(
            sessionDTO.getTitle(),
            sessionDTO.getSport(),
            sessionDTO.getDistance(),
            sessionDTO.getStartDate(),
            sessionDTO.getStartTime(),
            sessionDTO.getDuration()
        );
        trainingSessionRepository.save(session);
    }

    public List<TrainingSession> getRecentTrainingSessions(String email) {
        return trainingSessionRepository.findTop5ByUserEmailOrderByStartDateDesc(email);
    }

    public List<TrainingSession> getTrainingSessionsByDate(String email, Date startDate, Date endDate) {
        return trainingSessionRepository.findByUserEmailAndStartDateBetween(email, startDate, endDate);
    }

    public List<TrainingSession> getAllTrainingSessions(String email) {
        return trainingSessionRepository.findByUserEmail(email);
    }
}
