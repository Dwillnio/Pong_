package Pong;

/* Combines all classes to create the game */
import Pong.Objects.*;
import Pong.Physics.CollisionDetecter;
import Pong.Physics.CollisionHandler;
import Pong.Physics.PlatformMovement;
import Pong.Userinterface.Score;
import Pong.Userinterface.Userinterface;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Pong {

    public final static float FPS_TARGET = 200;
    public final static float FRAMETIME_MILLIS_TARGET = 1000 / FPS_TARGET;       //How long a frame should last,
    public final static float STANDARD_FRAMETIME = 5;                                                                                  // game will sleep so that time is kept

    /* Various objects */
    private Userinterface userinterface;
    private Ball ball;
    private List<Platform> platforms;
    private List<MovablePlatform> movablePlatforms;
    private PlatformMovement platformMovement;
    private CollisionDetecter collisionChecker;
    private CollisionHandler collisionHandler;
    private Score score;

    private GameMode gameMode;

    private boolean continues;
    private double lastFrameTime; //milliSec
    public final static int COLLISION_COOLDOWN = (int) (FPS_TARGET / (1000 / STANDARD_FRAMETIME) * 10);

    //Standard game init
    public Pong() {
        this(GameMode.TWOPLAYER);
    }

    //Game init if gamemode is specified, starts by creating objects that are always needed
    //then calling a specifing constructing function depending on the gamemode
    public Pong(GameMode gameMode) {

        ball = new Ball(new Coor2D(Userinterface.WIDTH / 2, Userinterface.HEIGHT / 2), new Vector2D(2.5f, 1.5f));
        platforms = new ArrayList<>();
        movablePlatforms = new ArrayList<>();
        platformMovement = new PlatformMovement(Userinterface.HEIGHT);
        score = new Score(5, this);
        lastFrameTime = 10;

        collisionChecker = new CollisionDetecter(ball, platforms, Userinterface.WIDTH, Userinterface.HEIGHT);
        collisionHandler = new CollisionHandler(ball);

        if (gameMode == GameMode.SINGLEPLAYER) {
            singlePlayerInit();
        } else if (gameMode == GameMode.TWOPLAYER) {
            twoPlayerInit();
        } else {
            twoPlayerInit();
        }

        userinterface = new Userinterface(this);
    }

    /* Various game initiation constructor functions */
    private void twoPlayerInit() {
        this.gameMode = GameMode.TWOPLAYER;

        platforms.add(new MovablePlatform(platformMovement, new Coor2D(50, Userinterface.HEIGHT / 2 - 50)));
        movablePlatforms.add((MovablePlatform) platforms.get(0));
        platforms.add(new MovablePlatform(platformMovement, new Coor2D(Userinterface.WIDTH - 50, Userinterface.HEIGHT / 2 - 50)));
        movablePlatforms.add((MovablePlatform) platforms.get(1));
    }

    private void singlePlayerInit() {
        this.gameMode = GameMode.SINGLEPLAYER;

        platforms.add(new MovablePlatform(platformMovement, new Coor2D(50, Userinterface.HEIGHT / 2 - 50)));
        movablePlatforms.add((MovablePlatform) platforms.get(0));
        platforms.add(new MovablePlatform(platformMovement, new Coor2D(Userinterface.WIDTH, 0), Userinterface.HEIGHT, 30));
        movablePlatforms.add((MovablePlatform) platforms.get(1));
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public Ball getBall() {
        return ball;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public PlatformMovement getPlatformMovement() {
        return platformMovement;
    }

    public Score getScore() {
        return score;
    }

    // Starts the game by activating the user interface
    public void start() {
        try {
            SwingUtilities.invokeLater(userinterface);
            while (userinterface.getDrawingBoard() == null) {
                Thread.sleep(500);
            }

            Thread.sleep(1000);

            while(true) {
                gameLoop();
                if(JOptionPane.showConfirmDialog(null, "Game ended, restart?", "Pong", JOptionPane.YES_NO_OPTION) != 0) {
                    System.exit(0);
                }
                reset();
                score.reset();
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR");
            ex.printStackTrace();
        }
    }

    /* Starts the loop the game goes through every frame */
    private void gameLoop() throws InterruptedException {
        int scoreChange;
        long deltaTime;
        long deltaFactorTime;

        continues = true;
        while (continues) {
            deltaTime = System.nanoTime();
            deltaFactorTime = System.nanoTime();

            ball.update(lastFrameTime);     //Moves the ball by its velocity
            movePlatforms();                                                         //Moves all the moving platforms depending on their flag

            scoreChange = collisionHandler.handleCollision(collisionChecker.check(), lastFrameTime);     //Checks if collisions are occuring and then handles them or changes score
            continues = score.handleScoreChange(scoreChange);                              //Score is updated and checks if max score is reached

            userinterface.update();                 //Repaints the drawingboard

            deltaTime = (System.nanoTime() - deltaTime);   //sets deltatimemillis as the time difference in milli secs
            if ((double) deltaTime < FRAMETIME_MILLIS_TARGET * 1000000) {
                double ddT = FRAMETIME_MILLIS_TARGET * 1000000 - deltaTime;
                Thread.sleep((long) ddT / 1000000, (int) (ddT % 1000000));
            }

            lastFrameTime = ((double) (System.nanoTime() - deltaFactorTime)) / 1000000;
            if (lastFrameTime > 40) {
                lastFrameTime = 40;
            }
        }
    }

    // To be used after a goal has been scored, places all movable object back on their startingposition
    public void reset() {
        ball.setPosition(new Coor2D(Userinterface.WIDTH / 2, Userinterface.HEIGHT / 2));
        ball.velocity().changeLength(2.0f);

        resetMovablePlatforms();
        userinterface.update();

        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
        }
    }

    private void resetMovablePlatforms() {
        for (MovablePlatform movablePlatform : movablePlatforms) {
            movablePlatform.setPosition(movablePlatform.getStartingPosition());
        }
    }

    public void setPlatformMovement(int index, Direction direction) {
        movablePlatforms.get(index).setMovementDirection(direction);
    }

    //Moves all movingplatforms depending on their flags and whether they would stay in bounds
    private void movePlatforms() {
        for (MovablePlatform movablePlatform : movablePlatforms) {
            movablePlatform.update(lastFrameTime);
        }
    }

}
