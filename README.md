# 🏥 HealthCare+ — API REST de Gestion Médicale

# 1. Nom du projet

**Nom du projet :** HealthCare+ — API REST de Gestion Médicale

---

# 2. Présentation du projet

HealthCare+ est une API REST développée avec Spring Boot permettant de gérer un système médical complet.  
Elle s'adresse aux établissements médicaux, cabinets et professionnels de santé souhaitant centraliser la gestion des patients, médecins, rendez-vous et dossiers médicaux.  
Son objectif principal est de fournir une solution backend organisée, fiable et évolutive pour faciliter la gestion des données médicales.

---

# 3. Problématique

Le problème identifié est que la gestion des informations médicales peut devenir complexe lorsque les données des patients, des médecins et des rendez-vous sont dispersées ou mal organisées.

La solution proposée permet de centraliser ces informations grâce à une API REST permettant de gérer efficacement les patients, les médecins, les rendez-vous et les dossiers médicaux avec une architecture claire et maintenable.

---

# 4. Fonctionnalités principales

- Créer, consulter, modifier et supprimer des patients.
- Gérer les informations des médecins et leurs spécialités.
- Planifier et gérer les rendez-vous médicaux.
- Consulter et gérer les dossiers médicaux des patients.
- Valider les données entrantes et gérer les erreurs de manière centralisée.
- Documenter les endpoints de l’API avec Swagger OpenAPI.

---

# 5. Technologies utilisées

| Technologie | Utilisation dans le projet |
|-------------|----------------------------|
| Java 21 | Développement de l’application backend |
| Spring Boot | Création de l’API REST et gestion de la logique applicative |
| Spring Data JPA | Interaction avec la base de données |
| Hibernate | Mapping objet-relationnel entre les entités Java et la base de données |
| MySQL | Stockage des données médicales |
| Flyway | Gestion des migrations et versions de la base de données |
| Maven | Gestion des dépendances et construction du projet |
| MapStruct | Conversion entre les entités et les DTO |
| Swagger OpenAPI | Documentation et test des endpoints REST |
| JUnit | Réalisation des tests unitaires |
| Docker | Conteneurisation de l’application |
| Git & GitHub | Gestion du versionnement du code |

---

# 6. Installation et lancement

## 6.1 Prérequis

Pour utiliser ce projet, vous devez disposer de :

- Java 21
- Maven
- MySQL
- Docker (optionnel)
- Git

---

## 6.2 Cloner le dépôt

```bash
git clone LIEN_DU_DEPOT
```

Commande du projet :

```bash
git clone https://github.com/VOTRE_USERNAME/HealthCarePlus.git
```

---

## 6.3 Ouvrir le dossier

```bash
cd HealthCarePlus
```

---

## 6.4 Installer les dépendances

Avec Maven :

```bash
mvn clean install
```

---

## 6.5 Variables d'environnement

Créer ou configurer le fichier `application.properties` :

```properties
DATABASE_URL=jdbc:mysql://localhost:3306/healthcare
DATABASE_USERNAME=root
DATABASE_PASSWORD=votre_mot_de_passe
```

---

## 6.6 Lancer le projet

Avec Maven :

```bash
mvn spring-boot:run
```

Ou depuis votre IDE :

Lancer la classe principale Spring Boot de l'application.

---

## 6.7 Ouvrir le projet

Après le lancement :

```
http://localhost:8080
```

Documentation Swagger :

```
http://localhost:8080/swagger-ui/index.html
```

---

# 7. Captures d'écran

## Capture 1

### Titre

Documentation Swagger de l'API

### Image

```md
![Swagger API](chemin-vers-image.png)
```

### Explication

Cette capture montre la documentation interactive Swagger permettant de tester les différents endpoints REST de l'application.

---

## Capture 2

### Titre

Structure de l'architecture du projet

### Image

```md
![Architecture Projet](chemin-vers-image.png)
```

### Explication

Cette capture montre l'organisation du projet basée sur une architecture MVC avec les couches Controller, Service, Repository, Entity, DTO et Mapper.

---

# 8. Contribution personnelle

Ma contribution principale a porté sur la réalisation complète du projet en individuel.

J'ai travaillé sur toutes les étapes du développement : conception de la base de données, création des entités, développement des services et contrôleurs REST, création des DTO et des mappers, gestion des exceptions, configuration de Flyway, documentation Swagger et tests unitaires.

J'ai été responsable de l'ensemble de l'implémentation backend et de la mise en place d'une API fonctionnelle répondant aux besoins de gestion médicale.

---

# 9. Difficultés rencontrées

## Difficulté 1

### Problème rencontré

La gestion des relations entre les différentes entités médicales (patients, médecins, rendez-vous et dossiers médicaux) représentait une difficulté importante.

### Recherches / Tests

J'ai étudié les relations JPA/Hibernate comme OneToMany, ManyToOne et OneToOne afin de choisir la meilleure modélisation de la base de données.

### Solution

J'ai configuré correctement les annotations JPA et organisé les entités afin d'assurer une bonne gestion des relations et des données.

### Ce que j'ai appris

Cette difficulté m'a permis d'améliorer mes compétences en conception de bases de données relationnelles avec Hibernate.

---

## Difficulté 2

### Problème rencontré

La gestion des erreurs et la validation des données envoyées par les utilisateurs.

### Recherches / Tests

J'ai étudié les bonnes pratiques de gestion des exceptions avec Spring Boot et la validation avec les annotations dédiées.

### Solution

J'ai mis en place une gestion centralisée des exceptions et des validations afin de retourner des réponses API plus claires.

### Ce que j'ai appris

J'ai appris à construire des API REST plus robustes et professionnelles.

---

# 10. Améliorations possibles

Dans une prochaine version, je pourrais :

- Développer une interface Front-End pour faciliter l'utilisation de l'application.
- Ajouter un système d'authentification et de gestion des rôles avec Spring Security et JWT.
- Ajouter davantage de tests automatisés pour améliorer la couverture du projet.
- Déployer l'application sur une plateforme cloud.

### Conclusion

Ces améliorations permettraient de transformer l'API actuelle en une solution médicale complète avec une meilleure expérience utilisateur, une sécurité renforcée et une disponibilité en production.

---
