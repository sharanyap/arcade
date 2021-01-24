package cs1302.arcade;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import java.util.ArrayList;

/** Represents an instance of a Hole.
 */
public class Hole extends Rectangle {

    int num;
    Image holeImage = new Image("file:resources/holewood.jpg");
    ImagePattern hole = new ImagePattern(holeImage);
    int x;
    int y;
    int width;
    int length;
    int numMarbles;
    ArrayList<Marble> marbles;

    /** Constructs a Hole Object.
     * @param inX the x location of hole
     * @param inY the y location of hole
     * @param inWidth the width of hole
     * @param inLength the length of hole
     * @param inNum the number assigned to hole
     * @param inNumMarbles the number of marbles in hole
     */
    public Hole(int inX, int inY, int inWidth, int inLength, int inNum, int inNumMarbles) {
        super(inX, inY, inWidth, inLength);
        num = inNum;
        x = inX;
        y = inY;
        width = inWidth;
        length = inLength;
        marbles = new ArrayList<Marble>(inNumMarbles);
        this.setArcWidth(80);
        this.setArcHeight(80);
        this.setStroke(Color.BLACK);
        this.setFill(hole);
    } // Hole

    /** Returns the number assigned to hole.
     * @return number of hole
     */
    public int getNum() {
        return num;
    } // getNum

    /** Returns the number of marbles in hole.
     * @return the number of marbles in hole
     */
    public int getNumMarbles() {
        return marbles.size();
    } // getNumMarbles

    /** Returns arraylist of marbles in hole.
     * @return the arraylist of marbles
     */
    public ArrayList<Marble> getMarbles() {
        return marbles;
    } //getMarbles

    /** Adds marbles to the hole.
     * @param inNum the number of marbles to be added
     */
    public void addMarbles(int inNum) {
        for (int i = 0; i < inNum; i++) {
            //set min a+nd max for x and y coordinates for marble placement
            int Xmin = (int)(getX() + 15);
            int Xmax = (int)(getX() + getWidth() - 15);
            int Ymin = (int)(getY() + 15);
            int Ymax = (int)(getY() + getHeight() - 15);
            //random values to put the marble
            int xVal = (int)(Math.random() * ((Xmax - Xmin) + 1) + Xmin);
            int yVal = (int)(Math.random() * ((Ymax - Ymin) + 1) + Ymin);
            Marble marble = new Marble(xVal, yVal);
            marbles.add(marble);
        } // for
    } // addMarbles

    /** Adds one marble to the hole.
     * @return the marble that was added
     */
    public Marble addMarble() {
        //set min a+nd max for x and y coordinates for marble placement
        int Xmin = (int)(getX() + 15);
        int Xmax = (int)(getX() + getWidth() - 15);
        int Ymin = (int)(getY() + 15);
        int Ymax = (int)(getY() + getHeight() - 15);
        //random values to put the marble
        int xVal = (int)(Math.random() * ((Xmax - Xmin) + 1) + Xmin);
        int yVal = (int)(Math.random() * ((Ymax - Ymin) + 1) + Ymin);
        Marble marble = new Marble(xVal, yVal);
        marbles.add(marble);
        return marble;
    } // addMarble

    /** Removes last marble from the hole.
     */
    public void removeMarble() {
        marbles.remove(marbles.size() - 1);
    } // clearMarbles

    /** Removes all marbles from the hole.
     */
    public void removeAll() {
        marbles.clear();
    } // removeAll;

} // Hole
