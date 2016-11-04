package Pong.Objects;

/* Ball class represents the ball in the game, is movable and can be drawn*/

import Pong.Updateable;
import java.awt.*;

public class Ball extends GameObject implements Movable, Drawable, Updateable {

    private Vector2D velocity;     // Contain direction and speed(as length) the ball is traveling

    public final static int SIZE = 25;                          //ball's diamater
    public final static float MAX_SPEED = 5;
    private final static Color COLOR = Color.red;          //ball color

    public Ball(Coor2D position) {
        this(position, new Vector2D(0, 0));
    }

    public Ball(Coor2D position, Vector2D velocity) {
        super(position);
        this.velocity = velocity;
    }

    public Vector2D velocity() {
        return velocity;
    }
    
    @Override
    public void update() {
        update(Pong.Pong.STANDARD_FRAMETIME);
    }
    
    @Override
    public void update(double frameTime) {
        float frameFactor = (float)(frameTime / Pong.Pong.STANDARD_FRAMETIME);
        move(velocity.getX() * frameFactor, velocity.getY() * frameFactor);
    }

    //Changes ball's coordinates by dx and dy
    @Override
    public void move(float dx, float dy) {
        position().move(dx, dy);
    }

    @Override
    public void setPosition(Coor2D newPosition) {
        position().set(newPosition);
    }

    /*function for drawing the ball: first sets color then draws ball as circle using its position
       and the const Size*/
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(COLOR);
        graphics.fillOval((int) position().getX() - SIZE/2, (int) position().getY() - SIZE/2, SIZE, SIZE);
    }

}
