package cs1302.arcade;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.util.Arrays;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import java.awt.BasicStroke;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.Node;

/**
 * Application subclass for {@code ArcadeApp}.
 * @version 2019.fa
 */
public class ArcadeApp extends Application {

    // Variables

    // variables for switching between scenes of 2 games
    Button tetrisButton;       // button to play tetris
    Button mancalaButton;     // button to play mancala
    Button back = new Button("Back");          // button to go back to home screen for mancala scene
    Button backt = new Button("Back/Restart");        //home screen button for tetris scene
    ToolBar menuBar = new ToolBar(back);    // menubar for mancala

    Tetris tetris = new Tetris(); // starts the tetris game
    Mancala mancala = new Mancala(); // starts the mancala game

    // creating 2 option buttons for the 2 games
    HBox mainBox = new HBox(); // main HBox that holds both the game scenes
    VBox mancalaOption;
    VBox tetrisOption;
    Image mancalaimg = new Image("file:resources/mancala.jpg");
    Image tetrisimg = new Image("file:resources/tetris.png");
    ImageView mancalaImage = new ImageView(mancalaimg);
    ImageView tetrisImage = new ImageView(tetrisimg);

    VBox tetrisBox = new VBox();     // holds Tetris game
    VBox mancalaBox = new VBox();    //get VBox from class files
    Scene tetrisScene;  // sets the scene for Tetris game
    Scene mancalaScene; // sets the scene for Mancala game
    Scene welcomeScene; // has the options to choose which game user wants to play

    // Variables for TETRIS game

    Pane current = new Pane(); // pane that stores the tetris blocks
    String[][] array = new String[500][500]; // info of which spots have been filled
    int dir = tetris.dir; // orientation of the block
    int count = 0; // tracks if the gameblock reached the top

    Line line; // line to separate game from score board
    Text score;
    Text level;
    Text instruct;
    int scorenum = 0;
    int levelnum = 1;
    int speed = 1000; // how fast the block falls
    Timeline timeline; // animation for the falling of the blocks

    /** {@inheritDoc} */

    @Override
    public void start(Stage stage) {

       //making tetris option
        tetrisButton = new Button("", tetrisImage);
        tetrisOption = new VBox(tetrisButton);
        tetrisOption.setAlignment(Pos.CENTER);

        //making mancala option
        mancalaButton = new Button("", mancalaImage);
        mancalaOption = new VBox(mancalaButton);
        mancalaOption.setAlignment(Pos.CENTER);

        setUpBoard(); // sets up the scoreboard

        //actions for back buttons on game scenes
        back.setOnAction(e -> stage.setScene(welcomeScene));
        backt.setOnAction(e -> {
            timeline.pause();
            count = 0;
            scorenum = 0;
            levelnum = 1;
            score.setText("Score: " + scorenum);
            level.setText("Level: " + levelnum);
            current.getChildren().clear();
            stage.setScene(welcomeScene);
        });

        //setting up main VBox for game scenes
        tetrisBox.getChildren().addAll( current);
        mancalaBox.getChildren().addAll(menuBar, mancala.getVBox());

        //making scenes for welcome screen and each game
        mainBox.getChildren().addAll(tetrisOption, mancalaOption);
        mainBox.setHgrow(tetrisOption, Priority.ALWAYS);
        mainBox.setHgrow(mancalaOption, Priority.ALWAYS);
        tetrisScene = new Scene(tetrisBox, 800, 500);
        mancalaScene = new Scene(mancalaBox, 800, 500);
        welcomeScene = new Scene(mainBox, 800, 500);

        //actions for game buttons
        tetrisButton.setOnAction(e -> {
            stage.setScene(tetrisScene);
            restart();
        });
        mancalaButton.setOnAction(e -> stage.setScene(mancalaScene));

        //finish setting up stage
        stage.setTitle("cs1302-arcade!");
        stage.setScene(welcomeScene);
        stage.setMinHeight(500);
        stage.setMinWidth(800);
        stage.setMaxHeight(500);
        stage.setMaxWidth(800);
        stage.show();
        current.requestFocus();
    } // start

    /**
     * Restarts the game when the "back/restart" button is pressed.
     */
    public void restart() {
        //accessing tetris blocks from the Tetris class
        tetris = new Tetris();
        current.getChildren().addAll(line, instruct, score, backt, level);
        current.getChildren().addAll(Tetris.a, Tetris.b, Tetris.c, Tetris.d);
        current.setOnKeyPressed(createKeyHandler());
        for (int i = 0; i < 500; i += 25) {
            for (int j = 0; j < 500; j += 25) {
                array[i][j] = "-";
            }
        }

        timeline = new Timeline(new KeyFrame(Duration.millis(speed),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent t) {
                        if (count > 1) {
                            stop();
                        } else {
                            moveDown();
                        }
                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Method to set up the scoreboard in the game.
     */
    public void setUpBoard() {
        // setting up instructions and scoreboard for tetris
        line = new Line(500, 0, 501, 500);
        String str = "INSTRUCTIONS: \nEach line is worth 100 points";
        str += "\nAfter every 400 points the level increases \nand blocks fall faster";
        str += "\nPress UP key to rotate the block!";
        instruct = new Text(str);
        instruct.setX(502);
        instruct.setY(90);
        score = new Text("Score: " + scorenum);
        level = new Text("Level: " + levelnum);
        score.setY(250);
        score.setX(500 + 50);
        level.setX(500 + 50);
        level.setY(275);
        backt.setLayoutX(500 + 50);
        backt.setLayoutY(200);

    }

    /**
     * Displays the Game Over message.
     */
    public void stop() {
        timeline.pause();
        Text gameover = new Text("Game Over");
        gameover.setFill(Color.RED);
        gameover.setStroke(Color.BLACK);
        gameover.setStyle(" -fx-font: 50px Courier;");
        gameover.setY(250);
        gameover.setX(150);
        current.getChildren().addAll(gameover);
        tetrisBox.requestFocus();
    }

    /**
     * Moves the block UP, DOWN, LEFT and RIGHT.
     * @return the key event handler
     */
    private EventHandler<? super KeyEvent> createKeyHandler() {
        return event -> {
            switch (event.getCode()) {
            case LEFT:  // KeyCode.LEFT
                moveLeft();
                break;
            case RIGHT: // KeyCode.RIGHT
                moveRight();
                break;
            case UP:
                rotate(Tetris.block);
                break;
            case DOWN:
                if (count < 2) {
                    moveDown();
                }
                break;
            default:
                // do nothing
            } // switch
            // TODO bounds checking
        };
    } // createKeyHandler

    /**
     * Checks what lines are full and deletes those lines and adjusts the game.
     */
    public void clear() {
        ArrayList<Integer> rows = new ArrayList<Integer>(); // stores all the rows that are filled
        ArrayList<Node> rectangles = new ArrayList<Node>();
        ArrayList<Node> nextrect = new ArrayList<Node>();
        int f = 0;
        for (int i = 0; i < 500; i += 25) {
            for (int j = 0; j < 500; j += 25) {
                if (array[i][j].equals("x")) {
                    f++;
                }
            }
            if (f == 20) {
                rows.add(i);
                scorenum += 100;
                if (scorenum != 0 && (scorenum / 100) % 4 == 0) {
                    levelnum += 1;
                    speed -= 100;
                }
            }
            f = 0;
        }
        score.setText("Score: " + scorenum);
        level.setText("Level: " + levelnum);

        if (rows.size() > 0) {
            while (rows.size() > 0) {
                // accessing all the rectangles in the "current" pane
                for (Node node: current.getChildren()) {
                    if (node instanceof Rectangle) {
                        rectangles.add(node);
                    }
                }
                // accessing the rectangles that need to be cleared
                for (Node n : rectangles) {
                    Rectangle x = (Rectangle) n;
                    if (x.getY() == rows.get(0)) {
                        array[(int)x.getY()][(int)x.getX()] = "-";
                        current.getChildren().remove(x);
                    } else {
                        nextrect.add(x);
                    }
                }
                // all the blocks that are on top will be moved down
                // storage of marks will be adjusted accordingly
                for (Node m : nextrect) {
                    Rectangle x = (Rectangle)m;
                    if (x.getY() < rows.get(0)) {
                        array[(int)x.getY()][(int)x.getX()] = "-";
                        x.setY(x.getY() + 25);
                        array[(int) x.getY()][(int) x.getX()] = "x";
                    }
                }
                rows.remove(0);
                rectangles.clear();
                nextrect.clear();
            }
        }
    }

    /**
     * Moves the block left in bounds.
     */
    public void moveLeft() {
        if (Tetris.a.getX() - 25 >= 0
            && !array[(int)Tetris.a.getY()][(int)Tetris.a.getX() - 25].equals("x")
            && Tetris.b.getX() - 25 >= 0
            && !array[(int)Tetris.b.getY()][(int)Tetris.b.getX() - 25].equals("x")
            && Tetris.c.getX() - 25 >= 0
            && !array[(int)Tetris.c.getY()][(int)Tetris.c.getX() - 25].equals("x")
            && Tetris.d.getX() - 25 >= 0
            && !array[(int)Tetris.d.getY()][(int)Tetris.d.getX() - 25].equals("x")) {
            Tetris.a.setX(Tetris.a.getX() - 25);
            Tetris.b.setX(Tetris.b.getX() - 25);
            Tetris.c.setX(Tetris.c.getX() - 25);
            Tetris.d.setX(Tetris.d.getX() - 25);
        }
    }

    /**
     * Moves the block right in bounds.
     */
    public void moveRight() {
        if (Tetris.a.getX() + 25 < 500
            && !array[(int)Tetris.a.getY()][(int)Tetris.a.getX() + 25].equals("x")
            && Tetris.b.getX() + 25 < 500
            && !array[(int)Tetris.b.getY()][(int)Tetris.b.getX() + 25].equals("x")
            && Tetris.c.getX() + 25 < 500
            && !array[(int)Tetris.c.getY()][(int)Tetris.c.getX() + 25].equals("x")
            && Tetris.d.getX() + 25 < 500
            && !array[(int)Tetris.d.getY()][(int)Tetris.d.getX() + 25].equals("x")) {
            Tetris.a.setX(Tetris.a.getX() + 25);
            Tetris.b.setX(Tetris.b.getX() + 25);
            Tetris.c.setX(Tetris.c.getX() + 25);
            Tetris.d.setX(Tetris.d.getX() + 25);
        }
    }

    /**
     * Moves the block down in bounds.
     */
    public void moveDown() {
        if (Tetris.a.getY() + 25 < 500
            && !array[(int)Tetris.a.getY() + 25][(int)Tetris.a.getX()].equals("x")
            && Tetris.b.getY() + 25 < 500
            && !array[(int)Tetris.b.getY() + 25][(int)Tetris.b.getX()].equals("x")
            && Tetris.c.getY() + 25 < 500
            && !array[(int)Tetris.c.getY() + 25][(int)Tetris.c.getX()].equals("x")
            && Tetris.d.getY() + 25 < 500
            && !array[(int)Tetris.d.getY() + 25][(int)Tetris.d.getX()].equals("x")) {
            Tetris.a.setY(Tetris.a.getY() + 25);
            Tetris.b.setY(Tetris.b.getY() + 25);
            Tetris.c.setY(Tetris.c.getY() + 25);
            Tetris.d.setY(Tetris.d.getY() + 25);
        }
        if (collide()) { // if the block collides
            int ax = (int)Tetris.a.getX();
            int ay = (int) Tetris.a.getY();
            int bx =  (int)Tetris.b.getX();
            int by =  (int)Tetris.b.getY();
            int cx =  (int)Tetris.c.getX();
            int cy = (int) Tetris.c.getY();
            int dx = (int) Tetris.d.getX();
            int dy =  (int)Tetris.d.getY();
            if (Tetris.a.getY() == 25 ||
                Tetris.b.getY() == 25 ||
                Tetris.c.getY() == 25 ||
                Tetris.d.getY() == 25) {
                count++;
            }     // marks the position where the block stops as filled
            array[ay][ax] = "x";
            array[by][bx] = "x";
            array[cy][cx] = "x";
            array[dy][dx] = "x";

            clear(); // checks and clears any full lines
            Tetris next = new Tetris(); // makes a new random block
            int nax = (int)next.a.getX();
            int nay = (int) next.a.getY();
            int nbx =  (int)next.b.getX();
            int nby =  (int)next.b.getY();
            int ncx =  (int)next.c.getX();
            int ncy = (int) next.c.getY();
            int ndx = (int) next.d.getX();
            int ndy =  (int)next.d.getY();

            if (array[nay][nax].equals("x") ||
                array[nby][nbx].equals("x") ||
                array[ncy][ncx].equals("x") ||
                array[ndy][ndx].equals("x")) {
                current.getChildren().addAll(next.a, next.b, next.c, next.d);
                count++; // checks if the block is at the top for game over
            } else {
                current.getChildren().addAll(next.a, next.b, next.c, next.d);
                current.requestFocus();
            }

        }
    }

    /**
     * Checks if a block collided with another block.
     * @return true if the block touches pre-existing block.
     */
    public boolean collide() {
        boolean result = false;

        int ax = (int)Tetris.a.getX();
        int ay = (int) Tetris.a.getY();
        int bx =  (int)Tetris.b.getX();
        int by =  (int)Tetris.b.getY();
        int cx =  (int)Tetris.c.getX();
        int cy = (int) Tetris.c.getY();
        int dx = (int) Tetris.d.getX();
        int dy =  (int)Tetris.d.getY();


        if (Tetris.a.getY() + 25 == 500 || Tetris.b.getY() + 25 == 500 ||
            Tetris.c.getY() + 25 == 500 || Tetris.d.getY() + 25 == 500) {
            result = true;
        } else if (array[ay + 25][ax].equals("x") ||
                   array[by + 25][bx].equals("x") ||
                   array[cy + 25][cx].equals("x") ||
                   array[dy + 25][dx].equals("x")) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Rotates a specific block 90 degrees.
     * @param s name of the block
     */
    public void rotate(String s) {
        switch (s) {
        case "i":
            if (dir == 0) {
                iDir0();
            } else {
                iDir1();
            }
            break;
        case "s":
            if (dir == 0) {
                sDir0();
            } else {
                sDir1();
            }
            break;
        case "z":
            if (dir == 0) {
                zDir0();
            } else {
                zDir1();
            }
            break;
        case "t":
            if (dir == 0) {
                tDir0();
            } else if (dir == 1) {
                tDir1();
            } else if (dir == 2) {
                tDir2();
            } else {
                tDir3();
            }
            break;
        case "j":
            if (dir == 0) {
                jDir0();
            } else if (dir == 1) {
                jDir1();
            } else if (dir == 2) {
                jDir2();
            } else {
                jDir3();
            }
            break;
        case "l":
            if (dir == 0) {
                lDir0();
            } else if (dir == 1) {
                lDir1();
            } else if (dir == 2) {
                lDir2();
            } else {
                lDir3();
            }
            break;
        }
    }

    /**
     * Rotates the i block to the 0 orientation.
     */
    public void iDir0() {
        int ox = (int)Tetris.b.getX();
        int oy = (int)Tetris.b.getY();
        if (oy - 25 > 0 && oy + 25 < 500 && oy + 50 < 500) {
            Tetris.a.setX(Tetris.b.getX());
            Tetris.c.setX(Tetris.b.getX());
            Tetris.d.setX(Tetris.b.getX());
            Tetris.a.setY(Tetris.b.getY() - 25);
            Tetris.c.setY(Tetris.b.getY() + 25);
            Tetris.d.setY(Tetris.b.getY() + 50);
        }
        dir = 1;
    }

    /**
     * Rotates the i block to the 1 orientation.
     */
    public void iDir1() {
        int ox = (int)Tetris.b.getX();
        int oy = (int)Tetris.b.getY();
        if (ox - 25 > 0 && ox + 25 < 500 && ox + 50 < 500) {
            Tetris.a.setX(ox - 25);
            Tetris.c.setX(ox + 25);
            Tetris.d.setX(ox + 50);
            Tetris.a.setY(oy);
            Tetris.c.setY(oy);
            Tetris.d.setY(oy);
        }
        dir = 0;
    }

    /**
     * Rotates the S block to the 0 orientation.
     */
    public void sDir0() {
        int ox = (int)Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox + 25 < 500 && oy - 25 > 0) {
            Tetris.a.setX(Tetris.c.getX() + 25);
            Tetris.b.setX(Tetris.c.getX() + 25);
            Tetris.d.setX(Tetris.c.getX());
            Tetris.a.setY(Tetris.c.getY() + 25);
            Tetris.b.setY(Tetris.c.getY());
            Tetris.d.setY(Tetris.c.getY() - 25);
        }
        dir = 1;
    }

    /**
     * Rotates the S block to the 1 orientation.
     */
    public void sDir1() {
        int ox = (int)Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox + 25 < 500 && ox - 25 > 0 && oy - 25 > 0) {
            Tetris.a.setX(Tetris.c.getX() + 25);
            Tetris.b.setX(Tetris.c.getX());
            Tetris.d.setX(Tetris.c.getX() - 25);
            Tetris.a.setY(Tetris.c.getY() - 25);
            Tetris.b.setY(Tetris.c.getY() - 25);
            Tetris.d.setY(Tetris.c.getY());
        }
        dir = 0;
    }

    /**
     * Rotates the Z block to the 0 orientation.
     */
    public void zDir0() {
        int ox = (int) Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox + 25 < 500 && oy - 25 >= 0 && oy + 25 < 500) {
            Tetris.a.setX(Tetris.c.getX() + 25);
            Tetris.b.setX(Tetris.c.getX() + 25);
            Tetris.d.setX(Tetris.c.getX());
            Tetris.a.setY(Tetris.c.getY() - 25);
            Tetris.b.setY(Tetris.c.getY());
            Tetris.d.setY(Tetris.c.getY() + 25);
        }
        dir = 1;
    }

    /**
     * Rotates the Z block to the 1 orientation.
     */
    public void zDir1() {
        int ox = (int) Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox + 25 < 500 && ox - 25 >= 0 && oy - 25 >= 0) {
            Tetris.a.setX(Tetris.c.getX() - 25);
            Tetris.b.setX(Tetris.c.getX());
            Tetris.d.setX(Tetris.c.getX() + 25);
            Tetris.a.setY(Tetris.c.getY() - 25);
            Tetris.b.setY(Tetris.c.getY() - 25);
            Tetris.d.setY(Tetris.c.getY());
        }
        dir = 0;
    }

    /**
     * Rotates the T block to the 0 orientation.
     */
    public void tDir0() {
        int ox = (int) Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox + 25 < 500 && oy - 25 >= 0 && oy + 25 < 500) {
            Tetris.a.setX(Tetris.c.getX());
            Tetris.b.setX(Tetris.c.getX() + 25);
            Tetris.d.setX(Tetris.c.getX());
            Tetris.a.setY(Tetris.c.getY() - 25);
            Tetris.b.setY(Tetris.c.getY());
            Tetris.d.setY(Tetris.c.getY() + 25);
        }
        dir = 1;
    }

    /**
     * Rotates the T block to the 1 orientation.
     */
    public void tDir1() {
        int ox = (int) Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox + 25 < 500 && ox - 25 >= 0 &&  oy + 25 < 500) {
            Tetris.a.setX(Tetris.c.getX() + 25);
            Tetris.b.setX(Tetris.c.getX());
            Tetris.d.setX(Tetris.c.getX() - 25);
            Tetris.a.setY(Tetris.c.getY());
            Tetris.b.setY(Tetris.c.getY() + 25);
            Tetris.d.setY(Tetris.c.getY());
        }
        dir = 2;
    }

    /**
     * Rotates the T block to the 2 orientation.
     */
    public void tDir2() {
        int ox = (int) Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox - 25 >= 0 &&  oy + 25 < 500 && oy - 25 >= 0) {
            Tetris.a.setX(Tetris.c.getX());
            Tetris.b.setX(Tetris.c.getX() - 25);
            Tetris.d.setX(Tetris.c.getX());
            Tetris.a.setY(Tetris.c.getY() + 25);
            Tetris.b.setY(Tetris.c.getY());
            Tetris.d.setY(Tetris.c.getY() - 25);
        }
        dir = 3;
    }

    /**
     * Rotates the T block to the 3 orientation.
     */
    public void tDir3() {
        int ox = (int) Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox + 25 < 500 && ox - 25 >= 0 &&  oy - 25 >= 0) {
            Tetris.a.setX(Tetris.c.getX() - 25);
            Tetris.b.setX(Tetris.c.getX());
            Tetris.d.setX(Tetris.c.getX() + 25);
            Tetris.a.setY(Tetris.c.getY());
            Tetris.b.setY(Tetris.c.getY() - 25);
            Tetris.d.setY(Tetris.c.getY());
        }
        dir = 0;
    }

    /**
     * Rotates the J block to the 0 orientation.
     */
    public void jDir0() {
        int ox = (int) Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox + 25 < 500 && oy - 25 >= 0 && oy + 25 < 500) {
            Tetris.a.setX(Tetris.c.getX() + 25);
            Tetris.b.setX(Tetris.c.getX());
            Tetris.d.setX(Tetris.c.getX());
            Tetris.a.setY(Tetris.c.getY() - 25);
            Tetris.b.setY(Tetris.c.getY() - 25);
            Tetris.d.setY(Tetris.c.getY() + 25);
        }
        dir = 1;
    }

    /**
     * Rotates the J block to the 1 orientation.
     */
    public void jDir1() {
        int ox = (int) Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox + 25 < 500 && ox - 25 >= 0 && oy + 25 < 500) {
            Tetris.a.setX(Tetris.c.getX() + 25);
            Tetris.b.setX(Tetris.c.getX() + 25);
            Tetris.d.setX(Tetris.c.getX() - 25);
            Tetris.a.setY(Tetris.c.getY() + 25);
            Tetris.b.setY(Tetris.c.getY());
            Tetris.d.setY(Tetris.c.getY());
        }
        dir = 2;
    }

    /**
     * Rotates the J block to the 2 orientation.
     */
    public void jDir2() {
        int ox = (int) Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox - 25 >= 0 && oy + 25 < 500 && oy - 25 >= 0) {
            Tetris.a.setX(Tetris.c.getX() - 25);
            Tetris.b.setX(Tetris.c.getX());
            Tetris.d.setX(Tetris.c.getX());
            Tetris.a.setY(Tetris.c.getY() + 25);
            Tetris.b.setY(Tetris.c.getY() + 25);
            Tetris.d.setY(Tetris.c.getY() - 25);
        }
        dir = 3;
    }

    /**
     * Rotates the J block to the 3 orientation.
     */
    public void jDir3() {
        int ox = (int) Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox - 25 >= 0 && ox + 25 < 500 && oy - 25 >= 0) {
            Tetris.a.setX(Tetris.c.getX() - 25);
            Tetris.b.setX(Tetris.c.getX() - 25);
            Tetris.d.setX(Tetris.c.getX() + 25);
            Tetris.a.setY(Tetris.c.getY() - 25);
            Tetris.b.setY(Tetris.c.getY());
            Tetris.d.setY(Tetris.c.getY());
        }
        dir = 0;
    }

    /**
     * Rotates the L block to the 0 orientation.
     */
    public void lDir0() {
        int ox = (int) Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox + 25 < 500 && oy - 25 >= 0 && oy + 25 < 500) {
            Tetris.a.setX(Tetris.c.getX() + 25);
            Tetris.b.setX(Tetris.c.getX());
            Tetris.d.setX(Tetris.c.getX());
            Tetris.a.setY(Tetris.c.getY() + 25);
            Tetris.b.setY(Tetris.c.getY() + 25);
            Tetris.d.setY(Tetris.c.getY() - 25);
        }
        dir = 1;
    }

    /**
     * Rotates the L block to the 1 orientation.
     */
    public void lDir1() {
        int ox = (int) Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox + 25 < 500 && ox - 25 >= 0 && oy + 25 < 500) {
            Tetris.a.setX(Tetris.c.getX() - 25);
            Tetris.b.setX(Tetris.c.getX() - 25);
            Tetris.d.setX(Tetris.c.getX() + 25);
            Tetris.a.setY(Tetris.c.getY() + 25);
            Tetris.b.setY(Tetris.c.getY());
            Tetris.d.setY(Tetris.c.getY());
        }
        dir = 2;
    }

    /**
     * Rotates the L block to the 2 orientation.
     */
    public void lDir2() {
        int ox = (int) Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox - 25 >= 0 && oy + 25 < 500 && oy - 25 >= 0) {
            Tetris.a.setX(Tetris.c.getX() - 25);
            Tetris.b.setX(Tetris.c.getX());
            Tetris.d.setX(Tetris.c.getX());
            Tetris.a.setY(Tetris.c.getY() - 25);
            Tetris.b.setY(Tetris.c.getY() - 25);
            Tetris.d.setY(Tetris.c.getY() + 25);
        }
        dir = 3;
    }

    /**
     * Rotates the L block to the 3 orientation.
     */
    public void lDir3() {
        int ox = (int) Tetris.c.getX();
        int oy = (int)Tetris.c.getY();
        if (ox - 25 >= 0 && ox + 25 < 500 && oy + 25 < 500) {
            Tetris.a.setX(Tetris.c.getX() + 25);
            Tetris.b.setX(Tetris.c.getX() + 25);
            Tetris.d.setX(Tetris.c.getX() - 25);
            Tetris.a.setY(Tetris.c.getY() - 25);
            Tetris.b.setY(Tetris.c.getY());
            Tetris.d.setY(Tetris.c.getY());
        }
        dir = 0;
    }

} // ArcadeApp
