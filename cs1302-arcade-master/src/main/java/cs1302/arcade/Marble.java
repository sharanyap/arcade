package cs1302.arcade;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/** Represents an instance of a Marble.
 */
public class Marble extends Circle {

    int centerX;
    int centerY;

    /** Constructs a marble object.
     * @param inCenterX the x location of marble
     * @param inCenterY the y location of marble
     */
    public Marble(int inCenterX, int inCenterY) {
        super(inCenterX, inCenterY, 12, Color.RED);
        centerX = inCenterX;
        centerY = inCenterY;
        this.setStroke(Color.BLACK);
        this.setMouseTransparent(true);
    } // Marble

    /** Returns "Marble".
     * @return the string "Marble"
     */
    public String toString() {
        return "Marble";
    } // toString

} // Circle
