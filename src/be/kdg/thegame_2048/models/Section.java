package be.kdg.thegame_2048.models;

/**
 * @author Jarne Van Aerde
 * @version 1.0 8/02/2017 17:06
 */
final class Section {
    //EIGENSCHAPPEN
    private Block block;

    //CONSTRUCTORS
    //Geen.

    //METHODEN
    public Block getBlock() {
        return this.block;
    }

    boolean hasBlock() {
        if (this.block == null) return false;
        return true;
    }

    void putBlock(Block block) {
        this.block = block;
    }

    public void removeBlock() {
        this.block = null;
    }

    @Override
    public String toString() {
        if (block == null) return "E";
        return block.toString();
    }
}
