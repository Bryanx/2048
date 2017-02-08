package be.kdg.thegame_2048.views.views;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bryan de Ridder
 * @version 1.0 08-02-17 18:55
 */
public class HighScoreView extends BorderPane {
    private static final int TOTAL_RANKS = 8;
    private static final Paint BG_COLOR = Color.rgb(236, 196, 0);
    private static final double OVERALL_PADDING = 50;
    private Label lblHighScores;
    private Label lblColumnNameRank;
    private Label lblColumnNameName;
    private Label lblColumnNameScore;
    private List<Label> ranks;
    private List<Label> names;
    private List<Label> scores;

    public HighScoreView() {
        initialiseNodes();
        updateView();
    }

    private void initialiseNodes() {
//        this.lblHighScores = lblHighScores;
        this.lblColumnNameRank = new Label("RANK");
        this.lblColumnNameName = new Label("NAME");
        this.lblColumnNameScore = new Label("SCORE");
        this.lblColumnNameRank.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        this.lblColumnNameRank.setTextFill(Color.rgb(0, 100, 100));
        this.lblColumnNameName.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        this.lblColumnNameName.setTextFill(Color.rgb(0, 100, 100));
        this.lblColumnNameScore.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        this.lblColumnNameScore.setTextFill(Color.rgb(0, 100, 100));

        this.ranks = new ArrayList<>();
        this.names = new ArrayList<>();
        this.scores = new ArrayList<>();

        //Updating the hiscore list, the information should be put here:
        for (int i = 0; i <= TOTAL_RANKS + 1; i++) {
            ranks.add(new Label(i + "."));
            names.add(new Label("Michiel"));
            scores.add(new Label(Integer.toString(i * i * i * i * i * i)));
        }

        for (int i = 0; i <= TOTAL_RANKS + 1; i++) {
            ranks.get(i).setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 25));
            ranks.get(i).setTextFill(Color.rgb(0, 100, 100));
            names.get(i).setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 25));
            names.get(i).setTextFill(Color.rgb(0, 100, 100));
            scores.get(i).setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 25));
            scores.get(i).setTextFill(Color.rgb(0, 100, 100));
        }
    }

    private void updateView() {
        GridPane grid = new GridPane();
        grid.add(lblColumnNameRank, 0, 0);
        GridPane.setMargin(lblColumnNameRank, new Insets(0, 15, 0, 0));
        grid.add(lblColumnNameName, 1, 0);
        grid.add(lblColumnNameScore, 2, 0);
        GridPane.setHalignment(lblColumnNameScore, HPos.RIGHT);

        for (int i = 1; i < TOTAL_RANKS + 1; i++) {
            grid.add(ranks.get(i), 0, i);
            GridPane.setMargin(ranks.get(i), new Insets(0, 15, 0, 0));
            grid.add(names.get(i), 1, i);
            GridPane.setMargin(names.get(i), new Insets(0, 80, 0, 0));
            grid.add(scores.get(i), 2, i);
            GridPane.setHalignment(scores.get(i), HPos.RIGHT);
        }
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        this.setCenter(grid);

        this.setPadding(new Insets(OVERALL_PADDING));
        this.setBackground(new Background(new BackgroundFill(BG_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
