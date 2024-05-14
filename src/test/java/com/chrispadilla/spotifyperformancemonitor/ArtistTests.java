package com.chrispadilla.spotifyperformancemonitor;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.chrispadilla.spotifyperformancemonitor.model.Artist;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
// @ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ArtistTests {
	// Hard coded for now, can be extracted into env variable later
	private static final String API_ROOT = "http://localhost:80/api/artist";

	private Artist createRandomArtist() {
		Artist artist = new Artist();
		artist.setName(RandomStringUtils.randomAlphabetic(15));
		artist.setSpotifyId(RandomStringUtils.randomAlphabetic(10));
		artist.setSpotifyUrl(RandomStringUtils.random(20));
		return artist;
	}

	private String createArtistAsUrl(Artist artist) {
		Response res = RestAssured.given()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(artist)
			.post(API_ROOT);
		
		return API_ROOT + "/" + res.jsonPath().get("id");
	}

	@Test
	public void whenGetAllArtists_thenOk() {
		Response response = RestAssured.get(API_ROOT);

		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	@Test
	public void whenGetCreatedArtistById_thenOk() {
		Artist artist = createRandomArtist();
		String location = createArtistAsUrl(artist);

		Response res = RestAssured.get(location);

		assertEquals(HttpStatus.OK.value(), res.getStatusCode());
	}

	// TODO
	// @Test
	// public void whenGetCreatedBookById_thenOk() {
	// 	Artist book = createRandomArtist();
	// 	String location = creatBookAsUrl(book);
	// 	Response response = RestAssured.get(location);
	// 	System.out.println(location);

	// 	assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	// 	assertEquals(book.getTitle(), response.jsonPath()
	// 		.get("title"));
	// }
}
