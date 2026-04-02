# Rapport d'utilisation de l'intelligence artificielle générative

## Informations
- **Nom** : Sabia Ahmed
- **DA** : 2371383
- **Outil d'IAg utilisé** : Claude (Anthropic) - claude.ai

---

## Requêtes formulées

1. Génération de la structure complète du projet MVP
2. Génération des fichiers du modèle (Task, Category, Priority)
3. Génération du TaskRepository et explication de chaque fonction
4. Génération des contrats MVP (TaskListContract, TaskFormContract)
5. Génération des présentateurs (TaskListPresenter, TaskFormPresenter)
6. Génération des layouts XML (activity_main, item_task, activity_task_form)
7. Génération des vues (MainActivity, TaskFormActivity, TaskAdapter)
8. Génération des tests unitaires et bout-en-bout
9. Correction des erreurs de compilation

---

## Ajustements et itérations

- Plusieurs erreurs de compilation ont nécessité des corrections :
    - `showTask` corrigé en `showTasks` dans le contrat
    - `navigateToEditTask()` corrigé pour accepter un paramètre `Task`
    - `CharCategory` corrigé en `Category` dans le contrat
    - `getTask()` corrigé en `getAllTasks()` dans le repository
    - `isDone` ajouté lors de la création d'une tâche dans le présentateur

---

## Démarches effectuées pour comprendre le code

- Demande d'explication détaillée pour chaque fonction du repository
- Demande d'explication de la logique de `updateTask()` avec un exemple concret
- Demande d'explication du fonctionnement de `loadTask()` et du paramètre nullable
- Demande d'explication de chaque fonction des contrats MVP
- Demande de commentaires dans le code pour mieux comprendre

---

## Modifications apportées au code généré

| Fichier | Modification | Raison |
|---------|-------------|--------|
| `Task.kt` | Suppression des valeurs par défaut sauf `id` et `dateCreation` | Forcer la spécification explicite des champs obligatoires |
| `TaskRepository.kt` | Renommage de `getTask()` en `getAllTasks()` | Cohérence avec les noms utilisés dans les présentateurs |
| `TaskRepository.kt` | Modification de `deleteTask()` pour accepter un `Task` au lieu d'un `String` | Choix personnel de conception |
| `TaskListContract.kt` | Correction de `CharCategory` en `Category` | Erreur de frappe lors de la saisie |
| `TaskFormPresenter.kt` | Ajout de `isDone = false` lors de la création | Correction d'une erreur de compilation |

---

## Réflexion critique sur l'apport de l'IAg

L'utilisation de Claude m'a permis de structurer rapidement un projet Android complet
en suivant l'architecture MVP. L'IAg a été particulièrement utile pour générer la
structure de base et expliquer les concepts comme les contrats, les interfaces et
la séparation des responsabilités.

Cependant, j'ai remarqué que le code généré ne correspondait pas toujours exactement
à mon implémentation — par exemple les noms de fonctions dans le repository étaient
différents de ce que j'avais écrit, ce qui a causé des erreurs de compilation.
Cela m'a appris l'importance de bien comprendre chaque fichier avant de passer au suivant.

Les explications demandées pour chaque fonction m'ont aidée à comprendre la logique
derrière le code, notamment la fonction `updateTask()` qui utilise `indexOfFirst`
pour trouver et remplacer une tâche par sa position dans la liste.

En conclusion, l'IAg est un outil utile pour accélérer le développement, mais
il est essentiel de comprendre le code généré et de l'adapter à ses besoins
plutôt que de simplement le copier.