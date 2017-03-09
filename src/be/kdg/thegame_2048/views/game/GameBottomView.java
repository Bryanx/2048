package be.kdg.thegame_2048.views.game;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 17-02-17 10:45
 */
class GameBottomView extends BorderPane {
    private Button btnRestart;
    private Button btnUndo;
    private Button btnHighScores;
    private Button btnExit;

    GameBottomView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.btnRestart = new Button();
        this.btnUndo = new Button();
        this.btnHighScores = new Button();
        this.btnRestart = new Button();
        this.btnExit = new Button();
        addStyles();
    }

    private void layoutNodes() {
        GridPane gridBottom = new GridPane();
        gridBottom.add(btnRestart, 0, 0);
        gridBottom.add(btnUndo, 1, 0);
        gridBottom.add(btnHighScores, 2, 0);
        gridBottom.add(btnExit, 3, 0);
        gridBottom.setMaxWidth(GameView.SCENE_WIDTH - GameView.OVERALL_PADDING * 2);
        gridBottom.setMinHeight(GameView.HEIGHT_OUTER_PANELS);
        Button[] buttons = {btnHighScores, btnRestart, btnExit};
        for (Button btn : buttons) {
            GridPane.setVgrow(btn, Priority.ALWAYS);
            GridPane.setHgrow(btn, Priority.ALWAYS);
            GridPane.setValignment(btn, VPos.CENTER);
            GridPane.setHalignment(btn, HPos.CENTER);
        }
        this.setCenter(gridBottom);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(215, 180, 7), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void addStyles() {
        btnHighScores.getStyleClass().add("btnHighScores");
        btnRestart.getStyleClass().add("btnRestart");
        btnUndo.getStyleClass().add("btnUndo");
        btnExit.getStyleClass().add("btnExit");
    }

    Button getBtnHighScores() {
        return btnHighScores;
    }

    Button getBtnExit() {
        return btnExit;
    }

    Button getBtnRestart() {
        return btnRestart;
    }

    public Button getBtnUndo() {
        return btnUndo;
    }
}
