package com.chrispadilla.spotifyperformancemonitor.util;

import java.io.Serializable;
import java.util.List;

import com.chrispadilla.spotifyperformancemonitor.model.Artist;

public class ArtistSerialized implements Serializable {
	private List<Artist> artists;

	public ArtistSerialized() {};

	public List<Artist> getArtists() {
		return artists;
	}

	public void setTowns(List<Artist> artists) {
		this.artists = artists;
	}

	@Override
	public String toString() {
			return "Artusts: " + artists.toString();
	}
}
