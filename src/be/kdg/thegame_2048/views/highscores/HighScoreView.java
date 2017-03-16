package be.kdg.thegame_2048.views.highscores;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import java.util.*;

/**
 * Creates a BorderPane that contains the high scores.
 * Maximum amount of players shown is 10.
 * When there are less players the high score list will always be congruent with the amount of players.
 *
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 08-02-17 18:55
 */
public final class HighScoreView extends BorderPane {
    private static final String HEADER = "2048 High Scores";
    private static final String COLUMN_HEADER_1 = "Rank";
    private static final String COLUMN_HEADER_2 = "Name";
    private static final String COLUMN_HEADER_3 = "Score";
    private static final double OVERALL_PADDING = 50;
    private static final double SCENE_WIDTH = 550;
    private static final double TEXT_SPACING = 10;
    private static final int MAX_PLAYER_AMOUNT = 10;
    private int playerAmount;
    private Label lblHighScores;
    private List<Label> lblHsRanks;
    private List<Label> lblHsNames;
    private List<Label> lblHsScores;
    private Button btnGoBack;

    private BorderPane top;

    public HighScoreView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.lblHighScores = new Label(HEADER);

        this.lblHsRanks = new ArrayList<>();
        this.lblHsNames = new ArrayList<>();
        this.lblHsScores = new ArrayList<>();

        this.lblHsRanks.add(new Label(COLUMN_HEADER_1));
        this.lblHsNames.add(new Label(COLUMN_HEADER_2));
        this.lblHsScores.add(new Label(COLUMN_HEADER_3));

        this.btnGoBack = new Button();
    }

    private void layoutNodes() {
        this.top = new BorderPane(lblHighScores);
        setMargin(lblHighScores, new Insets(OVERALL_PADDING / 2, 0, OVERALL_PADDING / 2, 0));
        this.setTop(top);
    }

    /**
     * Updates the current highscore list.
     * The high scores will show an equal amount of labels to the given amount of player names.
     * @param names contains all high score names in descending order.
     * @param scores contains all scores in descending order.
     **/
    void updateHighScore(List<String> names, List<Integer> scores) {
        setPlayerAmount(names.size());

        //MIDDLE (Actual highscores, including the column lblHsNames)
        GridPane gridPane = new GridPane();
        gridPane.setVgap(TEXT_SPACING);
        gridPane.setAlignment(Pos.TOP_CENTER);
        setMargin(gridPane, new Insets(OVERALL_PADDING));

        for (int i = 0; i < playerAmount; i++) {
            String rank = String.valueOf(i + 1);
            String playerName = names.get(i).toUpperCase().charAt(0) + names.get(i).substring(1);
            String score = String.valueOf(scores.get(i));

            lblHsRanks.add(new Label(rank));
            lblHsNames.add(new Label(playerName));
            lblHsScores.add(new Label(score));
        }

        //Player amount is increased by 1 because number 0 in the grid are the column names
        for (int i = 0; i < playerAmount + 1; i++) {
            gridPane.add(lblHsRanks.get(i), 0, i);
            gridPane.add(lblHsNames.get(i), 1, i);
            gridPane.add(lblHsScores.get(i), 2, i);

            GridPane.setMargin(lblHsRanks.get(i), new Insets(0, OVERALL_PADDING / 5, 0, 0));
            GridPane.setHalignment(lblHsScores.get(i), HPos.RIGHT);
            GridPane.setHgrow(lblHsNames.get(i), Priority.ALWAYS);
        }

        BorderPane middle = new BorderPane(gridPane);
        BorderPane bottom = new BorderPane(btnGoBack);

        VBox vBox = new VBox(middle, bottom);
        vBox.setMaxWidth(SCENE_WIDTH-OVERALL_PADDING*2);

        this.setCenter(vBox);
        addStyles();
    }

    private void setPlayerAmount(int amount) {
        if (amount < MAX_PLAYER_AMOUNT) {
            playerAmount = amount;
        } else {
            playerAmount = MAX_PLAYER_AMOUNT;
        }
    }

    /**
     * Adds custom css selector to each individual node.
     **/
    private void addStyles() {
        top.getStyleClass().add("darkerBG");
        lblHighScores.getStyleClass().add("hsHeader");
        lblHsRanks.get(0).getStyleClass().add("hsColumnNames");
        lblHsNames.get(0).getStyleClass().add("hsColumnNames");
        lblHsScores.get(0).getStyleClass().add("hsColumnNames");
        btnGoBack.getStyleClass().add("btnGoBack");
        for (int i = 1; i <= playerAmount; i++) {
            lblHsRanks.get(i).getStyleClass().add("hsColumnFillRanks");
            lblHsNames.get(i).getStyleClass().add("hsColumnFill");
            lblHsScores.get(i).getStyleClass().add("hsColumnFill");
        }
    }

    void highlightPlayer(String naam) {
        String upperNaam = naam.toUpperCase().charAt(0) + naam.substring(1);
        for (int i = 0; i <= playerAmount; i++) {
            if (upperNaam.equals(lblHsNames.get(i).getText())) {
                lblHsRanks.get(i).getStyleClass().add("hsActivePlayer");
                lblHsNames.get(i).getStyleClass().add("hsActivePlayer");
                lblHsScores.get(i).getStyleClass().add("hsActivePlayer");
            }
        }
    }

    Button getBtnGoBack() {
        return btnGoBack;
    }
}
