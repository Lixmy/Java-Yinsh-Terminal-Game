This was the first half of a University project to recreate a variant of the board game known as Yinsh using Java. This project was done in groups of 4 people utilizing Git to complete the project in under 2 days.
It had to contain:
- A seperate imported project called "hexagonalcoordinates" that manages all the calculations in relation to the players movements.
  It contains 2 types of game modes; Pointy, which uses 3 coordinates to move, and Flat, which uses only 2.
- Tests for every functionality that is implemented, which were coded using JUnit and can be found in the test folder.
- A working AI against which the player can play, that can determine the best move to make depending on the situation.
- A working interface (optional) that allows the player to play the game either against another player or against the AI, with the choice between the 2 game modes.
- A well documented code, explaining the use of the methods that were created.

The rules of the game are as follows:
"The game will take place on a hexagonal playing field.
At the beginning of the game, each player places five rings (empty circles) wherever they wish on the field. 
These rings can be moved according to certain rules. By moving, the rings will create or modify tokens (filled circles).
The objective is to create lines of five tokens. When a line is created, the player chooses a ring and must remove it along with the newly created line. 
The winner is the first to successfully remove three rings.
If a move creates multiple lines, the player must remove as many rings as there are lines. 
It is possible that a move might create a black line and a white line; in this case, both players remove one ring and one line."

More details can be found online (https://en.wikipedia.org/wiki/YINSH)

the image.png file is a picture of the final Git activity tree hosted on the University's Redmine.

/// LAUNCHING THE GAME ///

- Open the project with Eclipse
- Launch CUIMain
