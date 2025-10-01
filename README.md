Document tracking service

# About
Simple Spring Boot service allowing REST access to tasks

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

### Get all users
```shell
curl --user "webuser:websecret" localhost:8080/api/v1/user
```

### Get single user
```shell
curl --user "webuser:websecret" localhost:8080/api/v1/user/1
```

### Getting non-existing user
```shell
curl --user "webuser:websecret" localhost:8080/api/v1/user/90000
```

### Create user
```shell
curl --user "webuser:websecret" -X POST localhost:8080/api/v1/user -H 'Content-type:application/json' -d '{"name": "User200", "code": "u200"}'
```

### Update user
```shell
curl --user "webuser:websecret" -X PUT localhost:8080/api/v1/user/1 -H 'Content-type:application/json' -d '{"id": "1", "name": "User-updated", "code": "usr-upd"}'
```
### Query
```shell
curl --user "webuser:websecret" localhost:8080/api/v1/user/operation/query?startTime=2023-01-01T00:00:05\&endTime=2023-01-02T10:00:00\&taskDefinitionId=1
```


# Data store
Data is stored in MariaDB, container named "db" by default or at localhost in spring profile "localhost".
In a docker compose usage, the data can be viewed via [Adminer tool](http://localhost:10001/?server=db&username=dbuser&db=doctracking).

Data during integration tests is stored in H2 memory database.
In a paused debugger scenario, the data can be viewed via [H2 console](http://localhost:8080/h2-console).

All timestamps are considered to be in UTC. Time conversion to user's TZ should be done at the client side.
