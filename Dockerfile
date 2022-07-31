FROM postgres:14.2

FROM gradle:7.5-jdk11 AS TEMP_BUILD_IMAGE

ENV APP_HOME=/usr/app/

# Uncomment and set values before running
#ENV POSTGRES_DB=
#ENV POSTGRES_DEFAULT_SCHEMA=
#ENV POSTGRES_USERNAME=
#ENV POSTGRES_PASSWORD=
#ENV DB_HOST=
#ENV DB_PORT=
#ENV DOCKER_BUILDKIT=
#ENV COMPOSE_DOCKER_CLI_BUILD=

WORKDIR $APP_HOME

RUN useradd -ms /bin/bash gradle || true

COPY build.gradle settings.gradle $APP_HOME
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src

USER root

RUN chown -R gradle /home/gradle/src
RUN gradle flywayMigrate -i

COPY . .

RUN gradle clean test build

FROM openjdk:11-jdk
ENV ARTIFACT_NAME=tuum-test-assignment-1.0.0.jar
ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .

ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
