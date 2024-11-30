package es.deusto.sd.strava.external;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FacebookGateway implements AuthenticationInterface {

    private static final String FACEBOOK_SERVER_IP = "127.0.0.1";  // Facebook server IP (could be configurable)
    private static final int FACEBOOK_SERVER_PORT = 8081; // Facebook server port (could be configurable)

    @Override
    public boolean validate(String email, String password) {
        // Establish socket connection to the Facebook server
        try (Socket facebookSocket = new Socket(FACEBOOK_SERVER_IP, FACEBOOK_SERVER_PORT);
             DataOutputStream out = new DataOutputStream(facebookSocket.getOutputStream());
             DataInputStream in = new DataInputStream(facebookSocket.getInputStream())) {

            // Send email and password to Facebook server for authentication
            out.writeUTF(email + "#" + password);  // Sending email and password over the socket
            // Read response from Facebook server
            String response = in.readUTF();
            // Check response from Facebook server
            if (response.startsWith("OK")) {
                // User authenticated successfully
                return true;
            } else {
                // Authentication failed
                System.out.println("Authentication failed: " + response);
                return false;
            }
        } catch (IOException e) {
            System.err.println("Error during Facebook authentication: " + e.getMessage());
            return false;
        }
    }

}
