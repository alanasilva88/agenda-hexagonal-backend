# Estágio 1: Compilação (Build) usando Eclipse Temurin
FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Estágio 2: Execução (Run) usando uma imagem leve da Eclipse Temurin
FROM eclipse-temurin:17-jre-jammy
COPY --from=build /target/agenda-hexagonal-0.0.1-SNAPSHOT.jar agenda.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "agenda.jar"]
