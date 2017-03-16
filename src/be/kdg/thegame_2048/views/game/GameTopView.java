package be.kdg.thegame_2048.views.game;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * The topside of the game view.
 * It contains all score information.
 *
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 17-02-17 11:06
 */
class GameTopView extends BorderPane {
    private static final ImageView IMG_LOGO = new ImageView("be/kdg/thegame_2048/views/img/logo-2048.png");
    private static final String BEST_SCORE_TEXT = "Best score: ";
    private static final String CURRENT_SCORE_TEXT = "Current score: ";
    private Label lblBestScore;
    private Label lblBestScoreInput;
    private Label lblCurrentScore;
    private Label lblCurrentScoreInput;
    private Label lblScoreAnimation;
    private StackPane spScore;

    GameTopView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.lblBestScore = new Label(BEST_SCORE_TEXT);
        this.lblBestScoreInput = new Label();

        this.lblCurrentScore = new Label(CURRENT_SCORE_TEXT);
        this.lblCurrentScoreInput = new Label();

        this.lblScoreAnimation = new Label();
        this.lblScoreAnimation.setVisible(false);

        //stacks the Label to be animated on top of the original score.
        this.spScore = new StackPane(lblCurrentScoreInput, lblScoreAnimation);
        spScore.setAlignment(Pos.CENTER_RIGHT);

        addStyles();
    }

    private void layoutNodes() {
        VBox vboxScore = new VBox(lblBestScore, lblCurrentScore);
        VBox vboxScoreInput = new VBox(lblBestScoreInput, spScore);

        GridPane gridTop = new GridPane();
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
    }

    /**
     * Adds custom css selector to each individual node.
     **/
    private void addStyles() {
        this.getStyleClass().add("darkerBG");
        lblBestScore.getStyleClass().add("inGameScore");
        lblBestScoreInput.getStyleClass().add("inGameScore");
        lblCurrentScore.getStyleClass().add("inGameScore");
        lblCurrentScoreInput.getStyleClass().add("inGameScore");
        lblScoreAnimation.getStyleClass().add("lblScoreAnimation");
    }

    /**
     * @return a Label in which score animations can be put.
     **/
    Label getLblScoreAnimation() {
        return lblScoreAnimation;
    }
    Label getLblBestScoreInput() {
        return lblBestScoreInput;
    }
    Label getLblCurrentScoreInput() {
        return lblCurrentScoreInput;
    }
}
