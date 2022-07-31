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

`DOCKER_BUILDKIT=0`

`COMPOSE_DOCKER_CLI_BUILD=0`

# To run application locally

## 1. Export environment variables (Linux/MacOS)
* `export $(xargs < .env)`

## 2. Start up DB container
* `docker-compose up -d postgresdb`

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

## API docs
* for Storage API docs go to `http://localhost:8091/api/v1/swagger-ui/index.html`

## What is missing
* Transaction endpoints (GET/POST)
* Transaction service
* RabbitMQ
