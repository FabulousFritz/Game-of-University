# glowing-couscous

To run the most recent version go to the Master branch.

## CPSC 219 - Group 10 - The Game of University (an interactive board game)

This interactive game will be based off the board game, Life, where the user decides if they play against another user or a computer. All players move around the board following the number given on the wheel. The board spaces will have events which can increase or decrease the user’s wealth. Once each player has reached the end, the user with the amount of money is declared the winner.

## Prerequisites

An IDE, such as Eclipse, is required to implement and run the code from the repository. 

## Getting Started

Go into the code repository (glowingcouscous). Click the ‘Clone or Download’ green button on the right-hand side and copy the URL.

Open up an IDE, such as Eclipse, and select File —> Import —> Projects from Git —> Clone URI —> Enter the copied repository URL from Github and paste it into the URI —> Click ‘Next’ and enter your Github username and password —> Click ‘Finish’.

The code repository will now be inside your IDE. 

Updated code on Github may be reflected in your IDE by right clicking on the repository in the IDE -> Team -> Pull. New or updated code will be shown in the IDE.

## Running the code

To run the logic code:
Once the code repository has been downloaded, open up the Main branch. To run the code, run Main.

The user will be prompted to enter their name (or a name of their choice). The project will then ask the user if another human player is playing. If yes is selected, enter the second human player’s name. If no is selected, the code will prompt the user for a name to give the Computer player. Once all players have been named, the game will begin. A maximum of two players can play at one time.

The code will run and each player will get a spin. On each spin, the player’s position on the board is advanced according to the random wheel spin. Players can move anywhere from a minimum of 1 space, to a maximum of 10 spaces in one turn. The end of the board is when the players have reached space 49. Each space on the board will either add to, or take from, the user’s bank account. The object of the game is to finish with the most money.  The user will be prompted at the end, and the winner will be declared. 

To run the functional code (with GUI applications):

Open up the branch called GUIMain and run the code. 

A start screen will appear with three options: Start New Game, About, and Exit Game. If the user clicks on 'About', they will be given a brief description of the game and how to play. If the user selects 'Start New Game', the game will begin.

The user will then be prompted with the game set-up. The user can choose their own name and colour for their board piece, as well as the name and colour for the second player (this player can be a human or a computer, with a default name for a computer player being given). Click on the 'Get Started!' button and the game will begin. 

The player whose name was inputted first will begin the game. Click the button in the centre of the screen which says the player's name and 'spin the wheel!'. A wheel will pop-up, with a 'spin' button located in the centre. Click on 'spin' and a random number from 1-10 will be selected. After the wheel is spun, close the window. The game will advance the player's token to the appropriate location, corresponding to the number spun. The player will be prompted with a pop-up that explains what event occurred on their turn. The amount of money they gained or lost will be shown to them. To end the turn, close the pop-up and click 'End Turn' in the upper-right hand corner. At all times, a running total of both player's bank accounts will be displayed at the bottom of the screen. Each player begins the game with $100. 

The second player will then proceed to play, following the same steps as outlined above. If a computer player is selected as the second player, the wheel spin will be automatically generated, and a single pop-up informing the user of what occurred on the computer's turn will be displayed.

If the user would like to exit the game, they can select the 'Drop Out' button in the lower right-hand corner of the screen. This will generate a new screen which states the winner as whichever player had the most amount of money at that time. 

Once both players have reached the end of the board, a new pop-up will be generated which declares the winner and their final amount of money. The user then has the option to return to the Start menu.

## Authors

- Celia Skaley
- Felicity Merrick
- Thanh Hien Nguyen-Mai
- Mallory Zorman

## Acknowledgments

- Inspiration for this project was taken from the board game Life.
- All background graphics were created on the website www.canva.com
