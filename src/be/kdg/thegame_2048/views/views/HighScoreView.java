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

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Bryan de Ridder
 * @version 1.0 08-02-17 18:55
 */
public class HighScoreView extends BorderPane {
    private static final Paint BG_COLOR = Color.rgb(236, 196, 0);
    private static final double OVERALL_PADDING = 50;
    private int playerAmount;
    private Label lblHighScores;
    private List<Label> ranks;
    private List<Label> names;
    private List<Label> scores;
    private List<String> updatedScores;

    public HighScoreView() {
    }

    public void displayCurrentHighScore(String[] namen, int[] bestScores) {
        this.playerAmount = namen.length;

        updatedScores = new ArrayList<>();
        for (int i = 0; i < playerAmount; i++) {
            String zin = namen[i] + "," + bestScores[i] + ";";
            updatedScores.add(i, zin);
        }

        Collections.sort(updatedScores, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String score1 = o1.split(",")[1].replace(";","");
                String score2 = o2.split(",")[1].replace(";","");
                return Integer.parseInt(score2) - Integer.parseInt(score1);
            }
        });

        System.out.println(updatedScores.get(0).split(",")[0]);

        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
//        this.lblHighScores = lblHighScores;

        this.ranks = new ArrayList<>();
        this.names = new ArrayList<>();
        this.scores = new ArrayList<>();

        ranks.add(new Label("RANK"));
        names.add(new Label("NAME"));
        scores.add(new Label("SCORE"));

        for (int i = 0; i < playerAmount; i++) {
            ranks.add(new Label(i + 1 + "."));
            names.add(new Label(updatedScores.get(i).split(",")[0]));
            scores.add(new Label(updatedScores.get(i).split(",")[1].replace(";","")));
        }
    }

    private void layoutNodes() {
        GridPane grid = new GridPane();

        for (int i = 0; i < playerAmount + 1; i++) {
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
