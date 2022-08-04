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

###NB!  Tested only on MacOS

## 1. ENV variables
* Prefill ENV variables in Dockerfile
* Set `DB_HOST` to `host.docker.internal`

## 2. Start up DB and RabbitMQ containers
To make our API pass integration tests and run API 
* `docker-compose up -d postgresdb rabbitmq`

## 3. Run API
* `docker-compose up -d app`

## API docs
* for Tuum API docs go to `http://localhost:8091/api/v1/swagger-ui/index.html`
