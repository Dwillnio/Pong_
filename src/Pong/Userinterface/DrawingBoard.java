package Pong.Userinterface;

/* JPanel on that is drawn */

import Pong.Objects.Drawable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawingBoard extends JPanel {

    //List of components that are drawn, all must implement the Drawable interface
    private final List<Drawable> toDraw;

    public DrawingBoard(List<Drawable> toDraw) {
        setBackground(Color.lightGray);
        this.toDraw = toDraw;
    }

    //Calls the parent method and draws every component in toDraw
    // by calling their draw functions
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        for(Drawable gameObject: toDraw) {
            gameObject.draw(graphics);
        }
    }

    //Repaints the Drawingboard, happens at the end of every frame
    public void update() {
        super.repaint();
    }

}
