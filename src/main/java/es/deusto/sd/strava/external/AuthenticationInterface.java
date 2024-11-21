package es.deusto.sd.strava.external;

public interface AuthenticationInterface {
    boolean validate(String email, String password);
}
