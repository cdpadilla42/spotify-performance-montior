package com.chrispadilla.spotifyperformancemonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.chrispadilla.spotifyperformancemonitor.models")
@EnableJpaRepositories("com.chrispadilla.spotifyperformancemonitor.repositories")
public class SpotifyperformancemonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotifyperformancemonitorApplication.class, args);
	}

}
