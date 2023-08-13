# Battleship Game

The Battleship Game is a Java console-based two-player strategy game. Each player places their ships on a grid and then takes turns firing at their opponent's grid to sink their ships. The first player to sink all the opponent's ships wins.

## How to Play

1. Compile and run the `Main.java` file using a Java compiler (e.g., `javac Main.java` and `java Main`).
2. Players take turns entering coordinates to fire shots at their opponent's grid.
3. The game provides feedback on hits and misses, and displays the current state of both players' grids.
4. The game ends when one player sinks all the opponent's ships.

## Features

- Two-player interactive gameplay.
- Players can place their ships on the grid.
- Players take turns firing shots at their opponent's grid.
- Visual display of the game board with ships and shots.
- Detects when a player has sunk all opponent's ships and declares the winner.

## How to Place Ships

1. Players take turns placing their ships on their grid.
2. Players input the coordinates for the start and end of the ship (e.g., A1 A5 or B5 E5).
3. The game validates the coordinates and adds the ship to the grid if valid.
4. Different ship types have different lengths: Aircraft Carrier (5 cells), Battleship (4 cells), Cruiser (3 cells), Submarine (3 cells), and Destroyer (2 cells).

## Technologies Used

- Java for the core game logic.
- Command-line interface for input and display.

## Notes

- This is a simplified version of the traditional Battleship game.
- Ships are placed manually by players, and coordinates are entered manually for each shot.

## Future Improvements

- Enhance user interface with graphical elements.
- Add more game variations and customizations.


