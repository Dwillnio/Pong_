package Pong.Userinterface;

/* Class that keeps track of the score and reports if a side wins, is drawable and used
 *  to draw score on Drawingboard*/

import Pong.Objects.Drawable;
import Pong.Pong;

import java.awt.*;

public class Score implements Drawable{

    private int scoreLeft;
    private int scoreRight;

    private final int maxScore;

    private final Pong pong;

    public Score(int maxScore, Pong pong) {
        this.maxScore = maxScore;
        scoreLeft = 0;
        scoreRight = 0;
        this.pong = pong;
    }

    public int getScoreLeft() {
        return scoreLeft;
    }

    public int getScoreRight() {
        return scoreRight;
    }
    
    public void reset() {
        scoreLeft = 0;
        scoreRight = 0;
    }

    // Method used in combination with the CollisionHandler
    // Changes score and returns false if max score is reached
    public boolean handleScoreChange(int scoreChange) {
        if(scoreChange == 1) {
            scoreLeft++;
        } else if(scoreChange == -1) {
            scoreRight++;
        } else {
            return true;
        }
        pong.reset();
        return !checkIfMaxScore();
    }

    private boolean checkIfMaxScore(){
        if(scoreLeft >= maxScore || scoreRight >= maxScore) {
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font( "Verdana", Font.BOLD, 40));
        graphics.drawString(getScoreLeft() +  " - "  + getScoreRight(), Userinterface.WIDTH/2 - 51, 40);
    }

}
