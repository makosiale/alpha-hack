FROM maven:3.9.8-amazoncorretto-17 as build

COPY src src
COPY pom.xml pom.xml

RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17-jdk-slim
RUN adduser --system alpha-backend-user && addgroup --system alpha-backend-group && adduser alpha-backend-user alpha-backend-group
USER alpha-backend-user

WORKDIR /app

COPY --from=build target/Alpha-Hack-0.0.1-SNAPSHOT.jar ./alpha-backend.jar
ENTRYPOINT ["java", "-jar", "./alpha-backend.jar"]