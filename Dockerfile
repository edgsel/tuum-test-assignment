FROM postgres:14.2

FROM gradle:7.5-jdk11 AS build

#ENV HOST=host.docker.internal

#ENV POSTGRES_DB=tuum_bank_data
#ENV POSTGRES_DEFAULT_SCHEMA=public
#ENV POSTGRES_USERNAME=
#ENV POSTGRES_PASSWORD=
#ENV RABBITMQ_USERNAME=
#ENV RABBITMQ_PASSWORD=

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle build -x test
RUN gradle flywayMigrate -i
RUN gradle clean test build

FROM openjdk:11-jdk

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/

RUN ls -la /app

ENTRYPOINT ["java", "-jar","/app/tuum-test-assignment-1.0.0.jar"]
