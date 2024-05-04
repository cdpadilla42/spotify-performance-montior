package com.chrispadilla.spotifyperformancemonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrispadilla.spotifyperformancemonitor.model.TmStmp;
import com.chrispadilla.spotifyperformancemonitor.repositories.TimeStampRepository;

@Service
public class TimeStampService implements ITimeStampService{
	@Autowired
	private TimeStampRepository timeStampRepository;

	public TmStmp createTimeStamp() {
		TmStmp ts = new TmStmp();
		System.out.println(ts);
		return timeStampRepository.save(ts);
	}
}
