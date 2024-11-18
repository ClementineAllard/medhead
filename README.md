# MedHead
Système d'intervention d'urgence

## Prérequis
- Java 17
- Maven
- VS Code avec extension Spring Boot Extension Pack
- MySQL

## Installation
Cloner avec GitHub Desktop

ou

git clone https://github.com/ClementineAllard/medhead.git

## Commande lancement
./mvnw spring-boot:run

## Commande test
./mvnw test

## Accès
Api : http://localhost:8080

WebApp : http://localhost:8081
Compte Admin : email= admin, mdp= admin

## Base de données 
Port : 9000

Script données de test : api/src/main/resources/data.sql

## API
Script postman des endpoints : api/src/main/resources/MedHead Collection.postman_collection.json

## Automatisation CI
Workflows GitHub Actions : CI (génération des images Docker) et Tests (lancement des tests du projet)
