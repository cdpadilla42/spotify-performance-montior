package com.chrispadilla.spotifyperformancemonitor.controller;

import org.springframework.web.bind.annotation.RestController;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;

import com.chrispadilla.spotifyperformancemonitor.service.SpotifyClientService;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/spotify")
public class SpotifyController {
	@Autowired
	private SpotifyClientService spotifyClientService;

	@GetMapping
	public String getArtistName() {
		SpotifyApi spotifyApi = spotifyClientService.generateCleint();
		GetArtistRequest ar = spotifyApi.getArtist("3KNN2G4RReLVKbnIfTkRFf").build();

		try {
			final Artist artist = ar.execute();
			System.out.println(artist);
			return artist.getName();
		} catch (SpotifyWebApiException | ParseException | IOException e) {
			System.out.println("Error with artist request");
			System.out.println(e);
			return null;
		}
	}
	

}
