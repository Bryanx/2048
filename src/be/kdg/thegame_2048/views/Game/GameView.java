package be.kdg.thegame_2048.views.Game;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 12-02-17 18:49
 */
public final class GameView extends BorderPane {
    static final double OVERALL_PADDING = 50;
    static final double SCENE_WIDTH = 550;
    static final double HEIGHT_OUTER_PANELS = 100;
    static final double GAME_SIZE = 450;

    private BorderPane container;

    private GameTopView gameTopView;
    private GameMiddleView gameMiddleView;
    private GameBottomView gameBottomView;

    public GameView() {
        initialiseNodes();
        layoutNodes();

    }

    private void initialiseNodes() {
        this.gameTopView = new GameTopView();
        this.gameMiddleView = new GameMiddleView();
        this.gameBottomView = new GameBottomView();
    }

    public void layoutNodes() {
        this.container = new BorderPane();
        container.setTop(gameTopView);
        container.setCenter(gameMiddleView);
        container.setBottom(gameBottomView);
        this.setCenter(container);
    }

    void setView(BorderPane result) {
        container.setEffect(new GaussianBlur());
        StackPane stack = new StackPane(container, result);
        this.setCenter(stack);
    }

    void putBlockOnGrid(int value, int x , int y, boolean animate) {
        gameMiddleView.putBlockOnGrid(value, x, y, animate);
    }

    GameTopView getTopView() {
        return gameTopView;
    }

    GameMiddleView getMiddleView() {
        return gameMiddleView;
    }

    GameBottomView getBottomView() {
        return gameBottomView;
    }

    GridPane getSectionGrid() {
        return gameMiddleView.getSectionGrid();
    }

    Button getBtnExit() {
        return gameBottomView.getBtnExit();
    }

    public Label getLblBestScoreInput() {
        return gameTopView.getLblBestScoreInput();
    }

    public Label getLblScoreInput() {
        return gameTopView.getLblScoreInput();
    }

    void resetGrid() {
        gameMiddleView.getSectionGrid().getChildren().clear();
    }

    Button getBtnRestart() {
        return gameBottomView.getBtnRestart();
    }

    int getBlockValue(int i) {
        return gameMiddleView.getBlockValue(i);
    }
}