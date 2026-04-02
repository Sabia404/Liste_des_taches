# Liste de Tâches

Application Android native développée en Kotlin suivant l'architecture MVP.

## Auteure
- Nom : Sabia Ahmed
- DA : 2371383

## Description
L'application Liste de Tâches permet à l'utilisateur d'organiser ses activités.
Elle offre la possibilité de créer, modifier, supprimer et compléter des tâches.
Les tâches sont organisées par catégories et par niveau de priorité.

## Fonctionnalités
- Créer, modifier et supprimer des tâches
- Marquer une tâche comme complétée
- Filtrer les tâches par catégorie ou par statut
- Indicateur visuel de priorité (rouge = haute, orange = moyenne, vert = basse)

## Architecture MVP
- **Modèle** : `Task`, `Category`, `Priority`, `TaskRepository`
- **Vue** : `MainActivity`, `TaskFormActivity`, `TaskAdapter`
- **Présentateur** : `TaskListPresenter`, `TaskFormPresenter`
- **Contrats** : `TaskListContract`, `TaskFormContract`

## Instructions d'installation
1. Cloner le dépôt Git
2. Ouvrir le projet dans Android Studio
3. Synchroniser Gradle
4. Lancer sur un émulateur ou appareil Android (API 24+)

## Instructions d'exécution
1. Lancer l'application
2. Appuyer sur le bouton + pour ajouter une tâche
3. Remplir le titre, la description, la catégorie et la priorité
4. Appuyer sur Enregistrer
5. Appuyer longuement sur une tâche pour la supprimer

## Tests
### Tests unitaires
- `TaskRepositoryTest` : teste les opérations du repository
- `TaskFormPresenterTest` : teste la logique du formulaire

### Tests bout-en-bout
- `TaskListE2ETest` : teste les parcours utilisateur principaux