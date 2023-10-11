# Démarrer un système sur une machine

## Construction des images Docker

1. Build de la base de données

    `docker build -f src/main/docker/DockerfilePGSQL -t sfleury72/conversationsdb .`
2. Build de l'API
   
   Pré-requis : `mvn clean package` puis

    `docker build -f src/main/docker/Dockerfile.jvm -t sfleury72/conversationsapi:0.1.0-SNAPSHOT .`
 


## Soumission des images Docker

docker push sfleury72/conversationsdb

docker push sfleury72/conversationsapi:1.1.0-SNAPSHOT

## Récupération des images Docker

1. Image contenant la base de données conversations :

   `docker pull sfleury72/conversationsdb:latest`


2. Image contenant l'API conversations :

   `docker pull sfleury72/conversationsapi:latest`


## Exécution des images

1. Création d'un container pour conversationsdb

   `docker run --name db-conversations --network mon_reseau \
    -d -p 5433:5432 sfleury72/conversationsdb:latest`


2. Création d'un container pour l'API

      2.1 Container sur la DB du container conversationsdb
      `docker run --name api-conv-container -d -p 8081:8080 -p 8444:8443 -e DB_URL=db-conversations/conversations \
        -e DB_USERNAME=conversations -e DB_PWD=password -e LOGS_LEVEL=INFO \
        -e USERS_API_URL=http://api-container:8080/shareskillsapi \
       --network mon_reseau sfleury72/conversationsapi:0.1.0-SNAPSHOT`

         2.2 Container sur la DB ELEPHANT
         `docker run --name api-conv-container -d -p 8081:8080 -e DB_URL=trumpet.db.elephantsql.com/vutbwawl \
           -e DB_USERNAME=vutbwawl -e DB_PWD=JMTAmEy0cBQeDqBHis9CjwzNcMQ5Ccm8 sfleury72/conversationsapi:X.Y.Z`

