# Configuration ECS et LoadBalancer

## Quick Real

Une nouvelle version de l'API à déployer ?

Il suffit de sélectionner le Task Definition TDNPronos et faire "create new revision"

Remplir la bonne version de l'image Docker

Puis sur cette TDNPronos, il faut faire "Deploy > Create service"

## Le Cluster

- Cluster name : __PronosAPICluster__
  - VPC et Subnets utilisés par défaut
  - infrastructure Fargate

## Création d'une TaskDefinition
A task definition contains one or more container definitions and is required to deploy your workloads on Amazon ECS.

- TaskDefinition family name : 
  - __PronosAPITaskDefinition__
  

- Détails sur le container :
  - name : __PronosAPI-Container__
  - image URI : docker.io/sfleury72/pronosapi:1.0.0-SNAPSHOT

## Création du Load-Balancer : Application Load Balancer

__PronosAPI-ALB__

### Création d'un Target group

## Dans le cluster, crétion d'un service

