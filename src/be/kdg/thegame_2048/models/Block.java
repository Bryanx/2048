package be.kdg.thegame_2048.models;

/**
 * @author Jarne Van Aerde, Bryan de Ridder
 * @version 1.0 8/02/2017 16:54
 */
class Block {
    //EIGENSCHAPPEN
    private int value;

    //CONSTRUCTORS
    Block(int value) {
        this.value = value;
    }

    //METHODEN
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
