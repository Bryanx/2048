package be.kdg.thegame_2048.views.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * The middle part of the game view.
 * It contains the game grid.
 * All block objects are made here.
 *
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 17-02-17 11:11
 */
class GameMiddleView extends BorderPane {
    private static final Paint GRID_BG_COLOR = Color.web("bbada0");
    private static final double GRID_BG_CORNERRADII = 7;
    private static final int BLOCK_MARGIN = 10;
    static final int GRID_SIZE = 4;

    private StackPane container;
    private BlockView[][] blocks;
    private BlockView[][] bgBlocks;

    GameMiddleView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.container = new StackPane();
        //16 blocks are made for background and foreground
        this.blocks = new BlockView[GRID_SIZE][GRID_SIZE];
        this.bgBlocks = new BlockView[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                blocks[i][j] = new BlockView();
                bgBlocks[i][j] = new BlockView();
            }
        }
    }

    private void layoutNodes() {
        //fill grid with 0's
        GridPane bgGrid = new GridPane();
        GridPane sectionGrid = new GridPane();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                sectionGrid.add(blocks[i][j], i, j);
                bgGrid.add(bgBlocks[i][j], i, j);
            }
        }

        sectionGrid.setVgap(BLOCK_MARGIN);
        sectionGrid.setHgap(BLOCK_MARGIN);
        sectionGrid.setAlignment(Pos.CENTER);
        bgGrid.setVgap(BLOCK_MARGIN);
        bgGrid.setHgap(BLOCK_MARGIN);
        bgGrid.setAlignment(Pos.CENTER);

        container.getChildren().addAll(bgGrid,sectionGrid);

        BorderPane playground = new BorderPane(container);
        playground.setMinSize(GameView.GAME_SIZE, GameView.GAME_SIZE);
        playground.setMaxSize(GameView.GAME_SIZE, GameView.GAME_SIZE);
        playground.setBackground(new Background(new BackgroundFill(GRID_BG_COLOR,
                new CornerRadii(GRID_BG_CORNERRADII), Insets.EMPTY)));

        this.setCenter(playground);
    }

    void changeBlockValue(int value, int x, int y) {
        this.blocks[y][x].setValue(value);
    }

    BlockView getBlock(int x, int y) {
        return blocks[x][y];
    }

    int getBlockValue(int x, int y) {
        return blocks[x][y].getValue();
    }
}
