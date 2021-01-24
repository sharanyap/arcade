package cs1302.arcade;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.event.EventHandler;
import javafx.scene.layout.Priority;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.ToolBar;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import java.awt.BasicStroke;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

/**
 * Represents the Tetris game.
 */
public class Tetris {
    public static Rectangle a;
    public static Rectangle b;
    public static Rectangle c;
    public static Rectangle d;
    public static String block;
    public static int dir;
    int chooseshape;

    /**
     * Creates 4 rectangles that will become the basis for the blocks.
     */
    public Tetris() {
        a = new Rectangle(25, 25);
        b = new Rectangle(25, 25);
        c = new Rectangle(25, 25);
        d = new Rectangle(25, 25);
        block = "";
        dir = 0;
        chooseshape = 0;
        this.randomBlock();
    }

    /**
     * Chooses a random Tetris block to display on the screen.
     */
    public void randomBlock() {
        chooseshape = (int)(Math.floor(Math.random() * 6));
        switch (chooseshape) {
        case 0:
            this.i();
            block = "i";
            break;
        case 1:
            this.o();
            block = "o";
            break;
        case 2:
            this.t();
            block = "t";
            break;
        case 3:
            this.s();
            block = "s";
            break;
        case 4:
            this.z();
            block = "z";
            break;
        case 5:
            this.j();
            block = "j";
            break;
        case 6:
            this.l();
            block = "l";
            break;
        }
    }

    /**
     * Creates the "o" shape block.
     */
    public void o() {
        //square
        a.setX(250);
        a.setY(0);
        b.setX(250 + 25);
        b.setY(0);
        c.setX(250);
        c.setY(25);
        d.setX(250 + 25);
        d.setY(25);

        // color of squares
        a.setFill(Color.YELLOW);
        b.setFill(Color.YELLOW);
        c.setFill(Color.YELLOW);
        d.setFill(Color.YELLOW);
        a.setStroke(Color.BLACK);
        b.setStroke(Color.BLACK);
        c.setStroke(Color.BLACK);
        d.setStroke(Color.BLACK);
    }


    /**
     * Creates the "i" shape block.
     */
    public void i() {
        //square
        a.setX(250);
        b.setX(250 + 25);
        c.setX(250 + 50);
        d.setX(250 + 75);
        a.setY(0);
        b.setY(0);
        c.setY(0);
        d.setY(0);

        //color of squares
        a.setFill(Color.BLUE);
        b.setFill(Color.BLUE);
        c.setFill(Color.BLUE);
        d.setFill(Color.BLUE);
        a.setStroke(Color.BLACK);
        b.setStroke(Color.BLACK);
        c.setStroke(Color.BLACK);
        d.setStroke(Color.BLACK);
    }


    /**
     * Creates the "l" shape block.
     */
    public void l() {
        //square
        a.setX(250 + 25);
        b.setX(250 + 25);
        c.setX(250);
        d.setX(250 - 25);
        a.setY(0);
        b.setY(25);
        c.setY(25);
        d.setY(25);

        //color of squares
        a.setFill(Color.ORANGE);
        b.setFill(Color.ORANGE);
        c.setFill(Color.ORANGE);
        d.setFill(Color.ORANGE);
        a.setStroke(Color.BLACK);
        b.setStroke(Color.BLACK);
        c.setStroke(Color.BLACK);
        d.setStroke(Color.BLACK);
    }


    /**
     * Creates the "j" shape block.
     */
    public void j() {
        //square
        a.setX(250 - 25);
        b.setX(250 - 25);
        c.setX(250);
        d.setX(250 + 25);
        a.setY(0);
        b.setY(25);
        c.setY(25);
        d.setY(25);

        //color of squares
        a.setFill(Color.PINK);
        b.setFill(Color.PINK);
        c.setFill(Color.PINK);
        d.setFill(Color.PINK);
        a.setStroke(Color.BLACK);
        b.setStroke(Color.BLACK);
        c.setStroke(Color.BLACK);
        d.setStroke(Color.BLACK);
    }


    /**
     * Creates the "t" shape block.
     */
    public void t() {
        //square
        a.setX(250 - 25);
        b.setX(250);
        c.setX(250);
        d.setX(250 + 25);
        a.setY(25);
        b.setY(0);
        c.setY(25);
        d.setY(25);

        //color of squares
        a.setFill(Color.PURPLE);
        b.setFill(Color.PURPLE);
        c.setFill(Color.PURPLE);
        d.setFill(Color.PURPLE);
        a.setStroke(Color.BLACK);
        b.setStroke(Color.BLACK);
        c.setStroke(Color.BLACK);
        d.setStroke(Color.BLACK);

    }


    /**
     * Creates the "s" shape block.
     */
    public void s() {
        //square
        a.setX(250 + 25);
        b.setX(250);
        c.setX(250);
        d.setX(250 - 25);
        a.setY(0);
        b.setY(0);
        c.setY(25);
        d.setY(25);

        //color of squares
        a.setFill(Color.GREEN);
        b.setFill(Color.GREEN);
        c.setFill(Color.GREEN);
        d.setFill(Color.GREEN);
        a.setStroke(Color.BLACK);
        b.setStroke(Color.BLACK);
        c.setStroke(Color.BLACK);
        d.setStroke(Color.BLACK);
    }


    /**
     * Creates the "z" shape block.
     */
    public void z() {
        //square
        a.setX(250 - 25);
        b.setX(250);
        c.setX(250);
        d.setX(250 + 25);
        a.setY(0);
        b.setY(0);
        c.setY(25);
        d.setY(25);

        //color of squares
        a.setFill(Color.RED);
        b.setFill(Color.RED);
        c.setFill(Color.RED);
        d.setFill(Color.RED);
        a.setStroke(Color.BLACK);
        b.setStroke(Color.BLACK);
        c.setStroke(Color.BLACK);
        d.setStroke(Color.BLACK);
    }


} // Tetris
