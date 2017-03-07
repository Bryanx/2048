package be.kdg.thegame_2048.views.Game;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 17-02-17 11:06
 */
class GameTopView extends BorderPane {
    private static final ImageView IMG_LOGO = new ImageView("be/kdg/thegame_2048/views/img/logo-2048.png");
    private Label lblBestScore;
    private Label lblBestScoreInput;
    private Label lblScore;
    private StackPane spScore;
    private Label lblScoreInput;
    private Label lblScoreChange;

    GameTopView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.lblBestScore = new Label("Best score: ");
        this.lblBestScoreInput = new Label("0");
        this.lblScore = new Label("Current score: ");
        this.lblScoreInput = new Label("0");
        this.lblScoreChange = new Label("0");
        this.lblScoreChange.setVisible(false);

        this.spScore = new StackPane(lblScoreInput, lblScoreChange);

        addStyles();
    }

    private void layoutNodes() {
        GridPane gridTop = new GridPane();
        VBox vboxScore = new VBox(lblBestScore, lblScore);
        VBox vboxScoreInput = new VBox(lblBestScoreInput, spScore);
        gridTop.add(IMG_LOGO, 0, 0, 1, 2);
        gridTop.add(vboxScore, 1, 0);
        gridTop.add(vboxScoreInput, 2, 0);
        gridTop.setMaxWidth(GameView.SCENE_WIDTH - GameView.OVERALL_PADDING * 2);
        gridTop.setMinHeight(GameView.HEIGHT_OUTER_PANELS);
        VBox[] vBoxes = {vboxScoreInput, vboxScore};
        for (VBox vBox : vBoxes) {
            GridPane.setVgrow(vBox, Priority.ALWAYS);
            GridPane.setHalignment(vBox, HPos.RIGHT);
            GridPane.setValignment(vBox, VPos.CENTER);
            vBox.setAlignment(Pos.CENTER_RIGHT);
        }
        GridPane.setHgrow(vboxScore, Priority.ALWAYS);
        this.setCenter(gridTop);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(215, 180, 7), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void addStyles() {
        lblBestScore.getStyleClass().add("inGameScore");
        lblBestScoreInput.getStyleClass().add("inGameScore");
        lblScore.getStyleClass().add("inGameScore");
        lblScoreInput.getStyleClass().add("inGameScore");
    }

    Label getLblBestScoreInput() {
        return lblBestScoreInput;
    }

    Label getLblScoreInput() {
        return lblScoreInput;
    }

    Label getLblScoreChange() {
        return lblScoreChange;
    }
}
