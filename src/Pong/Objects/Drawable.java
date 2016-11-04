package Pong.Objects;

/* Interface used to signify that object can be drawn, used for Drawingboard as a
 * List of Drawables that is passed */

import java.awt.Graphics;

public interface Drawable {

    void draw(Graphics graphics);

}
