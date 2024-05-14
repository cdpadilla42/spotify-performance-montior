package com.chrispadilla.spotifyperformancemonitor.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chrispadilla.spotifyperformancemonitor.model.Artist;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long>{
	Artist findByName(String name);

	Artist findBySpotifyId(String spotifyId);
}