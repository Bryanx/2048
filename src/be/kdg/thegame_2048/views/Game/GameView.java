package be.kdg.thegame_2048.views.Game;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 12-02-17 18:49
 */
public final class GameView extends BorderPane {
    static final double OVERALL_PADDING = 50;
    static final double SCENE_WIDTH = 550;
    static final double HEIGHT_OUTER_PANELS = 100;
    static final double GAME_SIZE = 450;

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

    private void layoutNodes() {
        this.setTop(gameTopView);
        this.setCenter(gameMiddleView);
        this.setBottom(gameBottomView);
    }

    Image getImgBlock(int value) {
        return gameMiddleView.getImgBlock(value); //value 0 = Empty
    }

    Button getBtnHighScores() {
        return gameBottomView.getBtnHighScores();
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
}