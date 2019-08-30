# Cosgame
This project features some board games frequently played with my friends.  This is a **Spring Boot** project with **AngularJS** and **Bootstrap** for Front-End and MongoDB for Database.  The corresponding languages are Java, JavaScript, HTML and CSS.  Right now Cosgame has Dominion and Mafia.

## Dominion (partially completed)
Dominion is a deck-building game.  The general rules of the game can be seen in the following link:
http://wiki.dominionstrategy.com/index.php/Dominion
After played several games, I started brainstorming my own cards for the game and use those cards by calling an official card to the card I created.  During my last work term, I was working on a project using Spring Boot and AngularJS.  At the end of the turn, I realized I can make a web service so that I can play my own cards with my friends.  Right now, the service supports bot games and the game can go through.

![avatar](https://s2.ax1x.com/2019/08/16/meI5UP.md.png)

This is the login page.  User information is stored in MongoDB and new user is able to register

![avatar](https://s2.ax1x.com/2019/08/30/mjWxwd.md.png)

This is the game play page.  The service now realizes all the game mechanics including playing Action cards and Treasure cards, buying new cards, and end game handle.

This service is under fast development and will be updated frequently.

## Mafia (under development)
Mafia is an old-school game that I played from more than ten years ago.  It is a game about identity.  Each player has a role.  The innocents and the police must try their best to figure out who are the mafias.  The detailed introduction of the game is provided below.
https://en.wikipedia.org/wiki/Mafia_(party_game)
This game was developed on the purpose of MondoDB demo because compare to Dominion, this game has more simplified structure.  The development of this game is paused for now, but it will be finished eventually.
