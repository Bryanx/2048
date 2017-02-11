package be.kdg.thegame_2048.views.views;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Bryan de Ridder
 * @version 1.0 08-02-17 18:55
 */
public class HighScoreView extends BorderPane {
    private static final double OVERALL_PADDING = 50;
    private int playerAmount = 8;
    private Label lblHighScores;
    private List<Label> ranks;
    private List<Label> names;
    private List<Label> scores;

    public HighScoreView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.lblHighScores = new Label("2048 High Scores");
        lblHighScores.setGraphic(new ImageView("be/kdg/thegame_2048/views/views/img/cup.png"));

        //Initialize highscore
        this.ranks = new ArrayList<>();
        this.names = new ArrayList<>();
        this.scores = new ArrayList<>();

        //Adds the Column names
        ranks.add(new Label("RANK"));
        names.add(new Label("NAME"));
        scores.add(new Label("SCORE"));

        //Creates the list
        for (int i = 0; i < playerAmount; i++) {
            ranks.add(new Label(i + 1 + "."));
            names.add(new Label("naam"));
            scores.add(new Label("score"));
        }
    }

    private void layoutNodes() {
        //TOP
        this.setTop(lblHighScores);

        //MIDDLE
        GridPane grid = new GridPane();
        for (int i = 0; i < playerAmount + 1; i++) {
            grid.add(ranks.get(i), 0, i);
            GridPane.setMargin(ranks.get(i), new Insets(0, 15, 0, 0));
            grid.add(names.get(i), 1, i);
            GridPane.setMargin(names.get(i), new Insets(0, 80, 0, 0));
            grid.add(scores.get(i), 2, i);
            GridPane.setHalignment(scores.get(i), HPos.RIGHT);
        }
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);
        this.setCenter(grid);
        this.setPadding(new Insets(OVERALL_PADDING));
    }
}
