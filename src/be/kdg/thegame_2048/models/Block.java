package be.kdg.thegame_2048.models;

/**
 * A pojo used to store all block-data.
 *
 * @author Jarne Van Aerde, Bryan de Ridder
 * @version 1.0 8/02/2017 16:54
 */
final class Block {
    private int value;

    Block(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
