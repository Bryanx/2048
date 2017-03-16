package be.kdg.thegame_2048.models;

/**
 * This class is used to store all the relevant player-data
 *
 * @author Jarne Van Aerde, Bryan de Ridder
 * @version 1.0 7/02/2017 19:58
 */
public class Player implements Comparable<Player> {
    private final String name;
    private int bestScore;

    Player(String name, int bestScore) {
        this.name = name;
        this.bestScore = bestScore;
    }

    /**
     * Returns the best score of an instance of a player.
     **/
    public int getBestScore() {
        return bestScore;
    }

    /**
     * Returns the name of an instance of a player.
     **/
    public String getName() {
        return name.toLowerCase();
    }

    /**
     * If a player breaks its personal record,
     * then this method will update the best score.
     **/
    void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    /**
     * Gives back a string that contains the name and the highest score of an instance of a player.
     **/
    @Override
    public String toString() {
        return String.format("Name: %-20sBest Score: %d", this.name, this.bestScore);
    }

    /**
     * If two players have the same high score, then they need to be organised by name.
     * This takes care of that.
     **/
    @Override
    public int compareTo(Player o) {
        if (this.bestScore < o.getBestScore()) return 1;
        if (this.bestScore > o.getBestScore()) return -1;
        return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
    }
}
