# Equipe

- Anis REKIMA
- Adam RAMJATTAN
- Mehdi HAMMADOU
- Nilaxan SURESH

# Sujet

[Le sujet 2024](https://www.fil.univ-lille.fr/~varre/portail/l2s4-projet/sujet2024.pdf)

# UML DU PROJET

1. Ouvrir le fichier "UmlProjet.mdj" avec le logiciel StarUml.
1. Specification :  - Les fléches représente les héritages, les fléches avec un carré noir représente les héritages avec leur composition et les fléches avec des pointillés représente l'implémentation des interfaces.


##Livrables

## Livrable 1

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 2

### Compilation et execution :
- via l2s4-projet-2024 : javac -sourcepath src src/projet/*.java -d classes
- via l2s4-projet-2024 : java -classpath classes projet.BoardMain
- Pour l'executable : jar cvfe game.jar projet.BoardMain classes  -C classes projet
- Pour l'executer : java -jar game.jar
- Compilation : javac -classpath junit-console.jar:classes src/test/*.java
- Execution : java -jar junit-console.jar -classpath test:classes -scan-classpath
- Documentation : javadoc -sourcepath src -subpackages projet -d docs

### UML:
L'UML est a lancer sur STARUML pour un meilleur rendue.

### Atteinte des objectifs
Nous avons reussi à implementer les players, les zombies et les items dans la map, les players sont representer par un J suivit de leur nom(ici un chiffre) les items par un M et les zombie par un Z. Nous avons été en capacité à pouvoir faire bouger les players au nord mais pas que ils peuvent aussi bouger au sud, a l'est et a l'ouest. Rajout aussi du Training Board. 

### Difficultés restant à résoudre
Changer le type de classe de player( faire un player en classe abstraite) car cette erreur nous a mené à des problemes lors de l'ajout des players dans la liste. Et il nous reste à améliorer le rendue du display notamment en affichant les portes correctement


## Livrable 3

### Compilation et execution :
- via l2s4-projet-2024 : javac -sourcepath src src/projet/*.java -d classes
- via l2s4-projet-2024 : java -classpath classes projet.BoardMain
- Pour l'executable : jar cvfe game.jar projet.BoardMain classes  -C classes projet
- Pour l'executer : java -jar game.jar
- Compilation : javac -classpath junit-console.jar:classes src/test/projet/*.java
- Execution : java -jar junit-console.jar -classpath test:classes -scan-classpath
- Documentation : javadoc -sourcepath src -subpackages projet -d docs

pour le makefile :
- make all pour lancer le boardMain
- make test-c pour compiler les test
- make teste pour éxecuter les test  
- make doc pour la documentation
- make exec-c pour compiler l'executable
- make exec pour lancer l'executable
Nota Bene: si les make ne marche pas, il faut se referer au commander si dessus. 

### UML :
Comme pour le livrable 2 l'UML est a lancer sur STARUML pour un meilleur rendue.

### Atteinte des objectifs
Nous avons choisis de modéliser les actions à l'aide d'interface. Dans un premier temps nous voulions coder les actions directement dans les classes Player et Zombie mais finalement nous avons optés pour les interfaces car nous n'arrivions pas a accéder aux attributs da la classe Board dans la classe Player ou Zombie.
Certaine fois les méthodes renvoie des exception que nous avons créer nous réglerons cela avec un try catch pour le dernier livrable.

### Difficultés restant à résoudre

## Livrable 4

### Compilation et execution :
- via l2s4-projet-2024 : javac -sourcepath src src/projet/*.java -d classes
- via l2s4-projet-2024 : java -classpath classes projet.Game
- Pour l'executable : jar cvfe game.jar projet.Game classes  -C classes projet
- Pour l'executer : java -jar game.jar
- Compilation : javac -classpath junit-console.jar:classes src/test/projet/*.java
- Execution : java -jar junit-console.jar -classpath test:classes -scan-classpath
- Documentation : javadoc -sourcepath src -subpackages projet -d docs
- GameTrainer : via l2s4-projet-2024 : java -classpath classes projet.GameTrainer
- Pour éxectable : jar cvfe game_trainer.jar projet.GameTrainer classes  -C classes projet
- Pour l'éxecuter : java -jar game_trainer.jar


pour le makefile :
- make all (pour lancer le game)
- make test-c (pour compiler les test)
- make teste ( pour éxecuter les test ) 
- make doc (pour la documentation)
- make exec-c (pour compiler l'executable)
- make exec (pour lancer l'executable)
- make train (pour lancer le game trainer )
- game-c (executable du game)
- game (executé le jeu)
- game_trainer-c (executable du trainer game)
- gamer_trainer (executer du trainer)

Nota Bene: si les make ne marche pas, il faut se referer au commander si dessus. 

### UML :
Comme pour le livrable 2 l'UML est a lancer sur STARUML pour un meilleur rendue.

### Atteinte des objectifs
Tous les objectifs ont été atteint. Il n'y a rien n'a signaler

### Difficultés restant à résoudre
Il n'y a aucun problème qui n'a pas été resolu. Il nous reste juste à reflechir a des nouvelles fonctions dans le jeu

# Journal de bord

## Semaine 1
lien pour le site uml:  
https://lucid.app/lucidchart/82777509-69d3-4ee7-8ce5-b82c0f55ffce/edit?view_items=Vdo6XCZ0EUMq&invitationId=inv_cb9a8748-16d0-4661-9941-5bb73bd160fb  
Ce mercredi nous avons crée et importer le git sur éclipse, et commencer la création de l'uml sur le site ci-dessus.
## Semaine 2
Nous avons determiner les règles du plateau du jeu et son application avec avec l'objet immeuble et séparement a cela nous avons definis les règles des cellules.  
Pour cela nous avons crée un premier diagramme UML du plateau.  
Il possède une methode initBoard qui va crée un plateau d'une hauteur height et d'une largeur width. Cette méthode va nous permettre d'initié le lieu d'apparition des joueurs, les rues, les batiments,etc...
## Semaine 3
Aujourd'hui nous avons commencé à programmer les différente celulle du plateau de jeu.  
Mais hélas pour l'algorithme de l'initBoard ce fut un échec car nous ne trouvions pas de manière de découper le plateau convenablement.

## Semaine 4
Finalisation de notre classe Board pour initialiser la Map.

## Semaine 5
1. UML :

	* ajout de la classe Item et de ces héritiers(Weapon, Utilities et Heal) et de leur propre héritiers.  
	
	* ajout de la class Zombie et de ces héritiers(Walkers, Runners, Balaise, Abomination).  
		
2. Initialisation de chacunes des classes qui ont été rajouté dans l'UML.

## Semaine 6

1. Durant cette scéance nous avons continués l'UML et nous avaon debuter la pré-implementation des actions dans la clases player.
2. Début de la finalisation du placement des acteurs et des équipements dans la map.

## Semaine 7

1. Nous avons ajouter l'UML "UmlProjet.mdj" qu'il faut ouvrir avec StarUml, sur lequel nous allons travailler jusqu'à fin de notre projet.
2. Ajout de la classe TrainingBoard et de son main TrainingBoardMain.
3. Dans la classe BoardMain, nous avons permis au joueur de commencer la partie avec une Flask dans la main et une Map dans son inventaire.
4. Nous avons également permis aux zombies, aux joueurs et aux Items d'apparaître sur le Board. 

## Semaine 8

1. Nous avons reflechis comment nous allions modeliser les actions des Players et des Zombies.
2. Nous avons décider de les modeliser sous forme d'interface.

## Semaine 9

1. Commencemant du codage des methodes attack, makeNoise, et lookAroundYourself dans nos interfaces.

## Semaine 10

Rajout des actions makeNoise, searchInRoom, OpenTheDoor on a corriger lookAround et effectuer les tests des differentes méthodes ajouter. Finalisation des méthodes attack pour les zombies et les player ainsi que leurs méthode move.

## Semaine 11

Implementation des attaques pour les zombies qui tue le player si il n'a plus de points de vie.
Arrete le jeu si le niveau 30 ou si joueur n'a plus de points de vie ou si tout les zombies sont morts.

## Semaine 12
Demande aux joueurs sur quelle table il veut jouer.
Mouvement des zombies en fonctions du bruits. Appararition de zombie lors de l'ouverture de la porte. Correction sur TakeanItem TakeaHand.
Finalisation du jeu avec ajouts des listchooser qui permet de choisir les actions parmis toute la listes des actions. 

#Livra