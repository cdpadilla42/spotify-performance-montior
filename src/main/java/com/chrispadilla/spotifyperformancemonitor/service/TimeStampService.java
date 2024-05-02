package com.chrispadilla.spotifyperformancemonitor.service;

import org.springframework.stereotype.Service;

import com.chrispadilla.spotifyperformancemonitor.model.TmStmp;
import com.chrispadilla.spotifyperformancemonitor.repositories.TimeStampRepository;

@Service
public class TimeStampService implements ITimeStampService{
	private TimeStampRepository timeStampRepository;
	public TmStmp createTimeStamp() {
		TmStmp ts = new TmStmp();
		return timeStampRepository.save(ts);
	}
}
