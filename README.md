# pronos API

## API d'un user : 

## API Publications :
POST /shareskillsapi/v1/publications  : créer une publication

GET /shareskillsapi/v1/publications/{id}  : Obtenir une publication

DELETE /shareskillsapi/v1/publications/{id}  : Supprimer une publication

POST /shareskillsapi/v1/publications/survey  : créer un sondage

PUT /shareskillsapi/v1/publications/survey/chooseResponse  : Répondre à un sondage

PUT /shareskillsapi/v1/publications/{id}/mainpicture  : ajouter une image à une publication existante

GET /shareskillsapi/v1/publications/{id}/mainpicture  : Obtenir l'image de la publication

GET /shareskillsapi/v1/publications/users/{idUser} : Obtenir les publications d'un user




## API Likes :

POST /shareskillsapi/v1/publications/{id}/likes : Liker une publication

DELETE /shareskillsapi/v1/publications/{id}/likes : Déliker une publication


## API commentaires

POST /shareskillsapi/v1/publications/{id}/comments : Ajouter un commentaire à une publication

body :
{"content":"Publication très intéressante..."}

GET /shareskillsapi/v1/publications/{id}/comments : Obtenir la liste des commentaires sur une publication