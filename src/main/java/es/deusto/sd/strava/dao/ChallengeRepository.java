package es.deusto.sd.strava.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    //METHODS
    Optional<Challenge> findByName(String name);

    List<Challenge> findByStartDateBetweenAndSport(Date startDate, Date endDate, String sport);

    List<Challenge> findByStartDateBetween(Date startDate, Date endDate);

    List<Challenge> findBySport(String sport);

    List<Challenge> findByAcceptedUsersEmail(String email);
}