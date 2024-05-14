package com.chrispadilla.spotifyperformancemonitor.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chrispadilla.spotifyperformancemonitor.model.ArtistTimeSeries;

@Repository
public interface ArtistTimeSeriesRepository extends CrudRepository<ArtistTimeSeries, Long>{
	
}
