package be.kdg.thegame_2048.views.views;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
    private Button goBack;

    public HighScoreView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.lblHighScores = new Label("2048 High Scores");
        lblHighScores.setGraphic(new ImageView("be/kdg/thegame_2048/views/views/img/cup.png"));
        lblHighScores.setGraphicTextGap(10);
        lblHighScores.getStyleClass().add("HighScoreHeader");

        //Initialize highscore
        this.ranks = new ArrayList<>();
        this.names = new ArrayList<>();
        this.scores = new ArrayList<>();

        //Adds the Column names
        ranks.add(new Label("Rank"));
        names.add(new Label("Name"));
        scores.add(new Label("Score"));
        ranks.get(0).getStyleClass().add("hsColumnNames");
        names.get(0).getStyleClass().add("hsColumnNames");
        scores.get(0).getStyleClass().add("hsColumnNames");

        //Creates the list
        for (int i = 0; i < playerAmount; i++) {
            ranks.add(new Label(i + 1 + ""));
            names.add(new Label("Naam"));
            scores.add(new Label("0"));
            ranks.get(i+1).getStyleClass().add("hsColumnFillRanks");
            names.get(i+1).getStyleClass().add("hsColumnFill");
            scores.get(i+1).getStyleClass().add("hsColumnFill");
        }

        //back button
        goBack = new Button();
        goBack.setGraphic(new ImageView("be/kdg/thegame_2048/views/views/img/left-arrow.png"));
        goBack.getStyleClass().add("backButton");
    }

    private void layoutNodes() {
        //TOP (high score label)
        BorderPane top = new BorderPane();
        top.setCenter(lblHighScores);
        this.setTop(top);
        top.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        top.setMargin(lblHighScores, new Insets(OVERALL_PADDING/2, 0, OVERALL_PADDING/2, 0));

        //MIDDLE (scores)
        BorderPane middle = new BorderPane();
        GridPane grid = new GridPane();
        for (int i = 0; i < playerAmount + 1; i++) {
            grid.add(ranks.get(i), 0, i);
            GridPane.setMargin(ranks.get(i), new Insets(0, OVERALL_PADDING/2, 0, 0));
            grid.add(names.get(i), 1, i);
            GridPane.setMargin(names.get(i), new Insets(0, OVERALL_PADDING*2, 0, 0));
            grid.add(scores.get(i), 2, i);
            GridPane.setHalignment(scores.get(i), HPos.RIGHT);
        }
        grid.setVgap(20);
        grid.setAlignment(Pos.TOP_CENTER);
        middle.setCenter(grid);
        middle.setPadding(new Insets(OVERALL_PADDING, OVERALL_PADDING, OVERALL_PADDING/2, OVERALL_PADDING));

        //BOTTOM (back button)
        BorderPane bottom = new BorderPane();
        bottom.setCenter(goBack);
        bottom.setAlignment(goBack, Pos.TOP_CENTER);

        VBox vBox = new VBox(middle, bottom);
        this.setCenter(vBox);
    }
}
