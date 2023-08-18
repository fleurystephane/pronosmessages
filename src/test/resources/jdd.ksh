#!/bin/ksh

URL='LB-PronosAPI-193411774.eu-west-3.elb.amazonaws.com'
## LB-PronosAPI-193411774.eu-west-3.elb.amazonaws.com
## localhost:8080
## Creation de Pablo Escobar :
reponse=$(curl --request POST \
  --url http://$URL/shareskillsapi/v1/users \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRhdGFAZXhhbXBsZS5jb20ifQ.ZxHZBjy_2yRTE9tDVizgoiBTehz7D9DxYIDNYNl8NUw' \
  --header 'Content-Type: application/json' \
  --data '{
  "username":"Tata",
  "email":"tata@example.com",
  "displayName":"Pablo Escobar",
  "description": "Spécialiste du trafic de cocaïne",
  "birthdate":"1987-10-31T09:00:00.594Z"
}')

idPablo=$(echo $reponse | jq '.id')

## Creation de John Doe :
reponse=$(curl --request POST \
  --url http://$URL/shareskillsapi/v1/users \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImpvaG5kb2VAZXhhbXBsZS5jb20ifQ.68VonIVkD9b0SuPxpq69HvW_gJGmEpO80umeJSMvHiA' \
  --header 'Content-Type: application/json' \
  --data '{
  "username":"johndoe",
  "email":"johndoe@example.com",
  "displayName":"John Doe",
  "description": "Spécialiste du burger américain",
  "birthdate": "1990-08-03T09:00:00.594Z"
}')
idJohnDoe=$(echo $reponse | jq '.id')

## Creation de Alice Lee :
reponse=$(curl --request POST \
  --url http://$URL/shareskillsapi/v1/users \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFsaWNlbGVlQGV4YW1wbGUuY29tIn0.QJ0bFj2US1q2pWALSQUpXr7dvvKJkXIOWFeNMsmOU2s' \
  --header 'Content-Type: application/json' \
  --data '{
  "username":"alicelee",
  "email":"alicelee@example.com",
  "displayName":"Alice Lee",
  "birthdate": "1981-02-27T00:00:00.000Z"
}')
idAlice=$(echo $reponse | jq '.id')

## Creation de Mike Brown :
reponse=$(curl --request POST \
  --url http://$URL/shareskillsapi/v1/users \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1pa2Vicm93bkBleGFtcGxlLmNvbSJ9.ASWsuJ30tK0imXqGDWd-lf7-Tp0cqSGDrj5dD2tuyMs' \
  --header 'Content-Type: application/json' \
  --data '{
  "username":"mikebrown",
  "email":"mikebrown@example.com",
  "displayName":"Mike Brown",
  "birthdate": "1989-01-09T00:00:00.000Z"
}')
idMikeBrown=$(echo $reponse | jq '.id')

## Creation de Jane Smith :
reponse=$(curl --request POST \
  --url http://$URL/shareskillsapi/v1/users \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImphbmVzbWl0aEBleGFtcGxlLmNvbSJ9.cGgDyzzc-Yj6hPvht6PmCHGHkAT3UAD61TtoGY5iAAw' \
  --header 'Content-Type: application/json' \
  --data '{
  "username":"janesmith",
  "email":"janesmith@example.com",
  "displayName":"Jane Smith",
  "birthdate": "1973-11-03T09:00:00.000Z"
}')
idJaneSmith=$(echo $reponse | jq '.id')

## Alice Lee s'abonne à John Doe
curl --request POST \
  --url http://$URL/shareskillsapi/v1/connections \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFsaWNlbGVlQGV4YW1wbGUuY29tIn0.QJ0bFj2US1q2pWALSQUpXr7dvvKJkXIOWFeNMsmOU2s' \
  --header 'Content-Type: application/json' \
  --header 'email: alicelee@example.com' \
  --data '{
	"info": "Alice s abonne à John Doe",
	"creatorId": '$idJohnDoe'
}'
echo '-----------------------'

## Jane Smith s'abonne à John Doe :
curl --request POST \
  --url http://$URL/shareskillsapi/v1/connections \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImphbmVzbWl0aEBleGFtcGxlLmNvbSJ9.cGgDyzzc-Yj6hPvht6PmCHGHkAT3UAD61TtoGY5iAAw' \
  --header 'Content-Type: application/json' \
  --header 'email: janesmith@example.com' \
  --data '{
	"info": "Jane Smith s abonne à John Doe",
	"creatorId": '$idJohnDoe'
}'
echo '-----------------------'

## Mike Brown s abonne à Pablo Escobar
curl --request POST \
  --url http://$URL/shareskillsapi/v1/connections \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1pa2Vicm93bkBleGFtcGxlLmNvbSJ9.ASWsuJ30tK0imXqGDWd-lf7-Tp0cqSGDrj5dD2tuyMs' \
  --header 'Content-Type: application/json' \
  --header 'email: mikebrown@example.com' \
  --data '{
	"info": "Mike s abonne à Pablo Escobar",
	"creatorId": '$idPablo'
}'
echo '-----------------------'


## Pablo publie une publication privée
reponse=$(curl --request POST \
  --url http://$URL/shareskillsapi/v1/publications \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRhdGFAZXhhbXBsZS5jb20ifQ.ZxHZBjy_2yRTE9tDVizgoiBTehz7D9DxYIDNYNl8NUw' \
  --header 'Content-Type: application/json' \
  --header 'email: tata@example.com' \
  --data '{
	"content": "Je propose ici comment réaliser sa propre Meth...",
	"visible": false,
	"author": {
		"id": '$idPablo'
	}
}')
idPubPabloMeth=$(echo $reponse | jq '.id')

## Pablo publie une publication publique
reponse=$(curl --request POST \
  --url http://$URL/shareskillsapi/v1/publications \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRhdGFAZXhhbXBsZS5jb20ifQ.ZxHZBjy_2yRTE9tDVizgoiBTehz7D9DxYIDNYNl8NUw' \
  --header 'Content-Type: application/json' \
  --header 'email: tata@example.com' \
  --data '{
	"content": "Abonnez vous à mon compte pour apprendre bcp de choses....",
	"visible": true,
	"author": {
		"id": '$idPablo'
	}
}')
idPubPabloPublicite=$(echo $reponse | jq '.id')

## John Doe pubie une publication publique
reponse=$(curl --request POST \
  --url http://$URL/shareskillsapi/v1/publications \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImpvaG5kb2VAZXhhbXBsZS5jb20ifQ.68VonIVkD9b0SuPxpq69HvW_gJGmEpO80umeJSMvHiA' \
  --header 'Content-Type: application/json' \
  --header 'email: johndoe@example.com' \
  --data '{
	"content": "La recette ici du célèbre Hamburger...",
	"visible": true,
	"author": {
		"id": '$idJohnDoe'
	}
}')
idPubJohnHamburger=$(echo $reponse | jq '.id')

## JOhn Doe publie une publication privée
reponse=$(curl --request POST \
  --url http://$URL/shareskillsapi/v1/publications \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImpvaG5kb2VAZXhhbXBsZS5jb20ifQ.68VonIVkD9b0SuPxpq69HvW_gJGmEpO80umeJSMvHiA' \
  --header 'Content-Type: application/json' \
  --header 'email: johndoe@example.com' \
  --data '{
	"content": "La recette secrete du BigMac...",
	"visible": false,
	"author": {
		"id": '$idJohnDoe'
	}
}')
idPubJohnBigMac=$(echo $reponse | jq '.id')

## John Doe publie une publication publique
reponse=$(curl --request POST \
  --url http://$URL/shareskillsapi/v1/publications \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImpvaG5kb2VAZXhhbXBsZS5jb20ifQ.68VonIVkD9b0SuPxpq69HvW_gJGmEpO80umeJSMvHiA' \
  --header 'Content-Type: application/json' \
  --header 'email: johndoe@example.com' \
  --data '{
	"content": "La recette du pain burger",
	"visible": true,
	"author": {
		"id": '$idJohnDoe'
	}
}')
idPubJohnPainBurger=$(echo $reponse | jq '.id')

## Les likes ...
### Alice likes la recette du BigMac
curl --request POST \
  --url http://$URL/shareskillsapi/v1/publications/$idPubJohnBigMac/likes \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFsaWNlbGVlQGV4YW1wbGUuY29tIn0.QJ0bFj2US1q2pWALSQUpXr7dvvKJkXIOWFeNMsmOU2s' \
  --header 'email: alicelee@example.com'

echo '-----------------------'
### Mike likes la Meth...
curl --request POST \
  --url http://$URL/shareskillsapi/v1/publications/$idPubPabloMeth/likes \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1pa2Vicm93bkBleGFtcGxlLmNvbSJ9.ASWsuJ30tK0imXqGDWd-lf7-Tp0cqSGDrj5dD2tuyMs' \
  --header 'email: mikebrown@example.com'

## Les commentaires...

### Alice commente la recette du pain burger
curl --request POST \
  --url http://$URL/shareskillsapi/v1/publications/$idPubJohnPainBurger/comments \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFsaWNlbGVlQGV4YW1wbGUuY29tIn0.QJ0bFj2US1q2pWALSQUpXr7dvvKJkXIOWFeNMsmOU2s' \
  --header 'Content-Type: application/json' \
  --header 'email: alicelee@example.com' \
  --data '{
	"content": "Excellente la recette du pain Burger"
}'
echo '-----------------------'
### Alice commente la recette du BigMac
curl --request POST \
  --url http://$URL/shareskillsapi/v1/publications/$idPubJohnBigMac/comments \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFsaWNlbGVlQGV4YW1wbGUuY29tIn0.QJ0bFj2US1q2pWALSQUpXr7dvvKJkXIOWFeNMsmOU2s' \
  --header 'Content-Type: application/json' \
  --header 'email: alicelee@example.com' \
  --data '{
	"content": "Excellente la recette du BigMac!!"
}'
echo '-----------------------'
## Mike commente la recette de la Meth
curl --request POST \
  --url http://$URL/shareskillsapi/v1/publications/$idPubPabloMeth/comments \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1pa2Vicm93bkBleGFtcGxlLmNvbSJ9.ASWsuJ30tK0imXqGDWd-lf7-Tp0cqSGDrj5dD2tuyMs' \
  --header 'Content-Type: application/json' \
  --header 'email: mikebrown@example.com' \
  --data '{
	"content": "Excellente Meth!!!"
}'

echo '-----------------------'
## Johndoe ajout une image à son compte
curl --request PUT -T "src/test/resources/images/batman.png" \
  --url http://$URL/shareskillsapi/v1/users/accountpicture/png \
  --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImpvaG5kb2VAZXhhbXBsZS5jb20ifQ.68VonIVkD9b0SuPxpq69HvW_gJGmEpO80umeJSMvHiA' \
  --header 'Content-Type: application/octet-stream' \
  --header 'email: johndoe@example.com'

