package be.kdg.thegame_2048.models;

/**
 * @author Jarne Van Aerde, Bryan de Ridder
 * @version 1.0 8/02/2017 16:57
 */
public final class Score {
    //EIGENSCHAPPEN
    private int score;

    //CONSTRUCTORS
    Score() {
        this.score = 0;
    }

    //METHODEN
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    void calculateScore(Block block, Block otherBlock) {
        this.score += block.getValue() + otherBlock.getValue();
    }

    @Override
    public String toString() {
        return String.valueOf(this.score);
    }
}
