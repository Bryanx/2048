package be.kdg.thegame_2048.models;

/**
 * @author Jarne Van Aerde, Bryan de Ridder
 * @version 1.0 8/02/2017 17:06
 */
final class Section {
    /**
     * A pojo that stores the most inmportant section inormation
     **/
    private Block block;

    Block getBlock() {
        return this.block;
    }

    boolean hasBlock() {
        return this.block != null;
    }

    void putBlock(Block block) {
        this.block = block;
    }

    void removeBlock() {
        this.block = null;
    }

    @Override
    public String toString() {
        if (block == null) return "E";
        return block.toString();
    }
}
