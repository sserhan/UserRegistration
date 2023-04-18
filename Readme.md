UserRegistration : 

Application SpringBoot utilisant une base de données intégrée (H2).
Elle est réinitialisée à chaque redéploiement de l'application.

Voici les deux APIs exposées par l'application :

- POST http://localhost:8080/person
avec dans le body un JSON comme celui ci-dessous
{
"name": "TEST",
"birthdate": "1990-01-01",
"country": "France",
"phone": "0666666666",
"gender": "M"
}

- GET http://localhost:8080/person/{name}
avec entre crochet le nom de la personne recherchée.
Il faut lancer la requête POST avant le GET puis rechercher 
la personne ajoutée : http://localhost:8080/person/TEST
