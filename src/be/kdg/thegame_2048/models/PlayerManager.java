package be.kdg.thegame_2048.models;

import java.util.*;

/**
 * @author Jarne Van Aerde, Bryan de Ridder
 * @version 1.0 8/02/2017 17:17
 */
public final class PlayerManager {
    //EIGENSCHAPPEN
    private final List<Player> playerList;
    private Player currentPlayer;
    private int currentPlayerScore;

    //CONSTRUCTORS
    public PlayerManager() {
        this.playerList = new ArrayList<>();
    }

    public void saveInfoCurrentPlayer() {
        int bestScore = this.currentPlayer.getBestScore();

        if (currentPlayerScore > bestScore) {
            this.currentPlayer.setBestScore(currentPlayerScore);
        }
    }

    //METHODEN
    public void setCurrentPlayerToNull() {
        this.currentPlayer = null;
    }

    public void setCurrentPlayer(String name) {
        for (Player player : playerList) {
            if (player.getName().toLowerCase().equals(name.toLowerCase())) {
                this.currentPlayer = player;
            }
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void addPlayer(String name) {
        playerList.add(new Player(name, 0));
        System.out.println("New player added: " + name);
        playerList.forEach(System.out::println);
    }

    public boolean checkIfExists(String name) {
        for (Player player : playerList) {
            if (player.getName().toLowerCase().equals(name.toLowerCase())) return true;
        }
        return false;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setCurrentPlayerScore(int currentPlayerScore) {
        this.currentPlayerScore = currentPlayerScore;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Player player : this.playerList) {
            s.append(player.toString()).append("\n");
        }
        return s.toString();
    }
}
