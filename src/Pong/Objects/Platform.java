package Pong.Objects;

/* Platform class represents platforms in game, those are rectangular a gameobject with a hitbox*/

import java.awt.*;

public class Platform extends GameObject implements Drawable {

    private final int height;       //Height, grows downwards from starting point
    private final int width;        //Width, grows in both directions form starting point

    private final static Color COLOR = Color.BLACK;

    // Position functionality comes from parent
    public Platform(Coor2D position) {
        this(position, 100, 20);
    }

    public Platform(Coor2D position, int height, int width) {
        super(position);
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    //Draws the platform by first setting its color then drawing a rectangle
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(COLOR);
        graphics.fillRect((int) getX() - width/2, (int) getY(), width, height);
    }

}
