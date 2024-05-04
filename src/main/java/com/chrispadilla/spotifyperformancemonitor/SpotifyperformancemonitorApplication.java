package com.chrispadilla.spotifyperformancemonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.chrispadilla.spotifyperformancemonitor.*")
@EntityScan("com.chrispadilla.spotifyperformancemonitor.model")
@EnableJpaRepositories("com.chrispadilla.spotifyperformancemonitor.repositories")
public class SpotifyperformancemonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotifyperformancemonitorApplication.class, args);
	}

}
