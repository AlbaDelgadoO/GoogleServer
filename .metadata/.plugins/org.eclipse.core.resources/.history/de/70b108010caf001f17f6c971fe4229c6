package es.deusto.sd.strava.external.factory;

import es.deusto.sd.strava.external.AuthenticationInterface;
import es.deusto.sd.strava.external.FacebookGateway;
import es.deusto.sd.strava.external.GoogleGateway;

public class AuthenticationProviderFactory {
	public static AuthenticationInterface createProviderGateway(String providerType) {
		AuthenticationInterface provider = null;
		switch (providerType.toLowerCase()) {
		case "google":
			provider = new GoogleGateway();
			break;
		case "facebook":
			provider = new FacebookGateway();
			break;
		default:
			throw new IllegalArgumentException("Unsupported provider type: " + providerType);
		}
		return provider;
	}
}