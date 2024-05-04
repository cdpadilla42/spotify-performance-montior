package com.chrispadilla.spotifyperformancemonitor.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chrispadilla.spotifyperformancemonitor.model.TmStmp;

@Repository
public interface TimeStampRepository extends CrudRepository<TmStmp, Long>{
	
}
