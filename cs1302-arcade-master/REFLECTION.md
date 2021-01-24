# Reflection

Add to this file to satisfy the "Reflection Updates" non-functional requirement
for this project. Please keep this document organized using Markdown. If you
click on this file in your team's GitHub repository website, then you will see
that the Markdown is transformed into nice looking HTML.

Here is a sample entry (delete this line; also: feel free to copy/paste/modify):

## SUN 2019-04-05 @ 11:55 PM EST

1. **DONE:** Downloaded the skeleton code.

2. **TODO:** Finish reading the project description.

3. **PROB:** Had a hard time scheduling physicial meetings with each other at
   first, but now that we've settled on a regular day/time things are looking
   great!

    ----------------------------------------------

## THUR 2019-11-14 @ 6:00 PM EST

1. **DONE:** Decided to code Tetris and Mancala.
             Learned how to play Mancala.
             Discussed how to combine the two games into one application.
             Made a google calendar to update the dates when we are available.

2. **TODO:** Start implementing Tetris.

3. **PROB:** We were having trouble figuring out how to combine the code for the two games into one file.

    ----------------------------------------------

## SAT 2019-11-16 @ 3:22 PM EST

1. **DONE:** COMBINED THE TWO FINALLY!!!!!
             made the welcome screen buttons work
             made two different classes for the two games
             Drew a scene graph for the app
             Made back buttons for the two scenes so now we can switch between scenes

2. **TODO:** tetris - make the shapes and group them together and store them in an array.
             mancala - make board.

3. **PROB:** We were having trouble figuring out how to make the grid for Tetris.

    ----------------------------------------------

## SUN 2019-11-17 @ 11:00 PM EST

1. **DONE:** Decided to use 4 rectangles in order to make the tetris blocks.
             Made methods for square and bar shaped blocks.

2. **TODO:** Complete the methods for the rest of the shapes and test them out.

3. **PROB:** Initially we thought using the Polygon class would be helpful in making the
             the shapes but it was more difficult. We decided to use the Rectangle objects
             instead to create the shapes. We thought we could create the shapes and store them
             in an array. However, we have encountered that this requires us to create multiple
             rectangle objects which will slow down the program. We are instead planning on using
             methods that set the X and Y coordinates to make the shapes.

    ----------------------------------------------

## FRI 2019-11-22 @ 6:30 PM EST

1. **DONE:** Made buttons with graphics for the welcome page that leads their respective games.
             Learned how to move a group of rectangle objects at the same time.
             Drew a diagram for the layout of Mancala and Tetris.
             Started to implement Mancala.

2. **TODO:** Make all blocks for Tetris.
             Finish visual components of Mancala.

3. **PROB:** We had some trouble with git merging/pushing conflicts, but we managed to learn how to properly update changes.
             We had problems working with group objects so we switched to using Pane Objects.
             We couldn't see the visual components of Mancala, then realised that we dididn't need to put code in the start method since we are returning a VBox.

    -------------------------------------------------

## SAT 2019-11-23 @ 3:30 PM EST

1. **DONE:** Added Pictures to the board and background for Mancala.
             Started to make methods for all block shapes in Tetris.
             Fixed elements in visual for Mancala.
             Researched more on point class in javafx.

2. **TODO:** Add holes on mancala board.
             Make an array to track points of shapes in Tetris.

3. **PROB:** Reconsidered which game to implement.

    -----------------------------------------------

## SUN 2019-11-24 @ 4:30 PM EST

1. **DONE:** Researched how to stack nodes on top of each other.
             Researched Mesh Objects for Tetris board.

2. **TODO:** Implement what we researched to our respective games next time.

3. **PROB:** We didn't know how to put an object on an object, but thats why we researched.

    -----------------------------------------------

## MON 2019-11-25 @ 5:00 PM EST

1. **DONE:** Added all holes to Mancala Board.
             Made methods for 3 shapes for Tetris.
             Made a new class for Mancala Holes.

2. **TODO:** Fill holes with marbles on click of start button.
             Finish methods for shapes in Tetris.
             Implement methods on keyboard key click.

3. **PROB:** We didn't know if we could use integers instead of visual marbles so we asked on Piazza and we are currently waiting for an answer.

    ----------------------------------------------

## WED 2019-12-4 @ 6:00 PM EST

1. **DONE:** Made holes respond when clicked in Mancala.
             Filled holes with 4 marbles when start button is clicked in Mancala.
             Made Player number change when start button is clicked in Mancala.
             Changed the Tetris layout to use a GridPane.
             Added setonkeypressed event for Tetris.
             Integrated the Tetris class with the ArcadeApp class.

2. **TODO:** Make algorithm for Mancala work for the first move.
             Disable other holes when it is one player's turn in mancala.
             Make different classes for each block shape for Tetris
             Each block shape class must have a rotate method for Tetris
             Make an array that tracks the position of the blocks behind the scenes for Tetris.


3. **PROB:** The holes do not respond when you click on the marbles, which are on top of the holes. I think I should make the marbles transparent to mouse clicks.
             Using gridpane requires us to make many rectangle objects which might slow down the game. However, this makes it easier for me to organize the blocks.

    --------------------------------------------

## MON 2019-12-9 @ 6:00 PM EST

1. **DONE:** Finished successfully running one game of Mancala.
             Implemented "capturing" feature
             Tested one game for bugs and fixed them.
             Finished the rotation method for Tetris.
             All of the 7 blocks rotate.

2. **TODO:** Make a pop up window where it tells the scores.
             Make instrucion window
             Make marbles transparent to mouse clicks
             Reset the game when prompted.
             Consider implementing high score table.
             Make the blocks fall for tetris and make the method that checks if a line is clear.

3. **PROB:** Visually removing marbles was difficult because I needed to identify the exact marble in the backend array.
             Finding it difficult to access nodes from Pane object. I'm looking on stackoverflow for accessing children of Pane object.

    ---------------------------------------------

## TUES 2019-12-10 @ 1:00 PM EST

1. **DONE:** Made marbles transparent to mouse clicks
             Debugged algorithm for ending the game
             Made an instructions window when game starts
             Made a game over window
             Reset game when replay button is clicked
             Made the method that clears out the filled lines.
             Game resets when the back/return button is clicked.
             Scores and levels are updated for tetris.

2. **TODO:** Add attributions
             Condense the rotate method for Tetris.

3. **PROB:** I had trouble sizing the game over window but after setting minWidth and minHeight it worked.
             I was able to access Pane objects using "instanceof" method but I found it difficult to convert node to rectangle.

    -------------------------------------------