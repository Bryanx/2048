package be.kdg.thegame_2048.models;

/**
 * @author Jarne Van Aerde, Bryan de Ridder
 * @version 1.0 7/02/2017 19:58
 */
public class Player implements Comparable<Player> {
    //EIGENSCHAPPEN
    private final String name;
    private int bestScore;
    private String lastMove;
    private int highestBlockValue;

    //CONSTRUCTORS
    Player(String name, int bestScore) {
        this.name = name;
        this.bestScore = bestScore;
    }

    //METHODEN
    public String getLastMove() {
        return lastMove;
    }

    public void setLastMove(String lastMove) {
        System.out.println(lastMove.replaceAll("\n", ""));
        this.lastMove = lastMove;
    }

    public int getBestScore() {
        return bestScore;
    }

    public String getName() {
        return name.toLowerCase();
    }

    void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public int getHighestBlockValue() {
        return highestBlockValue;
    }

    public void setHighestBlockValue(int highestBlockValue) {
        this.highestBlockValue = highestBlockValue;
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
