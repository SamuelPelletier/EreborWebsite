Accés : \
https://pjkwozlo.preview.infomaniak.website

Installation : 

1) Télécharger le git (https://github.com/SamuelPelletier/EreborWebsite)
2) Installer une base de donnée et activer PDO
3) Exécuter le script SQL
4) Installer un serveur PHP 7.0
5) Ajouter des données factices dans la base de données

Une fois les serveurs lancer accéder au site web.

Fonctionnalités : \
A) Géolocalisation automatique est quasiment obligatoire \
B) Météo en fonction de la géolocalisation \
C) Connexion et inscription d'un utilisateur \
D) Affichage du trafic en temps réel en fonction de la géolocalisation \
E) Lien pour les transports en commun pour les principales ville de France \
F) Une page d'à propos \
G) Un kodami code caché \
H) Un calendrier de l'avent sur le théme d'Ubisoft

Information technique : \
Le site web est réalisé sur 90% en une seul page qui est recharger par des script JS. \
Les 10% restant sont les deux formulaires de connexion et d'inscription.

La solution du konami code est haut, haut, bas, bas, gauche, droite, gauche, droite, b,a,b,a. \
Nous avons utilisé un simple script JS pour le réaliser et ensuite nous l'avons offusqué. \
Ce kodami code permet d'accéder au calendrier de l'avant sur le théme d'Ubisoft. 

Nous avons utilisé deux API pour créer ce projet, celle de google pour google traffic et celle de 
yahoo pour la météo.

Nous avons également un certificat SSL afin d'utiliser la géolocalisation de maniére sécurisé.

Enfin nous avons créer une mini api rest afin de communiquer avec notre application Android (GPSam)

Projet : \
Notre projet consiste à prévenir les utilisateurs des risques que se soit par la météo ou le traffic.\
Nous avons choisis de faire une application afin d'appeler les secours le plus rapidement possible.\ 
Elle devait permettre également de pouvoir dire au utilisateur les risques proches sur la route.\
De plus il devait être possible de se signaler auprés des autres utilisateurs que vous êtes un SAM 
et donc de pouvoir reconduire des personnes.\
Enfin le site Web devait permettre de prévenir des risques lié à un événement.