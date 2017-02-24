package be.kdg.thegame_2048.views.Game;

import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import jdk.nashorn.internal.ir.Block;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 17-02-17 11:11
 */
class GameMiddleView extends BorderPane {
    private static final Image BG = new Image("be/kdg/thegame_2048/views/img/bg.png");
    private GridPane sectionGrid;
    private BlockView[][] blocks;

    GameMiddleView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //16 blocks worden aangemaakt
        this.blocks = new BlockView[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                blocks[i][j] = new BlockView(0);
            }
        }
    }

    private void layoutNodes() {
        //fill grid with 0's
        this.sectionGrid = new GridPane();
        blocks[0][0].setValue(4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sectionGrid.add(blocks[i][j], i, j);
            }
        }
        sectionGrid.setVgap(10);
        sectionGrid.setHgap(10);
        sectionGrid.setAlignment(Pos.CENTER);

        BorderPane playground = new BorderPane(sectionGrid);
        playground.setMinSize(GameView.GAME_SIZE, GameView.GAME_SIZE);
        playground.setMaxSize(GameView.GAME_SIZE, GameView.GAME_SIZE);
        playground.setBackground(new Background(
                new BackgroundImage(BG, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
        this.setCenter(new BorderPane(playground));
    }

    void putBlockOnGrid(int value, int x, int y, boolean animate) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                blocks[y][x].setValue(value);
            }
        }
        if (animate) popIn(blocks[y][x]);
    }

    private void popIn(StackPane block) {
        ScaleTransition st = new ScaleTransition(Duration.millis(250), block);
        st.setFromX(0.0);
        st.setFromY(0.0);
        st.setToX(1.0);
        st.setToY(1.0);
        st.play();
    }

    GridPane getSectionGrid() {
        return sectionGrid;
    }

    int getBlockValue(int i) {
        BlockView block = (BlockView) sectionGrid.getChildren().get(i);
        return block.getValue();
    }

    void resetGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                blocks[i][j].setValue(0);
            }
        }
    }
}
