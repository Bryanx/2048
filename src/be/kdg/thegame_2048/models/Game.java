package be.kdg.thegame_2048.models;

/**
 * @author Jarne van Aerde, Bryan de Ridder
 * @version 1.0 12/02/2017 19:40
 */
public final class Game {
    /**
     * The game class puts everyting together. It represents the game as a whole.
     * This has access to most of the other model classes.
     **/
    public enum Direction {
        TOP, DOWN, LEFT, RIGHT
    }

    final static private int STAETVALUE = 2;

    private final Score score;
    private final PlayerManager manager;
    private final Playground playground;
    private String lastMove;
    private String currentMove;
    private boolean isPlayingUndo;

    public Game(PlayerManager playerManager) {
        this.score = new Score();
        this.playground = new Playground(this.score);
        this.isPlayingUndo = false;

        this.manager = playerManager;

        playground.initialiseSections();
        this.playground.addRandomBlock(STAETVALUE);
        this.playground.addRandomBlock(STAETVALUE);

    }

    /**
     * Goes back to the last move.
     * Only used when player presses on the undo-button.
     **/
    public void goToLastMove() {
        System.out.println(this.lastMove.replaceAll("\n", "").replaceAll("  ", " "));
        String fields[] = this.lastMove.replaceAll("\n", "").replaceAll("  ", " ").split(" ");
        Section[][] sections = new Section[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Section section = new Section();
                if (!fields[(i * 4) + j].contains("E")) {
                    section.putBlock(new Block(Integer.parseInt(fields[(i * 4) + j])));
                }
                sections[i][j] = section;
                System.out.print(section.toString() + " ");
            }
        }
        playground.initialiseSections(sections);
    }

    /**
     * Gives back the x-coordinate of the last block that was put on the playground.
     **/
    public int getCoordRandomBlockX() {
        return playground.getCoordRandomBlockX();
    }

    /**
     * Gives back the x-coordinate of the last block that was put on the playground.
     **/
    public int getCoordRandomBlockY() {
        return playground.getCoordRandomBlockY();
    }

    /**
     * Runs one cycle of the game on the logical level.
     **/
    public void runGameCycle(Direction direction) {
        this.lastMove = playground.toString();
        switch (direction) {
            case TOP:
                playground.moveBlocksTop();
                break;
            case DOWN:
                playground.moveBlocksBottom();
                break;
            case LEFT:
                playground.moveBlocksLeft();
                break;
            case RIGHT:
                playground.moveBlocksRight();
        }
        this.currentMove = playground.toString();
        if (!lastMove.equals(currentMove)) {
            playground.addRandomBlock(STAETVALUE);
        }
        System.out.println(score.getScore() + "\n" + playground.toString());
    }

    /**
     * Decides if the current player has won the game.
     * After the player has won the game, he can choose to continue or to stop playing.
     **/
    public boolean hasWon() {
        Section[][] sections = playground.getSections();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (sections[i][j].hasBlock() && sections[i][j].getBlock().getValue() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Decides if the current player has lost the game.
     * If the player loses the game, than he has no other choice than to restart.
     **/
    public boolean hasLost() {
        if (!playground.playGroundFull()) return false;
        Section[][] sections = playground.getSections();

        //HORIZONTAL CONTROL
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j != 3) {
                    if (sections[i][j].getBlock().getValue() == sections[i][j + 1].getBlock().getValue()) return false;
                }
            }
        }

        //VERTICAL CONTROL
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j != 3) {
                    if (sections[j][i].getBlock().getValue() == sections[j + 1][i].getBlock().getValue()) return false;
                }
            }
        }

        return true;
    }

    /**
     * Gives back a grind of the positions of the sections and their values.
     * An E stands for an empty space.
     * The numbers represent a block with their current value.
     **/
    @Override
    public String toString() {
        return playground.toString();
    }

    /**
     * Returns the current score of the game.
     **/
    public Score getScore() {
        return score;
    }

    /**
     * Returns the value of an individual block after a move method has been performed.
     **/
    public int getPieceValue(int x, int y) {
        return playground.getSections()[x][y].getBlock().getValue();
    }

    /**
     * Returns an individual block after a move method has been performed.
     **/
    public Block getPiece(int x, int y) {
        return playground.getSections()[x][y].getBlock();
    }

    /**
     * Returns a string of the last move.
     **/
    public String getLastMove() {
        return lastMove;
    }

    /**
     * Returns a string of the current move.
     **/
    public String getCurrentMove() {
        return currentMove;
    }

    /**
     * If the player is playing with the undo, then the score won't be saved.
     **/
    public boolean isPlayingUndo() {
        return isPlayingUndo;
    }

    /**
     * Sets the boolean to true if the player decides to play with the undo button
     **/
    public void setPlayingUndo(boolean playingUndo) {
        isPlayingUndo = playingUndo;
    }

    /**
     * Gives back the highest block value that's currently on the playground
     **/
    public int getHighestBlockValue() {
        Section[][] sections = playground.getSections();
        int highestValue = 2;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (sections[i][j].getBlock().getValue() > highestValue)
                    highestValue = sections[i][j].getBlock().getValue();
            }
        }
        return highestValue;
    }
}
