package be.kdg.thegame_2048.models;

/**
 * The game class puts everything together. It represents the game as a whole.
 * This has access to most of the other model classes.
 *
 * @author Jarne van Aerde, Bryan de Ridder
 * @version 1.0 12/02/2017 19:40
 */
public final class Game {
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    final static private int STARTVALUE = 2;

    private final Score score;
    private final Playground playground;
    private String lastMove;
    private String currentMove;
    private boolean isPlayingUndo;

    public Game() {
        this.score = new Score();
        this.playground = new Playground(this.score);
        this.isPlayingUndo = false;

        playground.initialiseSections();
        this.playground.addRandomBlock(STARTVALUE);
        this.playground.addRandomBlock(STARTVALUE);
    }

    /**
     * Goes back to the last move.
     * Only used when player presses on the undo-button.
     **/
    public void goToLastMove() {
        playground.goBack(this.lastMove);
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
     * @param direction needs a direction parameter.
     **/
    public void runGameCycle(Direction direction) {
        this.lastMove = playground.toString();
        switch (direction) {
            case UP:
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
        //TODO: VERWIJDER.
        if (!lastMove.equals(currentMove)) {
            playground.addRandomBlock(STARTVALUE);
        }
        System.out.println(score.getScore() + "\n" + playground.toString());
        //TODO: VERWIJDR.
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
     * @return the current score of the game.
     **/
    public int getScore() {
        return score.getScore();
    }

    public Score getScoreObject() {
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
}
