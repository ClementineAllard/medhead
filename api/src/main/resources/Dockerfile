# Utilise une image Maven pour compiler le projet
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /api

# Copier le projet et le compiler
COPY . .
RUN ./mvnw clean package -DskipTests

# Utiliser une image JDK pour exécuter l'application
FROM openjdk:17-jdk-slim
WORKDIR /api

# Copier l'artefact généré depuis l'étape précédente
COPY --from=build /api/target/*.jar app.jar

# Exposer le port
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
