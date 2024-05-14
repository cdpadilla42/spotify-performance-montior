package com.chrispadilla.spotifyperformancemonitor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class ArtistTimeSeries {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Artist artist;

	@OneToOne
	private TmStmp tmstmp;

	@Column
	private int popularity;

	public ArtistTimeSeries() {
		
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public TmStmp getTmstmp() {
		return tmstmp;
	}

	public void setTmstmp(TmStmp tmstmp) {
		this.tmstmp = tmstmp;
	}

 
	
}
