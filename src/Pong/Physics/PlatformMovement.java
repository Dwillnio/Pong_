package Pong.Physics;

//Checks whether vertical movement of a platform is valid i.e. in bounds

import Pong.Objects.Platform;

public class PlatformMovement {

    private final int gameHeight;

    public PlatformMovement(int gameHeight) {
        this.gameHeight = gameHeight;
    }

    //Returns false if move is invalid/ out of bounds
    public boolean checkMove(Platform platform, float dy) {
        if(platform.getY() + dy < 0 || platform.getY() + dy + platform.getHeight() > gameHeight) {
            return false;
        }
        return true;
    }

}
