package Pong;

// Starts the game

public class Main {

    public static void main(String[] args) {
        Pong game = new Pong(GameMode.SINGLEPLAYER);
        game.start();
    }

}

//TODO  AI; game end screen;
