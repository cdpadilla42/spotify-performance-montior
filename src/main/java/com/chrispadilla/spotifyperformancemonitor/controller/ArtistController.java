package com.chrispadilla.spotifyperformancemonitor.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.chrispadilla.spotifyperformancemonitor.model.Artist;
import com.chrispadilla.spotifyperformancemonitor.model.ArtistTimeSeries;
import com.chrispadilla.spotifyperformancemonitor.model.TmStmp;
import com.chrispadilla.spotifyperformancemonitor.repositories.ArtistRepository;
import com.chrispadilla.spotifyperformancemonitor.repositories.ArtistTimeSeriesRepository;
import com.chrispadilla.spotifyperformancemonitor.service.SpotifyClientService;
import com.chrispadilla.spotifyperformancemonitor.service.TimeStampService;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;






@RestController
@RequestMapping("/api/artist")
public class ArtistController {
	@Autowired
	private SpotifyClientService spotifyClientService;
	@Autowired
	private TimeStampService timeStampService;
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private ArtistTimeSeriesRepository artistTimeSeriesRepository;

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Artist> getArtists() {
		return artistRepository.findAll();
	}

	@GetMapping("/{id}")
	public Artist getArtist(@PathVariable Long id) {
		Artist artist = artistRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No matching ID"));
		return artist;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Artist postArtist(@RequestBody Artist artist) {
		return artistRepository.save(artist);
	}

	@PostMapping("/spotify")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void collectArtists() {
		SpotifyApi spotifyApi = spotifyClientService.generateCleint();
		GetArtistRequest ar = spotifyApi.getArtist("3KNN2G4RReLVKbnIfTkRFf").build();

		try {
			final se.michaelthelin.spotify.model_objects.specification.Artist artist = ar.execute();
			Artist artistEntity= new com.chrispadilla.spotifyperformancemonitor.model.Artist();
			
			artistEntity.setName(artist.getName());
			artistEntity.setGenres(artist.getGenres());
			artistEntity.setSpotifyId(artist.getId());
			artistEntity.setSpotifyUrl(artist.getExternalUrls().get("spotify"));

			artistRepository.save(artistEntity);
		} catch (SpotifyWebApiException | ParseException | IOException e) {
			System.out.println("Error with artist request");
			System.out.println(e.getMessage());
		}		
	}

	@PostMapping("/{spotifyId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void collectArtist(@PathVariable String spotifyId) {
		String id = !spotifyId.isEmpty() ? spotifyId : "3KNN2G4RReLVKbnIfTkRFf";
		SpotifyApi spotifyApi = spotifyClientService.generateCleint();
		GetArtistRequest ar = spotifyApi.getArtist(id).build();

		try {
			final se.michaelthelin.spotify.model_objects.specification.Artist artist = ar.execute();
			Artist artistEntity= new com.chrispadilla.spotifyperformancemonitor.model.Artist();
			
			artistEntity.setName(artist.getName());
			artistEntity.setGenres(artist.getGenres());
			artistEntity.setSpotifyId(artist.getId());
			artistEntity.setSpotifyUrl(artist.getExternalUrls().get("spotify"));

			artistRepository.save(artistEntity);
		} catch (SpotifyWebApiException | ParseException | IOException e) {
			System.out.println("Error with artist request");
			System.out.println(e.getMessage());
		}		
	}

	@PostMapping("/collect")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void collectArtistsPopularity() {
		String spotifyId = "3KNN2G4RReLVKbnIfTkRFf";
		Artist dbArtist = artistRepository.findBySpotifyId(spotifyId);

		SpotifyApi spotifyApi = spotifyClientService.generateCleint();
		GetArtistRequest ar = spotifyApi.getArtist(spotifyId).build();

		try {
			final se.michaelthelin.spotify.model_objects.specification.Artist artist = ar.execute();
			ArtistTimeSeries artistTimeSeriesEntity = new ArtistTimeSeries();
			TmStmp timestamp = timeStampService.createTimeStamp();
			
			artistTimeSeriesEntity.setTmstmp(timestamp);
			artistTimeSeriesEntity.setArtist(dbArtist);
			artistTimeSeriesEntity.setPopularity(artist.getPopularity());
			System.out.println(artist.getPopularity());

			artistTimeSeriesRepository.save(artistTimeSeriesEntity);
			
		} catch (SpotifyWebApiException | ParseException | IOException e) {
			System.out.println("Error with artist request");
			System.out.println(e.getMessage());
		}		
	}
}
