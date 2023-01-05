# Contexte
This Kata goal is to implement a simple tennis score computer.

The scoring system consist in one game, divided by points :

1.  Each player starts a game with 0 point.
2.  If the player wins the 1st ball, he will have 15 points. 2nd balls won : 30 points. 3rd ball won : 40points.
3.  If a player have 40 points and wins the ball, he wins the game, however there are special rules.
4.  If both players have 40 points the players are “deuce”.
5.  If the game is in deuce, the winner of the ball will have advantage
6.  If the player with advantage wins the ball he wins the game
7.  If the player without advantage wins the ball they are back at “deuce”.

You can found more details about the rules here : ([http://en.wikipedia.org/wiki/Tennis#Scoring](http://en.wikipedia.org/wiki/Tennis#Scoring "http://en.wikipedia.org/wiki/tennis#scoring") )

Here we want you to develop a java method that will take a String as input containing the character ‘A’ or ‘B’. The character ‘A’ corresponding to “player A won the ball”, and ‘B’ corresponding to “player B won the ball”. The java method should print the score after each won ball (for example : “Player A : 15 / Player B : 30”) and print the winner of the game.

For example the following input “ABABAA” should print :

-   “Player A : 15 / Player B : 0”
-   “Player A : 15 / Player B : 15”
-   “Player A : 30 / Player B : 15”
-   “Player A : 30 / Player B : 30”
-   “Player A : 40 / Player B : 30”
-   “Player A wins the game

# Description
* Le projet a été créé en implémentant l'architecture hexagonale
* Le besoin a été un peu plus poussé pour montrer certaines bonnes pratiques
* Pour démarrer le projet en local : 
`mvn spring-boot:run -Dspring-boot.run.arguments="ABBBBBABBABAAAAB"`

