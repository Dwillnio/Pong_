package Pong.Physics;

/* Checks for a ball's collision with walls and platforms */
import Pong.Objects.Ball;
import Pong.Objects.Platform;
import Pong.Pong;

import java.util.List;

public class CollisionDetecter {

    private final Ball ball;
    private final List<Platform> platforms;
    private final int gameHeight;
    private final int gameWidth;
    
    private int cooldown;

    public CollisionDetecter(Ball ball, List<Platform> platforms, int gameWidth, int gameHeight) {
        this.ball = ball;
        this.platforms = platforms;
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
        cooldown = 0;
    }

    // Checks if the distance between the ball and a platform's straigt is less than the balls size
    private boolean ballPlatformCollision(Platform platform) {
        return Math.abs(ball.getX() - platform.getX()) < Ball.SIZE / 2 + platform.getWidth() / 2;
    }

    //Checks, in case the ball is close enough to the platform's straight,
    // if it the ball is actually close to the platform and not just it's straight
    //Does this by checking if the ball is in the y-corrdinate range of the platform
    private boolean ballPlatformVerticalAllign(Platform platform) {
        return (ball.getY() - platform.getY()) / platform.getHeight() <= 1
                && (ball.getY() - platform.getY()) / platform.getHeight() >= 0;
    }

    //Checks if the ball collides with the left or right wall. if so returns the wall
    //-1 collides with left goal, 0 no goal collision, 1 collides with right goal
    private int goalCollision() {
        int i = 0;
        if (ball.getX() - Ball.SIZE / 2 <= 0) {
            i--;
        } else if (ball.getX() + Ball.SIZE / 2 >= gameWidth) {
            i++;
        }
        return i;
    }

    //Checks if the ball collides with the top or bottom wall
    private boolean wallCollision() {
        return ball.getY() < Ball.SIZE / 2 || gameHeight - ball.getY() < Ball.SIZE / 2;
    } // /2 bcs ball.size equals diameter and radius is needed

    private boolean platformTopCollision(Platform p) {
        if (((ball.getX() + Ball.SIZE) > (p.getX() - p.getWidth() / 2)) //checks if x coor allign
                && ((ball.getX() - Ball.SIZE) < (p.getX() + p.getWidth() / 2))) {
            if (((Math.abs(p.getY() - ball.getY()) < Ball.SIZE) && (p.getY() - ball.getY() > 0)) // checks if y coor close enough
                    || ((Math.abs(ball.getY() - p.getHeight() - p.getY()) < Ball.SIZE) && (p.getY() + p.getHeight() - ball.getY() < 0))) {
                return true;
            }
        }
        return false;
    }

    //Performs all checks and returns the first one that is happening
    public Collision check() {
        Collision c = new Collision();
        if (wallCollision()) {
            c.swapY();
        }
        c.setGoalStatus(goalCollision());
        if (cooldown > 1) {
            cooldown--;
        } else {
            for (Platform platform : platforms) {
                if (platformTopCollision(platform)) {
                    cooldown = Pong.COLLISION_COOLDOWN;
                    c.swapY();
                }
            }

            for (Platform platform : platforms) {
                if (ballPlatformCollision(platform) && ballPlatformVerticalAllign(platform)) {
                    cooldown = Pong.COLLISION_COOLDOWN;
                    c.swapX();
                }
            }
        }
        return c;
    }

}
