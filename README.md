# Flappy-Bird-Game
This is a simple Flappy Bird game clone developed in Java using javax.swing and java.awt libraries. The game involves controlling a bird to fly through gaps between pipes without hitting them. The score increases each time the bird successfully passes through a set of pipes.

## Features
Smooth Bird Animation: The bird falls due to gravity and can "jump" when the spacebar is pressed.
Procedural Pipes: Pipes are randomly generated to make the game challenging and dynamic.
Score System: The score increases for every pair of pipes successfully passed.
Restart Functionality: Press "R" to restart the game after a game-over.

## Gameplay Controls
Spacebar: Make the bird jump.
R: Restart the game after a game-over.

## Installation and Usage
1. Clone the Repository
2. Compile and Run the Game
3. javac FlappyBird.java
4. java FlappyBird
### Alternatively, open the FlappyBird.java file in your favorite IDE, such as IntelliJ IDEA or Eclipse, and run the main method.

## How to Play
Press the spacebar to make the bird jump.
Avoid hitting the pipes or falling to the ground.
The score increases each time you pass a pair of pipes.
If you hit a pipe or fall out of bounds, the game is over, and your score will be displayed.
Press R to restart the game and try again.

### Code Structure
FlappyBird.java: This single file contains the main game logic, including bird movement, pipe spawning, collision detection, and score tracking.
Key Components
Game Area: Defined with a width of 800 and a height of 600.
Gravity and Jump Strength: Gravity pulls the bird down continuously, while the jump gives a boost to move up.
Pipe Generation: Pipes are generated with a gap for the bird to pass through, making each run unique.
Future Enhancements (Ideas)
Sound Effects: Adding sounds for jump, game-over, and scoring events.
Difficulty Levels: Increase pipe speed or decrease gap size to add more challenge.
Graphics Improvement: Use custom images or sprites for the bird and pipes.
