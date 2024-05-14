package com.chrispadilla.spotifyperformancemonitor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String name;

	@Column
	private String[] genres;

	@Column
	private String spotifyId;

	@Column
	private String spotifyUrl;

	public Artist() {
		
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	public void setSpotifyId(String spotifyId) {
		this.spotifyId = spotifyId;
	}

	public void setSpotifyUrl(String spotifyUrl) {
		this.spotifyUrl = spotifyUrl;
	}

	public String getName() {
		return name;
	}

	public String[] getGenres() {
		return genres;
	}

	public String getSpotifyId() {
		return spotifyId;
	}

	public String getSpotifyUrl() {
		return spotifyUrl;
	}

	
}
