package Pong.Objects;

/* class used to represent a vector, that shows direction and speed(as length) */

public class Vector2D {

    private float x;
    private float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float length() {
        return (float)Math.sqrt(x*x + y*y);
    }

    public void changeLength(float length) {
        float l = length();
        x = x * (length / l);
        y = y * (length / l);
    }
    
    public void increase(float amount) {
        x += x/length() * amount;
        y += y/length() * amount;
    }

    /* Mirrors vector on axis, very useful for ball phiysics*/

    public void mirrorOnYAxis() {
        x = -x;
    }

    public void mirrorOnXAxis() {
        y = -y;
    }

}
