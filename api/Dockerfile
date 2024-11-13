# Image JDK pour exécuter l'application
FROM openjdk:17-jdk-slim

# Répertoire de travail
WORKDIR /api

# Copie du projet
COPY target/*.jar api.jar

# Lancement l'application
ENTRYPOINT ["java", "-jar", "api.jar"]
