package Pong.Physics;

//Collisiontypes that are used in the Physics package

public class Collision {
    
    private boolean mirrorX;
    private boolean mirrorY;
    private int goal; //{-1, 0, 1}
    
    public Collision() {
        mirrorX = false;
        mirrorY = false;
        goal = 0;
    }
    
    public void swapX() {
        mirrorX = true;
    }
    
    public void swapY() {
        mirrorY = true;
    }
    
    public boolean shouldMirrorX() {
        return mirrorX;
    }
    
    public boolean shouldMirrorY() {
        return mirrorY;
    }
    
    public void setGoalStatus(int i) {
        goal = i;
    }
    
    public int getGoalStatus() {
        return goal;
    }
    
}
