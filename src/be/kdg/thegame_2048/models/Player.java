package be.kdg.thegame_2048.models;

/**
 * @author Jarne Van Aerde, Bryan de Ridder
 * @version 1.0 7/02/2017 19:58
 */
public class Player implements Comparable<Player> {
    //EIGENSCHAPPEN
    private String name;
    private int bestScore;
    private String lastMove;
    private int lastScore;

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

    String getLastMove() {
        return this.lastMove;
    }

    public void setLastMove(String lastMove) {
        if (lastMove.isEmpty()) this.lastMove = null;
        if (lastMove.contains("\n")) {
            this.lastMove = lastMove.replaceAll("\\n", " ").replaceAll("  ", " ").replaceAll("  ",  " ");
        } else {
            this.lastMove = lastMove;
        }
        System.out.println(this.lastMove);
    }

    public int getLastScore() {
        return lastScore;
    }

    public void setLastScore(int lastScore) {
        this.lastScore = lastScore;
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
