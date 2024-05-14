# Info

## Accessing the Postgres DB

```
psql -U postgres -h 0.0.0.0
```
Reading a table:

```
TABLE tableName;
```

# Todos

## Init

- [X] Initialize Project
- [X] Write TimeStamp controller, model, repo, and service classes
- [X] Setup Postgres on docker
- [X] Add config for hibernate
- [X] Verify that properites are @autowired
- [X] Verify correct name for entityscanner
- [X] add hibernate arguement: `spring.jpa.hibernate.ddl-auto=update`
- [X] Not saving id or current time to db. Adjust.
	- [X] Verified through psql that records are being recorded with `TABLE tm_stmp;`
	- [X] Added Temporal annotation to timestamp (no real change)
	- [X] Added Getters and Setters to Tmstmp Entity so that their properties may be accessed. That did the trick!

## Spotify Worker

### Setup
- [~] Write sample golang file
- [X] Attempt Serverless deploy
	- Error! Perhaps a manual deploy will suffice.
	- https://medium.com/@daniel.woods/deploying-a-golang-package-to-aws-lambda-in-5-minutes-cd11685f576
	- [X] Rename the service to a unique name. Must be unique across all AWS S3's
	- [~] Configure to your aws account https://www.serverless.com/framework/docs-providers-aws-guide-credentials
- [X] Opt for solution in this server
	- [X] Install API wrapper
	- [X] Store into Service Layer
		- [X] Classes must be public!
	- [X] Add credentials to application.properties

	- [X] Ensure all correct credentials are wired
- [ ] Get all artists in DB (temp for now)
	- [ ] Convert api Res to DTO and save as entity
- [ ] request from Spotify
	- [ ] Do so in a multi-threaded fassion
	- [ ] Make follow up song and album requests
- [X] Store popularity
- [ ] Refactor business logic to services, out of controllers

### Data Extraction

- [ ] Get artist data

## DB Design
- [X] Explore Spotify API
	- [X] Find interesting Data points to track
	- [X] Listens each month
	- [X] By artist, album, song.
- [ ] Setup Entity, Repositories, and Controllers.
- [ ] Test!
	- [X] Test artist creation and retrieval
		- [X] Be sure you're getting a json value, not a list, when expected.
		- [ ] Double check your test: We DO want to test getting by ID explecitly.
		- [ ] Assert not found
	- [ ] Add Mockito for API test

## Relationships[^1]

ArtistTimeSeries *--1 Artist

ArtistTimeSeris 1--1 TmStmp

AlbumTimeSeries *--1 Album

AlbumTimeSeries 1--* TmStmp

SongTimeSeries *--1 Song

SongTimeSeries 1--* TmStmp

Album *--1 Artist

Song +--1 Album

Song +--1 Artist


### Artist

id: int
name: string
avaragepopularity: integer (calculated value)
genres: String[]
spotifyid: String
spotifyurl: String

### ArtistTimeSeries

id: int
tmstmp: tmstmp
artistid: artistid

#### Note on Relationships:

One-to-One allows for the opportunity of a bidirectional connection, though it is not required.

One to many can be bidirectional as well, as the many table can have multiple values. Though, this is not required.

[^1]: Format for relationships follows the [erd](https://github.com/BurntSushi/erd) shorthand:
```
Cardinality    Syntax
0 or 1         ?
exactly 1      1
0 or more      *
1 or more      +
```