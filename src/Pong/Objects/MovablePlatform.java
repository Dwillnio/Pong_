package Pong.Objects;

/* Movable variant of Platform */

import Pong.Direction;
import Pong.Physics.PlatformMovement;
import Pong.Pong;
import Pong.Updateable;

public class MovablePlatform extends Platform implements Movable, Updateable{

    private Direction movementDirection;        //Flag so platform is moved every frame
    private final Coor2D origPosition;              //Saves original position for resets
    private final PlatformMovement platformMovement;

    public MovablePlatform(PlatformMovement platformMovement, Coor2D position) {
        this(platformMovement, position, 100, 20);
    }

    public MovablePlatform(PlatformMovement platformMovement, Coor2D position, int height, int width) {
        super(position, height, width);
        origPosition = new Coor2D(position.getX(), position.getY());
        movementDirection = Direction.STATIONARY;
        this.platformMovement = platformMovement;
    }

    public Direction getMovementDirection() {
        return movementDirection;
    }

    public void setMovementDirection(Direction movementDirection) {
        this.movementDirection = movementDirection;
    }

    public Coor2D getStartingPosition() {
        return origPosition;
    }

    // Moves the platforms position by accesing its parent
    @Override
    public void update() {
        update(Pong.STANDARD_FRAMETIME);
    }
    
    @Override 
    public void update(double frameTime) {
        float frameFactor = (float)frameTime / Pong.STANDARD_FRAMETIME;
        if(movementDirection == Direction.DOWN && platformMovement.checkMove(this, 3*frameFactor)) {
            move(0, 3*frameFactor);
        }
        if(movementDirection == Direction.UP && platformMovement.checkMove(this, -3*frameFactor)) {
            move(0, -3*frameFactor);
        }
    }
    
    @Override
    public void move(float dx, float dy) {
        position().move(dx, dy);
    }

    @Override
    public void setPosition(Coor2D newPosition) {
        position().set(newPosition);
    }

}
