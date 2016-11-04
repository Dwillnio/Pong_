package Pong.Userinterface;

/* A drawable class with no hitbox that represents the middle line in the game */

import  Pong.Objects.Drawable;
import java.awt.*;

public class MiddleLine implements Drawable {

    private final int height;                       //Length of each of the lines rectangles
    private final int width;                        //Width of each of the lines rectangles

    private final static int constant = 17;    //Amount of Rectangles that make up the middle line
                                                         //(includes empty ones)

    public MiddleLine() {
        height = Userinterface.HEIGHT / constant;
        width = height/2;
    }

    //Middle lines is drawn by drawing rectangles in a vertical line, while ignoring every second one
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GRAY);
        for(int x = 0; x < constant; x++) {
            if(x%2 == 0) {
                graphics.fillRect(Userinterface.WIDTH / 2 - width / 2, x * Userinterface.HEIGHT / constant, width, height);
            }
        }
    }

}
