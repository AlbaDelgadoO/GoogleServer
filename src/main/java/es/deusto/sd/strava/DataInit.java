package es.deusto.sd.strava;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.deusto.sd.strava.dto.ChallengeDTO;
import es.deusto.sd.strava.dto.TrainingSessionDTO;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.service.AuthService;
import es.deusto.sd.strava.service.ChallengeService;
import es.deusto.sd.strava.service.TrainingSessionService;

@Configuration
public class DataInit {

    private static final Logger logger = LoggerFactory.getLogger(DataInit.class);

    @Bean
    CommandLineRunner initData(AuthService authService, TrainingSessionService trainingSessionService, ChallengeService challengeService) {
        return args -> {
            try {
                // Crear usuarios y registrarlos
                User davidGoggins = new User("David Goggins", "1975-02-17", "1.85m", "86kg", 190, 60, "david@goggins.com", "google");
                User pedroSanchez = new User("Pedro Sanchez", "1972-02-29", "1.85m", "80kg", 180, 65, "pedro@sanchez.com", "google");
                User bolsonaro = new User("Bolsonaro", "1955-03-21", "1.73m", "85kg", 170, 70, "bolsonaro@politics.com", "facebook");
                User billieJoe = new User("Billie Joe", "1972-02-17", "1.70m", "70kg", 185, 62, "billiejoe@greenday.com", "google");
                User pabloIglesias = new User("Pablo Iglesias", "1978-10-17", "1.74m", "72kg", 175, 68, "pablo@iglesias.com", "google");
                User pacoLopez = new User("Paco Lopez", "1985-07-10", "1.80m", "78kg", 185, 60, "paco@lopez.com", "facebook");

                authService.register(davidGoggins);
                authService.register(pedroSanchez);
                authService.register(bolsonaro);
                authService.register(billieJoe);
                authService.register(pabloIglesias);
                authService.register(pacoLopez);

                logger.info("Users registered successfully!");

                // Crear sesiones de entrenamiento
                Calendar calendar = Calendar.getInstance();

                calendar.set(2024, Calendar.JANUARY, 15, 6, 30);
                Date startDate1 = calendar.getTime();
                TrainingSessionDTO session1 = new TrainingSessionDTO("Morning Run", "Running", 10.0, startDate1, 6.5, 60);
                trainingSessionService.createTrainingSession(davidGoggins, session1);

                calendar.set(2024, Calendar.JANUARY, 16, 18, 0);
                Date startDate2 = calendar.getTime();
                TrainingSessionDTO session2 = new TrainingSessionDTO("Evening Swim", "Swimming", 2.0, startDate2, 18.0, 45);
                trainingSessionService.createTrainingSession(pedroSanchez, session2);

                logger.info("Training sessions created successfully!");

                // Crear desafíos
                calendar.set(2024, Calendar.FEBRUARY, 1);
                Date challengeStartDate1 = calendar.getTime();
                calendar.set(2024, Calendar.MARCH, 1);
                Date challengeEndDate1 = calendar.getTime();
                ChallengeDTO challenge1 = new ChallengeDTO("Marathon Prep", challengeStartDate1, challengeEndDate1, 42.195, 180.0, "Running");

                calendar.set(2024, Calendar.FEBRUARY, 5);
                Date challengeStartDate2 = calendar.getTime();
                calendar.set(2024, Calendar.FEBRUARY, 10);
                Date challengeEndDate2 = calendar.getTime();
                ChallengeDTO challenge2 = new ChallengeDTO("Swimming Sprint", challengeStartDate2, challengeEndDate2, 5.0, 60.0, "Swimming");

                challengeService.createChallenge(challenge1);
                challengeService.createChallenge(challenge2);

                logger.info("Challenges created successfully!");

            } catch (Exception e) {
                logger.error("Error initializing data: " + e.getMessage());
            }
        };
    }
}
