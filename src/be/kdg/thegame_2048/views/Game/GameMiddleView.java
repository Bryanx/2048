package be.kdg.thegame_2048.views.Game;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 17-02-17 11:11
 */
class GameMiddleView extends BorderPane {
    private static final Image BG = new Image("be/kdg/thegame_2048/views/img/bg.png");
    private GridPane sectionGrid;

    GameMiddleView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
    }

    void layoutNodes() {
        //MIDDLE
        this.sectionGrid = new GridPane();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sectionGrid.add(new BlockView(0, false), i, j);
            }
        }
        sectionGrid.setVgap(10);
        sectionGrid.setHgap(10);
        sectionGrid.setAlignment(Pos.CENTER);

        BorderPane playground = new BorderPane(sectionGrid);
        playground.setMinSize(GameView.GAME_SIZE, GameView.GAME_SIZE);
        playground.setMaxSize(GameView.GAME_SIZE, GameView.GAME_SIZE);
        playground.setBackground(new Background(
                new BackgroundFill(Color.web("#bbada0"), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setCenter(new BorderPane(playground));
    }

    void putBlockOnGrid(int value, int x, int y, boolean animate) {
        BlockView blockView = new BlockView(value, animate);
        if (animate) popIn(blockView);
        this.sectionGrid.add(blockView, y, x);
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


}
