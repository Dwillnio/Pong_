package Pong.Userinterface;

/* Userinterface of the game, contains the drawingboard */

import Pong.Objects.Drawable;
import Pong.Pong;
import Pong.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Userinterface implements Runnable{

    private JFrame frame;
    private DrawingBoard drawingBoard;

    private final Pong pong;

    public final static int HEIGHT = 400;   //Height of the drawingboard
    public final static int WIDTH = 600;    //Width of the drawingboard

    public Userinterface(Pong pong) {
        this.pong = pong;
    }

    //run() function, to be executed by swingutilities.invokelater
    //Basically initializes the frame
    @Override
    public void run() {
        frame = new JFrame();
        frame.setTitle(pong.getGameMode().getTitle());
        frame.setLocation(100, 100);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT+29)); // +29 = top bar adjustment
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            createComponents(frame.getContentPane());
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.exit(0);
        }

        frame.pack();
        frame.setVisible(true);
    }

    //Creates the drawingboard, adds it and sets the gamemode appropriate keylistener
    private void createComponents(Container container) {
        if(pong.getGameMode().getPlayers() == 1) {
            frame.addKeyListener(new SinglePlayerKeyBoardListener());
        } else if (pong.getGameMode().getPlayers() == 2) {
            frame.addKeyListener(new TwoPlayerKeyBoardListener());
        } else {
            throw new IllegalStateException("Unsupported playercount");
        }

        drawingBoard = new DrawingBoard(getDrawables());
        container.add(drawingBoard);
    }

    //returns a list of all objects to be drawn by the drawingboard
    private List<Drawable> getDrawables() {
        List<Drawable> drawables = new ArrayList<Drawable>();
        drawables.add(new MiddleLine());
        drawables.add(pong.getScore());
        drawables.add(pong.getBall());
        drawables.addAll(pong.getPlatforms());
        return drawables;
    }

    //Repaints the drawinboard
    public void update() {
        drawingBoard.update();
    }

    //Used to check whether drawingboard is created and game can start
    public DrawingBoard getDrawingBoard() {
        return drawingBoard;
    }

    /* Area for the keylisteners that are set in createcomponents
    *  They function by setting the appropriate flag for a certain movingplatform
    *  when a key is pressed and removing it when the key is released */

    private class TwoPlayerKeyBoardListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    pong.setPlatformMovement(0, Direction.UP);
                    break;
                case KeyEvent.VK_S:
                    pong.setPlatformMovement(0, Direction.DOWN);
                    break;
                case KeyEvent.VK_UP:
                    pong.setPlatformMovement(1, Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    pong.setPlatformMovement(1, Direction.DOWN);
                    break;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) { }
        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                case KeyEvent.VK_S:
                    pong.setPlatformMovement(0, Direction.STATIONARY);
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                    pong.setPlatformMovement(1, Direction.STATIONARY);
                    break;
            }
        }

    }

    private class SinglePlayerKeyBoardListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    pong.setPlatformMovement(0, Direction.UP);
                    break;
                case KeyEvent.VK_S:
                    pong.setPlatformMovement(0, Direction.DOWN);
                    break;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) { }
        @Override
        public void keyReleased(KeyEvent e) {
            pong.setPlatformMovement(0, Direction.STATIONARY);
        }

    }

}
