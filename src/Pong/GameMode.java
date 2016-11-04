package Pong;

/* Gamemode class, is used to create new gamemodes, used in Pong.java */

public enum GameMode {
    SINGLEPLAYER(1, "Pong - Singleplayer"),
    TWOPLAYER(2, "Pong - Twoplayer");

    private final int playerNumber;
    private final String title;

    GameMode(int playerNumber, String title) {
        this.playerNumber = playerNumber;
        this.title = title;
    }

    public int getPlayers() {
        return playerNumber;
    }

    public String getTitle() {
        return title;
    }

}
