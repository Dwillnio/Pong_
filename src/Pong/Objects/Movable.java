package Pong.Objects;

/* Interface used to show an object can be moved, not all that necessary right now but
*  can be useful in the future*/

public interface Movable {

    void move(float dx, float dy);
    void setPosition(Coor2D newPosition);

}
