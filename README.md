# Projet M2 MIAGE - MenuisMiage

Ce projet a pour objectif de mettre en pratique la création et la manipulation de l’Intégration d’Application d’Entreprise (EAI Enterprise
Application Integration) avec différents clients. La réalisation de ce projet a pour objectif d’améliorer l’efficacité du système d’information dans le processus des prestations commerciales d’une entreprise nommée “Menuis’Miage”. L’ensemble des règles de gestion nous ont été transmises dans le sujet de ce projet.

Pour développer et mettre en place le système d’information de Menuis’Miage avec l’ensemble des clients nécessaires à
son fonctionnement, nous avons mis en place différents clients.

## Service Charger Affaire
`ServiceChargerAffaire` : Projet Maven Java EE (EAR, EJB, Web).  
`MenuisMiageShared` : Projet Maven Java qui permet le partage de certaines ressources (Exceptions, TransientObject, Exposition).  
`AppCA` : Client lourd destiné aux Charger d'Affaire pour gérer les affaires.  

## Service Commercial
`ServiceCommercial` : Projet Maven JEE (EAR, EJB, Web).    
`Postman-AppCommercial` : Collection Postman avec les différents appels vers l'API Rest du Service Commercial.  

## Service Achat
`ServiceAchat` : Projet Maven JEE (EAR, EJB, Web).  
`MenuisMiageSharedAchat` : Projet Maven Java qui permet le partage de certaines ressources (Exceptions, TransientObject, Exposition).  
`AppAchat` : Client lourd destiné au personnel du Service Achat. Dans le cadre de ce projet, les fonctionnalités reservés aux Magasinier sont les seules a être implémentées.  

## Service Comptable
`ServiceComptable` : Projet Maven JEE (EAR, EJB, Web).  
`MenuisMiageSharedComptable` : Projet Maven Java qui permet le partage de certaines ressources (Exceptions).  

## Service Poseur
`ServicePoseur` : Projet Maven JEE (EAR, EJB, Web).  
`Postman-AppPoseur` : Collection Postman avec les différents appels vers l'API Rest du Service Poseur.  

## Architecture JMS

Voici l'architecture JMS mise en place dans le projet.

![Architecture JMS](https://user-images.githubusercontent.com/48246043/144722684-9f530616-d95f-48cb-a079-b01ed2b8967c.png)
 
# Configuration GlassFish

Pour exécuter le projet correctement le projet sur votre PC, il vous faudra installer et configurer [Glassfish 5.0](https://javaee.github.io/glassfish/download)  
Ensuite, il faudra lancer le serveur Glassfish et vous rendre dans la console d'adminstration pour configurer JMS.

__Attention : Pour les clients lourds, il faudra vérifier la configuration RMI avec le port ORB.__  

## Connection Factory

Créer une connection factrory : `TPEAIConnectionFactory`

## TOPICS

Créer les topics suivants :
- `CommandeSaisie`
- `CommandeTransmiseFroun`
- `NotificationAffaire`

## QUEUES

Créer les queues suivantes :
- `EnregistreLivraison`
- `Encaissement`
- `PoseValidee`

# Documentation des APIs

Lien Swagger vers la documentation des APIs [MenuisMiage Swagger](https://app.swaggerhub.com/apis-docs/christian.michielan/MenuisMiage/1.0.0#/).  

*Nb : l'API du service Fournisseur est hors du périmètre de ce projet, son appel est simulé.*
