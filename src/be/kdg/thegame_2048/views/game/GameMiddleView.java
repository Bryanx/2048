package be.kdg.thegame_2048.views.game;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 17-02-17 11:11
 */
class GameMiddleView extends BorderPane {
    private static final int BLOCK_MARGIN = 10;
    static final int GRID_SIZE = 4;
    private static final Image BG = new Image("be/kdg/thegame_2048/views/img/bg.png");
    private BlockView[][] blocks;

    GameMiddleView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //16 blocks are made
        this.blocks = new BlockView[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                blocks[i][j] = new BlockView(0);
            }
        }
    }

    private void layoutNodes() {
        //fill grid with 0's
        GridPane sectionGrid = new GridPane();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                sectionGrid.add(blocks[i][j], i, j);
            }
        }
        sectionGrid.setVgap(BLOCK_MARGIN);
        sectionGrid.setHgap(BLOCK_MARGIN);
        sectionGrid.setAlignment(Pos.CENTER);

        BorderPane playground = new BorderPane(sectionGrid);
        playground.setMinSize(GameView.GAME_SIZE, GameView.GAME_SIZE);
        playground.setMaxSize(GameView.GAME_SIZE, GameView.GAME_SIZE);
        playground.setBackground(new Background(
                new BackgroundImage(BG, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
        this.setCenter(new BorderPane(playground));
    }

    /**
     * Changes the value of a block at x y intersect.
     * This method is used on a graphical level.
     **/
    void putBlockOnGrid(int value, int x, int y) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                blocks[y][x].setValue(value);
            }
        }
    }

    /**
     * Returns a block at coordinates x y.
     **/
    BlockView getBlock(int x, int y) {
        return blocks[x][y];
    }

    /**
     * Returns a block value at coordinates x y.
     **/
    int getBValue(int x, int y) {
        return blocks[x][y].getValue();
    }
}
