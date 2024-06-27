FROM maven:3.9.7-eclipse-temurin-21-alpine AS build
COPY pom.xml ./pom.xml
RUN mvn -B -f ./pom.xml dependency:resolve dependency:resolve-plugins
COPY src ./src
RUN mvn -f ./pom.xml quarkus:build

FROM openjdk:21-slim
COPY --from=build /target/quarkus-app/ /app
RUN chmod -R 755 /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/quarkus-run.jar"]