package es.deusto.sd.strava.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.deusto.sd.strava.dto.ChallengeDTO;
import es.deusto.sd.strava.dto.TrainingSessionDTO;
import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.TrainingSession;
import es.deusto.sd.strava.service.ChallengeService;
import es.deusto.sd.strava.service.TrainingSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/strava")
@Tag(name = "Strava Controller", description = "Operations related to training sessions and challenges")
public class StravaController {

    private final TrainingSessionService trainingSessionService;
    private final ChallengeService challengeService;

    public StravaController(TrainingSessionService trainingSessionService, ChallengeService challengeService) {
        this.trainingSessionService = trainingSessionService;
        this.challengeService = challengeService;
    }

    // POST to create a training session
    @Operation(
        summary = "Create a new training session",
        description = "Creates a new training session with the provided details",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success: Training session created"),
            @ApiResponse(responseCode = "400", description = "Bad Request: Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        }
    )
    @PostMapping("/trainingSession")
    public ResponseEntity<String> createTrainingSession(@RequestBody TrainingSessionDTO sessionDTO) {
        try {
            trainingSessionService.createTrainingSession(sessionDTO);
            return new ResponseEntity<>("Training session created successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating training session", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET to query training sessions
    @Operation(
        summary = "Query training sessions",
        description = "Retrieves a list of training sessions between specified dates",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: List of training sessions retrieved"),
            @ApiResponse(responseCode = "204", description = "No Content: No training sessions found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        }
    )
    @GetMapping("/trainingSessions")
    public ResponseEntity<List<TrainingSessionDTO>> queryTrainingSessions(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        try {
            List<TrainingSession> sessions = trainingSessionService.getTrainingSessions(startDate, endDate);
            if (sessions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<TrainingSessionDTO> dtos = new ArrayList<>();
            sessions.forEach(session -> dtos.add(new TrainingSessionDTO(session)));
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST to create a challenge
    @Operation(
        summary = "Create a new challenge",
        description = "Creates a new challenge with the provided details",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success: Challenge created"),
            @ApiResponse(responseCode = "400", description = "Bad Request: Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        }
    )
    @PostMapping("/challenge")
    public ResponseEntity<String> createChallenge(@RequestBody ChallengeDTO challengeDTO) {
        try {
            challengeService.createChallenge(challengeDTO);
            return new ResponseEntity<>("Challenge created successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating challenge", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET to view accepted challenges
    @Operation(
        summary = "View accepted challenges",
        description = "Retrieves a list of challenges accepted by the user",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: List of accepted challenges retrieved"),
            @ApiResponse(responseCode = "204", description = "No Content: No accepted challenges found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        }
    )
    @GetMapping("/acceptedChallenges")
    public ResponseEntity<List<ChallengeDTO>> viewAcceptedChallenges(@RequestParam String token) {
        try {
            List<Challenge> challenges = challengeService.getAcceptedChallenges(token);
            if (challenges.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<ChallengeDTO> dtos = new ArrayList<>();
            challenges.forEach(challenge -> dtos.add(new ChallengeDTO(challenge)));
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
