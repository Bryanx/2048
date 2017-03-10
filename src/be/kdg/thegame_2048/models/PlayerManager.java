package be.kdg.thegame_2048.models;

import java.util.*;

/**
 * @author Jarne Van Aerde, Bryan de Ridder
 * @version 1.0 8/02/2017 17:17
 */
public final class PlayerManager {
    /**
     * This class manages all the players that exist.
     **/
    private final List<Player> playerList;
    private Player currentPlayer;
    private int currentPlayerScore;

    public PlayerManager() {
        this.playerList = new ArrayList<>();
    }

    /**
     * Sets the currentPlayer-object to null.
     **/
    public void setCurrentPlayerToNull() {
        this.currentPlayer = null;
    }

    /**
     * Takes a player out of the list of players.
     **/
    public void setCurrentPlayer(String name) {
        for (Player player : playerList) {
            if (player.getName().toLowerCase().equals(name.toLowerCase())) {
                this.currentPlayer = player;
            }
        }
    }

    /**
     * Returns the player that is currently playing the game.
     **/
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Adds a new player to the list of players.
     * This method is only used when you play 2048 for the first time.
     **/
    public void addPlayer(String name) {
        playerList.add(new Player(name, 0));
        System.out.println("New player added: " + name);
        playerList.forEach(System.out::println);
    }

    /**
     * Returns true if the name is already used and false if it's not used.
     * Used to check if a name is already used.
     * Used to check if an existing name matches a name in the list.
     **/
    public boolean checkIfExists(String name) {
        for (Player player : playerList) {
            if (player.getName().toLowerCase().equals(name.toLowerCase())) return true;
        }
        return false;
    }

    /**
     * Saves all the info off the current player.
     * If the player has beaten his highscore, dan
     **/
    public void saveInfoCurrentPlayer() {
        int bestScore = this.currentPlayer.getBestScore();

        if (currentPlayerScore > bestScore) {
            this.currentPlayer.setBestScore(currentPlayerScore);
        }
    }

    /**
     * Returns the list of players.
     * Its only use if for the highscoreView.
     **/
    public List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Used to store the current score of the player that is currently playing the game.
     **/
    public void setCurrentPlayerScore(int currentPlayerScore) {
        this.currentPlayerScore = currentPlayerScore;
    }

    /**
     * Returns a string of all the player that were ever registered
     **/
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Player player : this.playerList) {
            s.append(player.toString()).append("\n");
        }
        return s.toString();
    }
}
