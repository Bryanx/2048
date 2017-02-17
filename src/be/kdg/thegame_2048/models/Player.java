package be.kdg.thegame_2048.models;

/**
 * @author Jarne Van Aerde
 * @version 1.0 7/02/2017 19:58
 */
class Player {
    //EIGENSCHAPPEN
    private String name;
    private int bestScore;

    //CONSTRUCTORS
    Player(String name, int bestScore) {
        this.name = name;
        this.bestScore = bestScore;
    }

    //METHODEN
    public int getBestScore() {
        return bestScore;
    }

    String getName() {
        return name;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    @Override
    public String toString() {
        return String.format("Name: %-20sBest Score: %d", this.name, this.bestScore);
    }
}
