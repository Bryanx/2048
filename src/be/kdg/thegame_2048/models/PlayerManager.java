package be.kdg.thegame_2048.models;

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
    //Geen.

    //METHODEN
    public Player getPlayerNowPlaying() {
        return playerNowPlaying;
    }

    public void addPlayer(String namePlayer) {
        if (!isUnique(namePlayer)) return;
        playerList.add(new Player(namePlayer, 0));
    }

    //Voor het aanmaken van een nieuwe speler.
    private boolean isUnique(String namePlayer) throws IllegalArgumentException {
        //Ja kan diet niet oplossen met de methode contains,
        //Want dan gaat die kijken of het object Player uniek is en niet de naam.
        for (Player player : playerList) {
            if (player.getName().equals(namePlayer)) {
                //Geen return meer nodig, de exception maakt meteen een einde aan de methode.
                throw new IllegalArgumentException("This name already exists :( Choose another one.");
            }
        }
        addPlayer(namePlayer);
        return true;
    }

    //Voor het zoeken naar een bestaande speler
    public boolean checkIfExists(String namePlayer) throws IllegalArgumentException {
        for (Player player: playerList) {
            if (player.getName().equals(namePlayer)) {
                this.playerNowPlaying = player;
                return true;
            }
        }
        throw new IllegalArgumentException("This player doesn't exists, check if the name is spelled correctly.");
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Player player: this.playerList) {
            s.append(player.toString() + "\n");
        }
        return s.toString();
    }
}
