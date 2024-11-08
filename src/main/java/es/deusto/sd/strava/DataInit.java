package es.deusto.sd.strava;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.TrainingSession;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.service.ChallengeService;
import es.deusto.sd.strava.service.TrainingSessionService;
import es.deusto.sd.strava.dto.TrainingSessionDTO;
import es.deusto.sd.strava.dto.ChallengeDTO;
//import es.deusto.sd.strava.service.UserService;

@Configuration
public class DataInit {

    private static final Logger logger = LoggerFactory.getLogger(DataInit.class);

    @Bean
    CommandLineRunner initData(/*UserService userService, */TrainingSessionService trainingSessionService, ChallengeService challengeService) {
        return args -> {
            // Create some users
            /*User davidGoggins = new User("DavidGoggins", "1975-02-17", "1.85m", "86kg", 190, 60, "david@goggins.com");
            User pedroSanchez = new User("PedroSanchez", "1972-02-29", "1.85m", "80kg", 180, 65, "pedro@sanchez.com");
            User bolsonaro = new User("Bolsonaro", "1955-03-21", "1.73m", "85kg", 170, 70, "bolsonaro@politics.com");
            User billieJoe = new User("BillieJoe", "1972-02-17", "1.70m", "70kg", 185, 62, "billiejoe@greenday.com");
            User pabloIglesias = new User("PabloIglesias", "1978-10-17", "1.74m", "72kg", 175, 68, "pablo@iglesias.com");
            User pacoLopez = new User("PacoLopez", "1985-07-10", "1.80m", "78kg", 185, 60, "paco@lopez.com");

            userService.addUser(davidGoggins);
            userService.addUser(pedroSanchez);
            userService.addUser(bolsonaro);
            userService.addUser(billieJoe);
            userService.addUser(pabloIglesias);
            userService.addUser(pacoLopez);

            logger.info("Users saved!");*/
        	
        	

        	// Create some training sessions
        	Calendar calendar = Calendar.getInstance();
        	calendar.set(2024, Calendar.JANUARY, 15, 6, 30);
        	Date startDate1 = calendar.getTime();
        	TrainingSessionDTO session1 = new TrainingSessionDTO("Morning Run", "Running", 10.0, startDate1, 6.5, 60);

        	calendar.set(2024, Calendar.JANUARY, 16, 18, 0);
        	Date startDate2 = calendar.getTime();
        	TrainingSessionDTO session2 = new TrainingSessionDTO("Evening Swim", "Swimming", 2.0, startDate2, 18.0, 45);

        	calendar.set(2024, Calendar.JANUARY, 17, 9, 0);
        	Date startDate3 = calendar.getTime();
        	TrainingSessionDTO session3 = new TrainingSessionDTO("Cycling Adventure", "Cycling", 50.0, startDate3, 9.0, 120);

            trainingSessionService.createTrainingSession(session1);
            trainingSessionService.createTrainingSession(session2);
            trainingSessionService.createTrainingSession(session3);

            logger.info("Training sessions saved!");

            // Create some challenges
            calendar.set(2024, Calendar.FEBRUARY, 1); // 1st February 2024
            Date challengeStartDate1 = calendar.getTime();
            calendar.set(2024, Calendar.MARCH, 1); // 1st March 2024
            Date challengeEndDate1 = calendar.getTime();
            ChallengeDTO challenge1 = new ChallengeDTO("Marathon Prep", challengeStartDate1, challengeEndDate1, 42.195, 300.0, "Running");

            calendar.set(2024, Calendar.FEBRUARY, 5); // 5th February 2024
            Date challengeStartDate2 = calendar.getTime();
            calendar.set(2024, Calendar.FEBRUARY, 10); // 10th February 2024
            Date challengeEndDate2 = calendar.getTime();
            ChallengeDTO challenge2 = new ChallengeDTO("Swimming Sprint", challengeStartDate2, challengeEndDate2, 5.0, 60.0, "Swimming");

            challengeService.createChallenge(challenge1);
            challengeService.createChallenge(challenge2);

            logger.info("Challenges saved!");
        };
    }
}