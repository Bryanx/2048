package be.kdg.thegame_2048.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jarne Van Aerde
 * @version 1.0 8/02/2017 17:17
 */
public final class PlayerManager {
    //EIGENSCHAPPEN
    private List<Player> playerList;
    private Player playerNowPlaying;

    //CONSTRUCTORS
    public PlayerManager() {
        this.playerList = new ArrayList<>();
    }

    //METHODEN
    public void setPlayerNowPlayingToNull() {
        this.playerNowPlaying = null;
    }

    public void setPlayerNowPlaying(String name) {
        for (Player player : playerList) {
            if (player.getName().toLowerCase().equals(name.toLowerCase())) {
                this.playerNowPlaying = player;
            }
        }
    }

    public Player getPlayerNowPlaying() {
        return playerNowPlaying;
    }

    public void addPlayer(String name) {
        playerList.add(new Player(name, 0));
        System.out.println("New player added: " + name);
        for (Player player : playerList) {
            System.out.println(player);
        }
    }

    public boolean checkIfExists(String name) {
        for (Player player : playerList) {
            if (player.getName().toLowerCase().equals(name.toLowerCase())) return true;
        }
        return false;
    }

    public void updatePlayerInfo(Player p) {
       for (Player player: playerList) {
           if (player.getName().equals(p.getName())) {
               player.setBestScore(p.getBestScore());
           }
       }
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Player player : this.playerList) {
            s.append(player.toString() + "\n");
        }
        return s.toString();
    }
}
