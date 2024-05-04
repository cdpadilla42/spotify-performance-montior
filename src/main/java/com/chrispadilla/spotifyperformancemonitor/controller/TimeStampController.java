package com.chrispadilla.spotifyperformancemonitor.controller;

import org.springframework.web.bind.annotation.RestController;

import com.chrispadilla.spotifyperformancemonitor.model.TmStmp;
import com.chrispadilla.spotifyperformancemonitor.repositories.TimeStampRepository;
import com.chrispadilla.spotifyperformancemonitor.service.TimeStampService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("/api/timestamp")
public class TimeStampController {
	@Autowired
	private TimeStampService timeStampService;
	@Autowired
	private TimeStampRepository timeStampRepository;

	@GetMapping
	public Iterable<TmStmp> getAll() {
	return timeStampRepository.findAll();
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public TmStmp createStmp() {
		TmStmp ts = timeStampService.createTimeStamp();
		
		return ts;
	}
	
	
}
