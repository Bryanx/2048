package be.kdg.thegame_2048.views;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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
    private Button[][] btnSections;
    private ImageView[] imgBlocks;

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
        //Create imgBlocks
        String path = "be/kdg/thegame_2048/views/img/blocks/block";
        String[] blockNumbers = {"E","2","4","8","16","32","64","128","256","512","1024","2048"};
        this.imgBlocks = new ImageView[12];
        for (int i = 0; i <= 11; i++) {
            this.imgBlocks[i] = new ImageView(path + blockNumbers[i] + ".png");
        }
        //create btnSections
        //to fill btnSections: btnSections[i][j].setGraphic()
        this.btnSections = new Button[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.btnSections[i][j] = new Button();
                this.btnSections[i][j].setMinSize(100, 100);
                this.btnSections[i][j].setMaxSize(100, 100);
            }
        }

        //Bottom side
        this.btnRestart = new Button();
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
        gridTop.add(logo, 0, 0, 1, 2);
        gridTop.add(vboxScore, 1, 0);
        gridTop.add(vboxScoreInput, 2, 0);
        gridTop.setMaxWidth(SCENE_WIDTH - OVERALL_PADDING * 2);
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
        top.setBackground(new Background(new BackgroundFill(Color.rgb(215, 180, 7), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setTop(top);

        //MIDDLE
        BorderPane middle = new BorderPane();
        BorderPane playground = new BorderPane();
        GridPane sectionGrid = new GridPane();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sectionGrid.add(this.btnSections[i][j], i, j);
            }
        }
        sectionGrid.setVgap(10);
        sectionGrid.setHgap(10);
        sectionGrid.setAlignment(Pos.CENTER);
        playground.setMinSize(450, 450);
        playground.setMaxSize(450, 450);
        playground.setCenter(sectionGrid);
        playground.setBackground(new Background(new BackgroundFill(Color.web("#bbada0"), new CornerRadii(5), Insets.EMPTY)));
        middle.setCenter(playground);
        this.setCenter(middle);

        //BOTTOM
        BorderPane bottom = new BorderPane();
        GridPane gridBottom = new GridPane();
        gridBottom.add(btnRestart, 0, 0);
        gridBottom.add(btnHighScores, 1, 0);
        gridBottom.setMaxWidth(SCENE_WIDTH - OVERALL_PADDING * 2);
        gridBottom.setMinHeight(HEIGHT_OUTER_PANELS);
        Button[] buttons = {btnHighScores, btnRestart};
        for (Button btn : buttons) {
            GridPane.setVgrow(btn, Priority.ALWAYS);
            GridPane.setHgrow(btn, Priority.ALWAYS);
            GridPane.setValignment(btn, VPos.CENTER);
            GridPane.setHalignment(btn, HPos.CENTER);
        }
        bottom.setCenter(gridBottom);
        bottom.setBackground(new Background(new BackgroundFill(Color.rgb(215, 180, 7), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setBottom(bottom);
    }

    private void addStyles() {
        lblBestScore.getStyleClass().add("inGameScore");
        lblBestScoreInput.getStyleClass().add("inGameScore");
        lblScore.getStyleClass().add("inGameScore");
        lblScoreInput.getStyleClass().add("inGameScore");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.btnSections[i][j].getStyleClass().add("sections");
            }
        }

        btnHighScores.getStyleClass().add("btnHighScores");
        btnRestart.getStyleClass().add("btnRestart");
    }

    Button[][] getBtnSections() {
        return btnSections;
    }

    ImageView getBlock(int index) {
        return imgBlocks[index];
    }
}