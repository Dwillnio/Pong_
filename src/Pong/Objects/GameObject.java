package Pong.Objects;

/* Represents a object with a position in game, provides functions to be inherited, that
*  make retrieving the obejects position possible*/

public abstract class GameObject {

    private Coor2D position;

    public GameObject(Coor2D position) {
        this.position = position;
    }

    public Coor2D position() {
        return position;
    }

    public float getX() {
        return position.getX();
    }

    public float getY() {
        return position.getY();
    }

}
