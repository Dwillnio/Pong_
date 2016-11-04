package Pong.Physics;

//Handles ball collision reported by collision for the ball

import Pong.Objects.Ball;

public class CollisionHandler {

    private Ball ball;

    public CollisionHandler(Ball ball) {
        this.ball = ball;
    }

    //Handles the collision specified: mirrors the balls velocity vector if a platform or wall is hit;
    //                                          returns -1 if the left, 1 if the right wall is hit,
    //                                          0 in all other cases
    public int handleCollision(Collision c, double frameTime) {
        if(c.shouldMirrorX()) {
            ball.velocity().mirrorOnYAxis(); //dont ask
            if(ball.velocity().length() + 0.2 < Ball.MAX_SPEED) {
                ball.velocity().increase(0.2f);
            }
        }
        if(c.shouldMirrorY()) {
            ball.velocity().mirrorOnXAxis(); //dont ask either
            if(ball.velocity().length() + 0.2 < Ball.MAX_SPEED) {
                ball.velocity().increase(0.2f);
            }
        }
        return c.getGoalStatus();
    }

}
