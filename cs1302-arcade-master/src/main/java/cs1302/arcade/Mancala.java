package cs1302.arcade;

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
import javafx.geometry.Insets;
import javafx.scene.layout.Priority;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.ImagePattern;
import javafx.scene.Group;
import java.util.ArrayList;
import java.util.stream.*;

/**
 * Represents the Mancala game.
 */
public class Mancala {
    VBox mancalaBox;
    HBox titleBox;
    Text title;
    HBox playerBox;
    Text player;
    Group boardGroup;
    Rectangle board;
    HBox startBox;
    Button start;
    Hole[] holes;
    Image backgroundImage = new Image("file:resources/blackwood.jpg");
    Image boardBackgroundImage = new Image("file:resources/woodboard.jpg");
    boolean keepGoing;

    /** Constructor for Mancala Game.
     */
    public Mancala() {
        //MAIN BOX
        mancalaBox = new VBox(12);
        BackgroundImage background = new BackgroundImage(backgroundImage,BackgroundRepeat.REPEAT,
                                                         BackgroundRepeat.REPEAT,
                                                         BackgroundPosition.DEFAULT,
                                                         BackgroundSize.DEFAULT);
        mancalaBox.setBackground(new Background(background));
        title = new Text("Mancala"); // setting up title box
        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        title.setFill(Color.WHITE);
        titleBox = new HBox(10);
        titleBox.getChildren().addAll(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(30));
        player = new Text("Player: "); // setting up player box
        player.setFill(Color.WHITE);
        playerBox = new HBox(player);
        playerBox.setAlignment(Pos.CENTER);
        playerBox.setPadding(new Insets(10));
        board = new Rectangle(); //setting up board group
        board.setWidth(800);
        board.setHeight(260);
        board.setArcWidth(50);
        board.setArcHeight(50);
        board.setStroke(Color.BLACK);
        ImagePattern boardBackground = new ImagePattern(boardBackgroundImage);
        board.setFill(boardBackground);
        boardGroup = new Group(board); // making group to put board in
        holes = new Hole[14];
        for (int i = 0; i < 6; i++) {
            Hole hole = new Hole(135 + (90 * i), 150, 80, 80, i, 4);
            holes[i] = hole;
            boardGroup.getChildren().add(hole);
            hole.setOnMouseReleased(e -> turn(hole));
            hole.setDisable(true);
        } // for setting up Player 1 holes
        Hole player1 = new Hole(695, 30, 80, 200, 6, 0); // player 1 score hole
        holes[6] = player1;
        boardGroup.getChildren().add(player1);
        for (int i = 0; i < 6; i ++) {
            Hole hole = new Hole(585 - (90 * i), 30, 80, 80, i + 7, 4);
            holes[i + 7] = hole;
            boardGroup.getChildren().add(hole);
            hole.setOnMouseReleased(e -> turn(hole));
            hole.setDisable(true);
        } // for Setting up player 2 holes
        Hole player2 = new Hole(25, 30, 80, 200, 13, 0); // player 2 score hole
        holes[13] = player2;
        boardGroup.getChildren().add(player2);
        start = new Button("Start"); // start Box
        start.setOnAction(e -> startGame());
        startBox = new HBox(start);
        startBox.setAlignment(Pos.CENTER);
        startBox.setPadding(new Insets(10));
        mancalaBox.setVgrow(titleBox, Priority.ALWAYS);
        mancalaBox.setVgrow(playerBox, Priority.ALWAYS);
        mancalaBox.setVgrow(board, Priority.ALWAYS);
        mancalaBox.getChildren().addAll(titleBox, playerBox, boardGroup, startBox);
    } // Mancala

    /** Returns VBox for Mancala game.
     * @return vbox for Mancala
     */
    public VBox getVBox() {
        return mancalaBox;
    } // getHBox

    /** Starts the game.
     */
    public void startGame() {
        instructions();
        startLayout();
        disableHoles();
    } // startGame

    /** Pop up window for instructions.
     */
    public void instructions() {
        Stage instructions = new Stage();
        VBox vbox = new VBox(40);
        Text rules = new Text("Mancala Rules:\n\nThis is a single lap game (no relay sowing)" +
                              "\n\nThe game ends when there are no more marbles on a side." +
                              "\n\nThe player with the most marbles wins.");
        vbox.getChildren().add(rules);
        vbox.setAlignment(Pos.CENTER);
        Scene instructScene = new Scene(vbox, 500, 200);
        instructions.setTitle("Rules");
        instructions.setScene(instructScene);
        instructions.setMinWidth(500);
        instructions.setMinHeight(200);
        instructions.show();
    } // instructions

    /** Sets up starting layout of game.
     */
    public void startLayout() {
        //disable start button
        start.setDisable(true);
        player.setText("Player: 1");
        //add marbles to array
        for (int i = 0; i < 14; i++) {
            if ((holes[i].getNum() != 6) && (holes[i].getNum() != 13)) {
                holes[i].addMarbles(4);
            }
        } // for
        for (int i = 0; i < 14; i++) {
            ArrayList<Marble> marbles = holes[i].getMarbles();
            for (Marble m : marbles) {
                boardGroup.getChildren().add(m);
            } // for
        } // for
    } // startLayout

    /** Disables holes according to which player's turn.
     */
    public void disableHoles() {
        if (player.getText().equals("Player: 1")) {
            for (int i = 0; i < 6; i++) {
                Hole h = holes[i];
                h.setDisable(false);
                if (holes[i].getNumMarbles() == 0) {
                    h.setDisable(true);
                } // if for empty holes
            } // for
            for (int i = 7; i < 13; i++) {
                Hole h = holes[i];
                h.setDisable(true);
            } // for
        } else if (player.getText().equals("Player: 2")) {
            for (int i = 0; i < 6; i++) {
                Hole h = holes[i];
                h.setDisable(true);
            } // for
            for (int i = 7; i < 13; i++) {
                Hole h = holes[i];
                h.setDisable(false);
                if (holes[i].getNumMarbles() == 0) {
                    h.setDisable(true);
                } // if for empty holes
            } // for
        } // if
    } // disableHoles

    /** Decides whether game should or not.
     * @return whether a player has all holes empty.
     */
    public boolean endGame() {
        boolean end1 = true;
        boolean end2 = true;
        for (int i = 0; i < 6; i++) {
            if (holes[i].getNumMarbles() != 0) {
                end1 = false;
            } // if
        } // for
        for (int i = 7; i < 13; i++) {
            if (holes[i].getNumMarbles() != 0) {
                end2 = false;;
            } // if
        } // for
        return end1 || end2;
    } // endGame

    /** Executes a turn according to which hole was clicked.
     * @param h the hole that was clicked.
     */
    public void turn(Hole h) {
        int holeNumber = h.getNum();
        int numMarbles = h.getNumMarbles();
        int index = holeNumber + 1;
        int lastHole;
        for (Marble m : h.getMarbles()) {
            boardGroup.getChildren().remove(m);
        } // remove all marbles in clicked hole
        h.removeAll();
        if (player.getText().equals("Player: 1")) {
            for (int i = numMarbles; i > 0; i--) {
                if (index == 13) {
                    index = 0;
                } // makes sure marble does not go in opposing player's hole
                //add marble to array and to board visual
                boardGroup.getChildren().add(holes[index].addMarble());
                index++;
                if (index == 14) {
                    index = 0;
                } // makes sure marble goes into a hole in bounds.
            } // for
            lastHole = index - 1;
            if (lastHole >= 0 && lastHole <= 5
                && (holes[lastHole].getNumMarbles() == 1)
                && (holes[12 - lastHole].getNumMarbles() > 0)) {
                capture(lastHole);
            } // if last marble lands in empty hole on players side, take it and marbles across it.
            if (index != 7) {
                player.setText("Player: 2");
            } // decides whether player 1 can move again
        } else if (player.getText().equals("Player: 2")) {
            for (int i = numMarbles; i > 0; i--) {
                if (index == 6) {
                    index = 7;
                } // makes sure marble doesn't go in opposing player's hole
                //add marble to array and to board visual
                boardGroup.getChildren().add(holes[index].addMarble());
                index++;
                if (index == 14) {
                    index = 0;
                } // makes sure marble hole is in bounds
            } // for
            lastHole = index - 1;
            if (lastHole >= 7 && lastHole <= 12
                && (holes[lastHole].getNumMarbles() == 1)
                && (holes[12 - lastHole].getNumMarbles() > 0)) {
                capture(lastHole);
            } // if last marble lands in empty hole on players side, take it and marbles across it.
            if (index != 0) {
                player.setText("Player: 1");
            } // decides whether player 2 can move again
        } // if
        //disable holes to the proper player
        disableHoles();

        //test whether there are no marbles in the holes
        if (endGame() == true) {
            endText();
        } // if
    } // turn

    /** Captures all marbles in the hole at index as well as across from the index.
     * @param index the index at which to take marbles from.
     */
    public void capture(int index) {
        int numMarbles = 1 + holes[12 - index].getNumMarbles();
        for (Marble m : holes[12 - index].getMarbles()) {
            boardGroup.getChildren().remove(m);
        } // remove marbles in other side

        for (Marble m : holes[index].getMarbles()) {
            boardGroup.getChildren().remove(m);
        } // remove marbles in current side

        //remove marbles in hole arrays
        holes[index].removeAll();
        holes[12 - index].removeAll();

        if (index >= 0 && index <= 5) {
            // player 1 capture: add marbles to array and visual for hole 6
            for (int i = 0; i < numMarbles; i++) {
                boardGroup.getChildren().add(holes[6].addMarble());
            } // for
        } else if (index >= 7 && index <= 12) {
            // player 2 capture: add marbles to array and visual for hole 13
            for (int i = 0; i < numMarbles; i++) {
                boardGroup.getChildren().add(holes[13].addMarble());
            } // for
        } // if

    } // capture

    /** Popup Window for Game Over.
     */
    public void endText() {
        for (int i = 0; i < 14; i++) {
            holes[i].setDisable(true);
        } // disable all holes
        start.setDisable(false);
        int player1 = holes[6].getNumMarbles();
        int player2 = holes[13].getNumMarbles();
        String winner;

        //create popup
        Stage gameOver = new Stage();
        VBox vbox = new VBox(40);
        if (player1 > player2) {
            winner = "Player 1";
        } else if (player1 < player2) {
            winner = "Player 2";
        } else {
            winner = "It's a tie!";
        } // if
        Text scores = new Text("GAME OVER" +
                               "\n\nPlayer 1: " + player1 + " marbles." +
                               "\n\nPlayer 2: " + player2 + " marbles." +
                               "\n\nWinner: " + winner +
                               "\n\n");
        Button reset = new Button("Replay");
        reset.setOnAction(e -> reset());
        vbox.getChildren().addAll(scores, reset);
        vbox.setAlignment(Pos.CENTER);
        Scene endScene = new Scene(vbox, 500, 300);
        gameOver.setTitle("GAME OVER");
        gameOver.setScene(endScene);
        gameOver.setMinWidth(500);
        gameOver.setMinHeight(300);
        gameOver.show();

    } // endText

    /** Removes all marbles from board.
     */
    public void reset() {
        for (int i = 0; i < 14; i++) {
            for (Marble m : holes[i].getMarbles()) {
                boardGroup.getChildren().remove(m);
            } // for
            holes[i].removeAll();
        } // for
    } // reset

} // Mancala
