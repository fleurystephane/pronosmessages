# Démarrer un système sur une machine

## Construction des images Docker

1. Build de la base de données

    `docker build -f src/main/docker/DockerfilePGSQL -t sfleury72/pronosdb .`
2. Build de l'API
   
   Pré-requis : `mvn clean package` puis

    `docker build -f src/main/docker/Dockerfile.jvm -t sfleury72/pronosapi:0.0.1-SNAPSHOT .`
 


## Soumission des images Docker

docker push sfleury72/pronosdb

docker push sfleury72/pronosapi:1.1.0-SNAPSHOT

## Récupération des images Docker

1. Image contenant la base de données pronos :

   `docker pull sfleury72/pronosdb:latest`

2. Image contenant le service Keycloak pronos :

   `docker pull sfleury72/pronoskeycloak:latest`

3. Image contenant l'API pronos :

   `docker pull sfleury72/pronosapi:latest`


## Exécution des images

1. Création d'un container pour pronosdb

   `docker run --name db-pronos -d -p 5432:5432 sfleury72/pronosdb:latest`


2. Création d'un container pour l'API

      2.1 Container sur la DB du container pronosdb
      `docker run --name api-container -d -p 8080:8080 -p 8443:8443 -e DB_URL=db-pronos/pronos \
        -e DB_USERNAME=pronos -e DB_PWD=password -e LOGS_LEVEL=INFO \
        -e S3_BUCKETURL=https://publicationsimages-stagging.s3.eu-west-3.amazonaws.com/ \
         -e S3_BUCKETNAME=publicationsimages-stagging \
        --link db-pronos sfleury72/pronosapi:0.0.4-SNAPSHOT`

      2.2 Container sur la DB ELEPHANT
      `docker run --name api-container -d -p 8080:8080 -e DB_URL=lucky.db.elephantsql.com/hlomemka \
        -e DB_USERNAME=hlomemka -e DB_PWD=QtKyl-iEhZmMuumTi8F9amaXmZvP-NQZ sfleury72/pronosapi:X.Y.Z`
   
----------------------------
COMMAND KO :
docker run -d --name mykeycloak-dev -p 8080:8080 \
--link mydbhost \
-v /Users/steph/mesdevs/pronos/src/main/docker/realm:/opt/keycloak/data/import \
sfleury72/pronoskeycloak \
start-dev --hostname-port=8080 --import-realm

docker run --name keycloak_unoptimized -p 8080:8080 \
-e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=change_me \
quay.io/keycloak/keycloak:latest \
start-dev

----------------------------

## Appels de l'API

1. GET http://localhost:8080/api/users
2. GET http://localhost:8080/api/users/1
3. GET http://localhost:8080/api/users/search/jo
4. pour créer un user :

`POST http://localhost:8080/api/users
body :
{
"name" : "tintin",
"username" : "titi",
"email" : "titi@gmail.com",
"description" : "Je suis Tintin"
}`

5. Appel de keycloak : 

   `GET http://localhost:3000/...(TODO)`