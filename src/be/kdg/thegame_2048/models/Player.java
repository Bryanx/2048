package be.kdg.thegame_2048.models;

/**
 * @author Jarne Van Aerde
 * @version 1.0 7/02/2017 19:58
 */
public class Player {
    //EIGENSCHAPPEN
    private String name;
    private String bestScore;

    //CONSTRUCTORS
    public Player(String name, String bestScore) {
        this.name = name;
        this.bestScore = bestScore;
    }

    //METHODEN
    public String getBestScore() {
        return bestScore;
    }

    public String getName() {
        return name;
    }

    public void setBestScore(String bestScore) {
        this.bestScore = bestScore;
    }
}
