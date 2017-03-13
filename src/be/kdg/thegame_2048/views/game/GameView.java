package be.kdg.thegame_2048.views.game;

import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
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

    /**
     * Makes the background blurry and puts a new BorderPane on top.
     **/
    void setView(BorderPane pane) {
        container.setEffect(new GaussianBlur());
        StackPane stack = new StackPane(container, pane);
        this.setCenter(stack);
    }

    /**
     * Returns the TopView.
     **/
    GameTopView getTopView() {
        return gameTopView;
    }

    /**
     * Returns the MiddleView.
     **/
    GameMiddleView getMiddleView() {
        return gameMiddleView;
    }

    /**
     * Returns the BottomView.
     **/
    GameBottomView getBottomView() {
        return gameBottomView;
    }

    /**
     * Returns a Label for best score input.
     **/
    public Label getLblBestScoreInput() {
        return gameTopView.getLblBestScoreInput();
    }

    /**
     * Returns a Label for score input.
     **/
    public Label getLblScoreInput() {
        return gameTopView.getLblScoreInput();
    }
}