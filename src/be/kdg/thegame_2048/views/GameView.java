package be.kdg.thegame_2048.views;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Bryan de Ridder
 * @version 1.0 12-02-17 18:49
 */
public final class GameView extends BorderPane {
    private static final double OVERALL_PADDING = 50;
    private static final double SCENE_WIDTH = 550;
    private static final double HEIGHT_OUTER_PANELS = 100;
    private ImageView logo;
    private Label lblBestScore;
    private Label lblBestScoreInput;
    private Label lblScore;
    private Label lblScoreInput;

    //middle
//    private Canvas playground;
    private Button[][] blocks;

    //bottom
    private Button btnRestart;
    private Button btnHighScores;

    public GameView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //Top side
        this.logo = new ImageView("be/kdg/thegame_2048/views/img/logo-2048.png");
        this.lblBestScore = new Label("Best score: ");
        this.lblBestScoreInput = new Label("0");
        this.lblScore = new Label("Current score: ");
        this.lblScoreInput = new Label("0");

        //Middle side
//        this.playground = new Canvas(450,450);
//        final GraphicsContext gc = this.playground.getGraphicsContext2D();
//        gc.setFill(Color.rgb(215,180,7));
//        gc.fillRoundRect(0.0,0.0,450,450,10,10);

        this.blocks = new Button[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.blocks[i][j] = new Button();
                this.blocks[i][j].setText(i + " " + j);
                this.blocks[i][j].setMinSize(100,100);
                this.blocks[i][j].setMaxSize(100,100);
            }
        }

        //Bottom side
        this.btnRestart = new Button();
        this.btnRestart.setGraphic(new ImageView("be/kdg/thegame_2048/views/img/restart.png"));
        this.btnHighScores = new Button();
        this.btnHighScores.setGraphic(new ImageView("be/kdg/thegame_2048/views/img/highscores.png"));

        //Add CSS Styles
        addStyles();
    }

    private void layoutNodes() {
        //TOP (logo and scores)
        BorderPane top = new BorderPane();
        GridPane gridTop = new GridPane();
        VBox vboxScore = new VBox(lblBestScore, lblScore);
        VBox vboxScoreInput = new VBox(lblBestScoreInput, lblScoreInput);
        gridTop.add(logo,0,0,1,2);
        gridTop.add(vboxScore,1,0);
        gridTop.add(vboxScoreInput,2,0);
        gridTop.setMaxWidth(SCENE_WIDTH -OVERALL_PADDING*2);
        gridTop.setMinHeight(HEIGHT_OUTER_PANELS);
        VBox[] vBoxes = {vboxScoreInput, vboxScore};
        for (VBox vBox : vBoxes) {
            GridPane.setVgrow(vBox, Priority.ALWAYS);
            GridPane.setHalignment(vBox, HPos.RIGHT);
            GridPane.setValignment(vBox, VPos.CENTER);
            vBox.setAlignment(Pos.CENTER_RIGHT);
        }
        GridPane.setHgrow(vboxScore, Priority.ALWAYS);
        top.setCenter(gridTop);
        top.setBackground(new Background(new BackgroundFill(Color.rgb(215,180,7), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setTop(top);

        //MIDDLE
        BorderPane middle = new BorderPane();
        GridPane gridSections = new GridPane();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gridSections.add(this.blocks[i][j],i,j);
            }
        }
        gridSections.setVgap(10);
        gridSections.setHgap(10);
        gridSections.setAlignment(Pos.CENTER);
        middle.setCenter(gridSections);
//        middle.setCenter(playground);
        this.setCenter(middle);

        //BOTTOM
        BorderPane bottom = new BorderPane();
        GridPane gridBottom = new GridPane();
        gridBottom.add(btnRestart, 0,0);
        gridBottom.add(btnHighScores, 1,0);
        gridBottom.setMaxWidth(SCENE_WIDTH -OVERALL_PADDING*2);
        gridBottom.setMinHeight(HEIGHT_OUTER_PANELS);
        Button[] buttons = {btnHighScores, btnRestart};
        for (Button btn : buttons) {
            GridPane.setVgrow(btn, Priority.ALWAYS);
            GridPane.setHgrow(btn, Priority.ALWAYS);
            GridPane.setValignment(btn, VPos.CENTER);
            GridPane.setHalignment(btn, HPos.CENTER);
        }
        bottom.setCenter(gridBottom);
        bottom.setBackground(new Background(new BackgroundFill(Color.rgb(215,180,7), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setBottom(bottom);
    }
    private void addStyles() {
        lblBestScore.getStyleClass().add("inGameScore");
        lblBestScoreInput.getStyleClass().add("inGameScore");
        lblScore.getStyleClass().add("inGameScore");
        lblScoreInput.getStyleClass().add("inGameScore");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.blocks[i][j].getStyleClass().add("blocks");
            }
        }

        btnHighScores.getStyleClass().add("btnHighScores");
        btnRestart.getStyleClass().add("btnRestart");
    }
}