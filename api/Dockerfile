# Image Maven pour compiler le projet
FROM maven:3.8.5-openjdk-17 AS build

# Répertoire de travail
WORKDIR /app

# Copie du projet
COPY . /app

# Compilation du projet
RUN chmod +x ./api/mvnw
RUN ./api/mvnw clean package -DskipTests

# Image JDK pour exécuter l'application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copie de l'artefact généré depuis l'étape précédente
COPY --from=build /app/target/*.jar api.jar

# Exposition du port
EXPOSE 8080

# Lancement l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
