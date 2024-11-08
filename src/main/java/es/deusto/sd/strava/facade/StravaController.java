package es.deusto.sd.strava.facade;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import es.deusto.sd.strava.dto.ChallengeDTO;
import es.deusto.sd.strava.dto.TrainingSessionDTO;
import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.TrainingSession;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.service.AuthService;
import es.deusto.sd.strava.service.ChallengeService;
import es.deusto.sd.strava.service.TrainingSessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/strava")
@Tag(name = "Strava Controller", description = "Operations related to training sessions and challenges")
public class StravaController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TrainingSessionService trainingSessionService;

    @Autowired
    private ChallengeService challengeService;

    /**
     * Creates a new training session for the authenticated user.
     */
    @PostMapping("/trainingSession")
    @Operation(
        summary = "Create a new training session",
        description = "Creates a new training session with the provided details",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success: Training session created"),
            @ApiResponse(responseCode = "400", description = "Bad Request: Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Invalid token"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        }
    )
    public ResponseEntity<String> createTrainingSession(
            @RequestHeader("Authorization") String token,
            @RequestBody TrainingSessionDTO sessionDTO) {
        try {
            Optional<User> userOpt = authService.getUserByToken(token);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                trainingSessionService.createTrainingSession(user.getEmail(), sessionDTO);
                return new ResponseEntity<>("Training session created successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating training session", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves training sessions for the authenticated user.
     * If startDate and endDate are provided, sessions between those dates are returned.
     * Otherwise, the last 5 sessions are returned.
     */
    @GetMapping("/trainingSessions")
    @Operation(
        summary = "Query training sessions",
        description = "Retrieves a list of training sessions between specified dates or the last 5 sessions if dates are not provided",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: List of training sessions retrieved"),
            @ApiResponse(responseCode = "204", description = "No Content: No training sessions found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Invalid token"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        }
    )
    public ResponseEntity<List<TrainingSessionDTO>> queryTrainingSessions(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        try {
            Optional<User> userOpt = authService.getUserByToken(token);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                List<TrainingSession> sessions;
                if (startDate != null && endDate != null) {
                    sessions = trainingSessionService.getTrainingSessionsByDate(user.getEmail(), startDate, endDate);
                } else {
                    sessions = trainingSessionService.getRecentTrainingSessions(user.getEmail());
                }
                if (sessions.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                List<TrainingSessionDTO> dtos = sessions.stream()
                        .map(TrainingSessionDTO::new)
                        .collect(Collectors.toList());
                return new ResponseEntity<>(dtos, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a new challenge.
     */
    @PostMapping("/challenge")
    @Operation(
        summary = "Create a new challenge",
        description = "Creates a new challenge with the provided details",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success: Challenge created"),
            @ApiResponse(responseCode = "400", description = "Bad Request: Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Invalid token"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        }
    )
    public ResponseEntity<String> createChallenge(
            @RequestHeader("Authorization") String token,
            @RequestBody ChallengeDTO challengeDTO) {
        try {
            Optional<User> userOpt = authService.getUserByToken(token);
            if (userOpt.isPresent()) {
                challengeService.createChallenge(challengeDTO);
                // [Opcional] Enviar correo electrónico de confirmación al creador del desafío
                return new ResponseEntity<>("Challenge created successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating challenge", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves active challenges, optionally filtered by date or sport.
     */
    @GetMapping("/activeChallenges")
    @Operation(
        summary = "Get active challenges",
        description = "Retrieves a list of active challenges, optionally filtered by date or sport",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: List of challenges retrieved"),
            @ApiResponse(responseCode = "204", description = "No Content: No challenges found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Invalid token"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        }
    )
    public ResponseEntity<List<ChallengeDTO>> getActiveChallenges(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam(required = false) String sport) {
        try {
            Optional<User> userOpt = authService.getUserByToken(token);
            if (userOpt.isPresent()) {
                List<Challenge> challenges = challengeService.getActiveChallenges(startDate, endDate, sport);
                if (challenges.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                List<ChallengeDTO> dtos = challenges.stream()
                        .map(ChallengeDTO::new)
                        .collect(Collectors.toList());
                return new ResponseEntity<>(dtos, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Allows the authenticated user to accept a challenge.
     */
    @PostMapping("/acceptChallenge")
    @Operation(
        summary = "Accept a challenge",
        description = "Allows the user to accept a challenge",
        responses = {
            @ApiResponse(responseCode = "200", description = "Challenge accepted successfully"),
            @ApiResponse(responseCode = "404", description = "Challenge not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Invalid token"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        }
    )
    public ResponseEntity<String> acceptChallenge(
            @RequestHeader("Authorization") String token,
            @RequestParam String challengeName) {
        try {
            Optional<User> userOpt = authService.getUserByToken(token);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                boolean success = challengeService.acceptChallenge(user.getEmail(), challengeName);
                if (success) {
                    return new ResponseEntity<>("Challenge accepted successfully", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Challenge not found", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error accepting challenge", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves the list of challenges accepted by the authenticated user.
     */
    @GetMapping("/acceptedChallenges")
    @Operation(
        summary = "View accepted challenges",
        description = "Retrieves a list of challenges accepted by the user",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: List of accepted challenges retrieved"),
            @ApiResponse(responseCode = "204", description = "No Content: No accepted challenges found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Invalid token"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        }
    )
    public ResponseEntity<List<ChallengeDTO>> viewAcceptedChallenges(@RequestHeader("Authorization") String token) {
        try {
            Optional<User> userOpt = authService.getUserByToken(token);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                List<Challenge> challenges = challengeService.getAcceptedChallenges(user.getEmail());
                if (challenges.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                List<ChallengeDTO> dtos = challenges.stream()
                        .map(ChallengeDTO::new)
                        .collect(Collectors.toList());
                return new ResponseEntity<>(dtos, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Checks the progress status of an accepted challenge.
     */
    @GetMapping("/challengeStatus")
    @Operation(
        summary = "Check challenge status",
        description = "Checks the progress status of an accepted challenge",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: Challenge status retrieved"),
            @ApiResponse(responseCode = "404", description = "Challenge not found or not accepted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Invalid token"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        }
    )
    public ResponseEntity<String> checkChallengeStatus(
            @RequestHeader("Authorization") String token,
            @RequestParam String challengeName) {
        try {
            Optional<User> userOpt = authService.getUserByToken(token);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                List<TrainingSession> userSessions = trainingSessionService.getAllTrainingSessions(user.getEmail());
                double progress = challengeService.checkChallengeStatus(user.getEmail(), challengeName, userSessions);
                if (progress >= 0) {
                    return new ResponseEntity<>("Challenge progress: " + String.format("%.2f", progress) + "%", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Challenge not found or not accepted", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
            }
        } catch (IllegalStateException | IllegalArgumentException e) {
            // Manejar desafíos inválidos
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error checking challenge status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}