package Pong;

// Starts the game

public class Main {

    public static void main(String[] args) {
        Pong game = new Pong(GameMode.SINGLEPLAYER);
        game.start();
    }

}

//TODO  comment everything; ball changes speed and direction; Fix the collision System; AI; game end screen;
