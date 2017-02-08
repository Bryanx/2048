package be.kdg.thegame_2048.models;

/**
 * @author Jarne Van Aerde
 * @version 1.0 8/02/2017 17:06
 */
public class Section {
    //EIGENSCHAPPEN
    private final String coord;
    private Block block;
    private boolean containsBlock;

    //CONSTRUCTORS
    public Section(String coord) {
        this.coord = coord;
    }

    //METHODEN
    public String getCoord() {
        return coord;
    }

    public Block getBlockAndRemove() {
        Block returnBlock = this.block;
        this.block = null;
        return returnBlock;
    }

    public boolean hasBlock() {
        if (this.block == null) return false;
        return true;
    }

    public void putBlock(Block block) {
        this.block = block;
    }
}
