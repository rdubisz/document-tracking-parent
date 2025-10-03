Document tracking service

# About
Simple Spring Boot service allowing REST access to documents

# Usage

## Requirements
- JDK >= 25
- Docker

## Building

```shell
./mvnw clean install && docker-compose -f stack.yml build
```

The project was edited on Windows, so the Unix executable permission may not have been properly saved in the repository.

## Running
Running with Docker
```shell
docker-compose -f stack.yml up
```

## Stopping
When started with the above interactive Docker command, press Ctrl+C to gracefully stop the containers and
```shell
docker-compose -f stack.yml down
```
to delete stopped containers

## Client usage
Requires to have `curl` tool installed.

### About
```shell
curl -v --user "webuser:websecret" localhost:8080
```

### Get all teams
```shell
curl -s --user "webuser:websecret" localhost:8080/api/v1/team | jq
```

### Get all users
```shell
curl -s --user "webuser:websecret" localhost:8080/api/v1/person | jq
```

### Get single user
```shell
curl -s --user "webuser:websecret" localhost:8080/api/v1/person/1 | jq
```

### Getting non-existing user
```shell
curl --user "webuser:websecret" localhost:8080/api/v1/person/90000
```

### Create user
```shell
curl -s --user "webuser:websecret" -X POST localhost:8080/api/v1/person -H 'Content-type:application/json' -d '{"teamId":1,"email":"someone@company.com","firstName":"Some","lastName":"One"}'
```

### Update user
```shell
curl -s --user "webuser:websecret" -X PUT localhost:8080/api/v1/person/1 -H 'Content-type:application/json' -d '{"id":1,"teamId":2,"email":"john.doe.updated@company.com","firstName":"John","lastName":"Updated"}'
```


### Get all documents
```shell
curl -s --user "webuser:websecret" localhost:8080/api/v1/document | jq
```


### Query inactive users
Query number of users inactive in a given period, who were registered before that period
```shell
curl -s --user "webuser:websecret" localhost:8080/api/v1/person/inactive?startTime=2023-06-01T00:00:00\&endTime=2023-07-01T00:00:00 | jq
```


### Document words frequency
Words frequency for a given document id
```shell
curl -s --user "webuser:websecret" localhost:8080/api/v1/document/1/stats/words-frequency | jq
```


# Data store
Data is stored in MariaDB, container named "db" by default or at localhost in spring profile "localhost".
In a docker compose usage, the data can be viewed via [Adminer tool](http://localhost:10001/?server=db&username=dbuser&db=doctracking).

Data during integration tests is stored in H2 memory database.
In a paused debugger scenario, the data can be viewed via [H2 console](http://localhost:8080/h2-console).

All timestamps are considered to be in UTC. Time conversion to user's TZ should be done at the client side.
