package com.chrispadilla.spotifyperformancemonitor.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

@Service
public class SpotifyClientService implements ISpotifyClientService {
	@Value("${spotify.client-id}")
	private String spotifyClientId;
	@Value("${spotify.client-secret}")
	private String spotifyClientSecret;
	@Value("${spotify.redirect-uri}")
	private String spotifyRedirectURI;

	public SpotifyApi generateCleint() {
		// TODO Organize into seperate Bean
		// https://docs.spring.io/spring-framework/reference/core/beans/java/bean-annotation.html
		try {
			System.out.println("___SPOTIFY___");
			System.out.println(spotifyClientId);
			System.out.println(spotifyClientSecret);
			System.out.println(spotifyRedirectURI);
			SpotifyApi spotifyApi = new SpotifyApi.Builder()
			.setClientId(spotifyClientId)
			.setClientSecret(spotifyClientSecret)
			.setRedirectUri(new URI(spotifyRedirectURI))
			.build();

			ClientCredentialsRequest clientCredentialsRequest = spotifyApi
				.clientCredentials()
				.build();

			
			ClientCredentials clientCredentials = clientCredentialsRequest.execute();

			spotifyApi.setAccessToken(clientCredentials.getAccessToken());


			return spotifyApi;
		} catch (URISyntaxException | IOException | SpotifyWebApiException | ParseException e) {
			System.out.println(e);
		}

		return null;
	}
	
}