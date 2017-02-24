package be.kdg.thegame_2048.models;

/**
 * @author Jarne Van Aerde, Bryan de Ridder
 * @version 1.0 7/02/2017 19:58
 */
public class Player implements Comparable<Player> {
    //EIGENSCHAPPEN
    private String name;
    private int bestScore;
    private String lastPlayed;

    //CONSTRUCTORS
    Player(String name, int bestScore) {
        this.name = name;
        this.bestScore = bestScore;
    }

    //METHODEN
    public int getBestScore() {
        return bestScore;
    }

    public String getName() {
        return name.toLowerCase();
    }

    void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public String getLastPlayed() {
        return this.lastPlayed;
    }

    public void setLastPlayed(String lastPlayed) {
        if (lastPlayed == null) {
            this.lastPlayed = null;
        } else {
            String formatted = lastPlayed.replaceAll("\\n", "");
            formatted = formatted.replaceAll("  ", " ");
            this.lastPlayed = formatted;
        }
    }

    @Override
    public String toString() {
        return String.format("Name: %-20sBest Score: %d", this.name, this.bestScore);
    }

    @Override
    public int compareTo(Player o) {
        if (this.bestScore < o.getBestScore()) return 1;
        if (this.bestScore > o.getBestScore()) return -1;
        return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
    }
}
