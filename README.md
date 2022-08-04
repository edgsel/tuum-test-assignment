# TUUM Core Banking API v1

A small core banking solution to create, get accounts and transactions.

## Required setup
JDK 11+

Gradle 7.5+

Docker 4.6.1+

## Required ENV variables:

`POSTGRES_DB=tuum_bank_data`

`POSTGRES_DEFAULT_SCHEMA=public`

`POSTGRES_USERNAME=`

`POSTGRES_PASSWORD=`

`DB_HOST=`

`DB_PORT=`

`RABBITMQ_USERNAME=`

`RABBITMQ_PASSWORD=`


# To run application locally

## 1. Export environment variables (Linux/MacOS)
* `export $(xargs < .env)`

## 2. Start up DB and RabbitMQ containers
* `docker-compose up -d postgresdb rabbitmq`

## 3. NB! run DB migrations
* ` ./gradlew flywayMigrate -i`

### To reset DB migrations
* ` ./gradlew flywayClean -i`

## 4. To run the project with Gradle for `MacOS/Linux`:
Build the project:
* `./gradlew clean build`

Run tests:
* `./gradlew test`

## 5. And then...
Run the project:
* `./gradlew bootRun`

# To run apps in docker containers (Tested only on MacOS, [unstable, after building JAR  endpoints are unreachable]) 

### NB!  Tested only on MacOS

## 1. ENV variables
* Prefill ENV variables in Dockerfile
* Set `DB_HOST` to `host.docker.internal`

## 2. Start up DB and RabbitMQ containers
To make our API pass integration tests and run API 
* `docker-compose up -d postgresdb rabbitmq`

## 3. Run API
* `docker-compose up -d app`

# API docs
* for Tuum API docs go to `http://localhost:8091/api/v1/swagger-ui/index.html`


## What can be improved
* Instead of calculating balance every time when request is done, store balances in a separate table
* Insert methods could return stored object immediately
* DTO/Entity converters could be refactored to use Java generics
* JAR file do not include all packages resulting in non-working docker container

## Estimate on how many transactions can your account application can handle per second on your development machine
* Workstation: Macbook PRO 14 (M1 PRO, 16GB, 1TB)

For testing I have used Postman Runner. I Made it iterate 20 times with 0ms delay. In average, API handled 8 requests per second (See report in Tuum_20API.postman_test_run.json)