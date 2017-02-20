package be.kdg.thegame_2048.models;

/**
 * @author Jarne van Aerde, Bryan de Ridder
 * @version 1.0 12/02/2017 19:40
 */
public final class Game {
    //ATTRIBUTES
    public enum Direction {
        TOP, DOWN, LEFT, RIGHT
    }

    //private Player playerNowPlaying;
    private Score score;
    private PlayerManager manager;
    private Playground playground;

    //CONSTRUCTORS
    public Game(PlayerManager playerManager) {
        this.score = new Score();
        this.playground = new Playground(this.score);
        this.manager = playerManager;

        playground.initialiseSections();
        this.playground.addRandomBlock();
        this.playground.addRandomBlock();
    }

    //METHODS
    public void runGameCycle(Direction direction) {
        boolean addRandom = false;
        switch (direction) {
            case TOP:
                playground.moveBlocksTop();
                if (checkIfMoveable(3, 0, -1, 'Y')) addRandom = true;
                break;
            case DOWN:
                playground.moveBlocksBottom();
                if (checkIfMoveable(0, 3, 1, 'Y')) addRandom = true;
                break;
            case LEFT:
                playground.moveBlocksLeft();
                if (checkIfMoveable(3, 0, -1, 'X')) addRandom = true;
                break;
            case RIGHT:
                playground.moveBlocksRight();
                if (checkIfMoveable(0, 3, 1, 'X')) addRandom = true;
        }
        //if (addRandom) {
            playground.addRandomBlock();
        //}
        //playground.setHasMerged(false);
        System.out.println(score.getScore() + "\n" + playground.toString());
    }

    private boolean checkIfMoveable(int begin, int end, int increment, char as) {
        boolean hasBlock = false;
        Section[][] sections = playground.getSections();
        for (int i = begin; i >= end; i = i + increment) {
            for (int j = begin; j >= end; j = j + increment) {
                if (as == 'Y') {
                    if (sections[i][j].hasBlock()) hasBlock = true;
                    if (hasBlock && !sections[i][j].hasBlock()) return true;
                } else {
                    if (sections[j][i].hasBlock()) hasBlock = true;
                    if (hasBlock && !sections[j][i].hasBlock()) return true;
                }
            }
        }
        return false;
    }

    public boolean hasWon() {
        Section[][] sections = playground.getSections();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (sections[i][j].hasBlock() && sections[i][j].getBlock().getValue() == 2048)
                    return true;
            }
        }
        return false;
    }

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

    public boolean beatHighscore(Player p) {
        return this.score.getScore() > p.getBestScore();
    }

    public Score getScore() {
        return score;
    }

    public int getPieceValue(int x, int y) {
        return playground.getSections()[x][y].getBlock().getValue();
    }

    public Block getPiece(int x, int y) {
        return playground.getSections()[x][y].getBlock();
    }
}
