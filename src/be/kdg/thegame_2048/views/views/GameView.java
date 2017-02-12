package be.kdg.thegame_2048.views.views;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Bryan de Ridder
 * @version 1.0 12-02-17 18:49
 */
public class GameView extends BorderPane {
    private static final double OVERALL_PADDING = 50;
    private ImageView logo;
    private Label lblBestScore;
    private Label lblBestScoreInput;
    private Label lblScore;
    private Label lblScoreInput;
    //bottom
    private Button btnRestart;
    private Button btnHighScores;

    public GameView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //Top side
        this.logo = new ImageView("be/kdg/thegame_2048/views/views/img/logo-2048.png");
        this.lblBestScore = new Label("Best score: ");
        this.lblBestScoreInput = new Label("34234");
        this.lblScore = new Label("Current score: ");
        this.lblScoreInput = new Label("1024");

        //Bottom side
        this.btnRestart = new Button();
        this.btnRestart.setGraphic(new ImageView("be/kdg/thegame_2048/views/views/img/restart.png"));
        this.btnHighScores = new Button();
        this.btnHighScores.setGraphic(new ImageView("be/kdg/thegame_2048/views/views/img/highscores.png"));

        addStyles();
    }

    private void layoutNodes() {
        //TOP (logo and scores)
        BorderPane top = new BorderPane();
        GridPane gridTop = new GridPane();
        gridTop.add(logo,0,0,1,2);
        gridTop.add(lblBestScore,1,0);
        gridTop.add(lblBestScoreInput,2,0);
        gridTop.add(lblScore,1,1);
        gridTop.add(lblScoreInput,2,1);
        gridTop.setMaxWidth(550);
        gridTop.setHalignment(lblBestScoreInput, HPos.RIGHT);
        gridTop.setHalignment(lblBestScore, HPos.RIGHT);
        gridTop.setHalignment(lblScoreInput, HPos.RIGHT);
        gridTop.setHalignment(lblScore, HPos.RIGHT);
        gridTop.setHgrow(lblBestScore, Priority.ALWAYS);
        gridTop.setMargin(logo, new Insets(OVERALL_PADDING/2, OVERALL_PADDING/2, OVERALL_PADDING/2, OVERALL_PADDING));
        gridTop.setMargin(lblBestScore, new Insets(OVERALL_PADDING/2, 0, OVERALL_PADDING/10, 0));
        gridTop.setMargin(lblBestScoreInput, new Insets(OVERALL_PADDING/2, OVERALL_PADDING, OVERALL_PADDING/10, 0));
        gridTop.setMargin(lblScore, new Insets(OVERALL_PADDING/10, 0, OVERALL_PADDING/2, 0));
        gridTop.setMargin(lblScoreInput, new Insets(OVERALL_PADDING/10, OVERALL_PADDING, OVERALL_PADDING/2, 0));
        top.setCenter(gridTop);
        top.setBackground(new Background(new BackgroundFill(Color.rgb(215,180,7), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setTop(top);

        //TOP
        BorderPane bottom = new BorderPane();
        GridPane gridBottom = new GridPane();
        gridBottom.add(btnRestart, 0,0);
        gridBottom.add(btnHighScores, 1,0);
        gridBottom.setMaxWidth(550);
        gridBottom.setHalignment(btnRestart, HPos.CENTER);
        gridTop.setHalignment(btnHighScores, HPos.CENTER);
        gridTop.setHgrow(btnRestart, Priority.ALWAYS);
        gridTop.setHgrow(btnHighScores, Priority.ALWAYS);
        gridTop.setMargin(btnRestart, new Insets(OVERALL_PADDING/2, OVERALL_PADDING/2, OVERALL_PADDING/2, OVERALL_PADDING));
        gridTop.setMargin(btnHighScores, new Insets(OVERALL_PADDING/2, OVERALL_PADDING, OVERALL_PADDING/2, OVERALL_PADDING/2));
        bottom.setCenter(gridBottom);
        bottom.setBackground(new Background(new BackgroundFill(Color.rgb(215,180,7), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setBottom(bottom);
    }
    private void addStyles() {
        lblBestScore.getStyleClass().add("inGameScore");
        lblBestScoreInput.getStyleClass().add("inGameScore");
        lblScore.getStyleClass().add("inGameScore");
        lblScoreInput.getStyleClass().add("inGameScore");

        btnHighScores.getStyleClass().add("btnHighScores");
        btnRestart.getStyleClass().add("btnRestart");
    }
}