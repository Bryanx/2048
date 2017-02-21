package be.kdg.thegame_2048.views.HighScores;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.*;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 08-02-17 18:55
 */
public final class HighScoreView extends BorderPane {
    private static final double OVERALL_PADDING = 50;
    private static final double SCENE_WIDTH = 550;
    private int playerAmount;
    private Label lblHighScores;
    //lblHsNames.get(1) the name of rank 1
    //lblHsScores.get(1) is the score of rank 1
    private List<Label> lblHsRanks;
    private List<Label> lblHsNames;
    private List<Label> lblHsScores;

    private Button goBack;

    public HighScoreView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //Top side of the highscores
        this.lblHighScores = new Label("2048 High Scores");

        //Initialize highscorelist
        this.lblHsRanks = new ArrayList<>();
        this.lblHsNames = new ArrayList<>();
        this.lblHsScores = new ArrayList<>();

        //Adds the Column lblHsNames to the highscorelist
        lblHsRanks.add(new Label("Rank"));
        lblHsNames.add(new Label("Name"));
        lblHsScores.add(new Label("Score"));



        //back button
        goBack = new Button();
        addStyles();
    }

    private void layoutNodes() {
        //TOP (2048 High Scores)
        BorderPane top = new BorderPane(lblHighScores);
        top.setBackground(new Background(new BackgroundFill(Color.rgb(215,180,7), CornerRadii.EMPTY, Insets.EMPTY)));
        setMargin(lblHighScores, new Insets(OVERALL_PADDING / 2, 0, OVERALL_PADDING / 2, 0));
        this.setTop(top);
    }

    private void addStyles() {
        lblHighScores.getStyleClass().add("hsHeader");
        lblHsRanks.get(0).getStyleClass().add("hsColumnNames");
        lblHsNames.get(0).getStyleClass().add("hsColumnNames");
        lblHsScores.get(0).getStyleClass().add("hsColumnNames");
        goBack.getStyleClass().add("btnGoBack");
    }

    void updateHighScore(List<String> names, List<Integer> scores) {
        //Adds the players to the highscorelist (max=10)
        //Number 1 on the list is also rank 1 of the highscores
        if (names.size() < 10) {
            playerAmount = names.size();
        } else {
            playerAmount = 10;
        }

        for (int i = 0; i < playerAmount; i++) {
            lblHsRanks.add(new Label(i + 1 + "")); //Rank
            lblHsNames.add(new Label(names.get(i).toUpperCase().charAt(0) + names.get(i).substring(1))); //Name
            lblHsScores.add(new Label(String.valueOf(scores.get(i)))); //Score
        }
        for (int i = 0; i < playerAmount; i++) {
            lblHsRanks.get(i + 1).getStyleClass().add("hsColumnFillRanks");
            lblHsNames.get(i + 1).getStyleClass().add("hsColumnFill");
            lblHsScores.get(i + 1).getStyleClass().add("hsColumnFill");
        }

        //MIDDLE (Actual highscores, including the column lblHsNames)
        GridPane grid = new GridPane();
        for (int i = 0; i < playerAmount + 1; i++) {
            grid.add(lblHsRanks.get(i), 0, i);
            grid.add(lblHsNames.get(i), 1, i);
            grid.add(lblHsScores.get(i), 2, i);
            GridPane.setMargin(lblHsRanks.get(i), new Insets(0, OVERALL_PADDING / 5, 0, 0));
            GridPane.setHalignment(lblHsScores.get(i), HPos.RIGHT);
            GridPane.setHgrow(lblHsNames.get(i), Priority.ALWAYS);
        }
        grid.setVgap(10);
        grid.setAlignment(Pos.TOP_CENTER);
        setMargin(grid, new Insets(OVERALL_PADDING));

        //BOTTOM (back button)
        VBox vBox = new VBox(new BorderPane(grid), new BorderPane(goBack));
        vBox.setMaxWidth(SCENE_WIDTH-OVERALL_PADDING*2);
        this.setCenter(vBox);
    }

    void highlightPlayer(String naam) {
        //highlights the active player in the highscore list
        String upperNaam = naam.toUpperCase().charAt(0) + naam.substring(1);
        for (int i = 0; i < playerAmount; i++) {
            if (upperNaam.equals(lblHsNames.get(i).getText())) {
                lblHsRanks.get(i).getStyleClass().add("hsActivePlayer");
                lblHsNames.get(i).getStyleClass().add("hsActivePlayer");
                lblHsScores.get(i).getStyleClass().add("hsActivePlayer");
            }
        }
    }

    Button getGoBack() {
        return goBack;
    }
}
