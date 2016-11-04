package Pong.Objects;

/* Class used to represent a point/coordinate, widely used, uses floats*/

public class Coor2D {

    private float x;
    private float y;

    public Coor2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void set(Coor2D otherCoor2D) {
        this.x = otherCoor2D.x;
        this.y = otherCoor2D.y;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void move(float dx, float dy) {
        x += dx;
        y += dy;
    }

}
