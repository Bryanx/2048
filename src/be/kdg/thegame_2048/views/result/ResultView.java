package be.kdg.thegame_2048.views.result;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Creates a BorderPane that contains a win or lose situation.
 * lblResult needs to be set with win or lose.
 * lblFinalScore needs to be set with the final score.
 *
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 14-02-17 11:51
 */
public final class ResultView extends BorderPane {
    private static final double TEXT_SPACING = 25;
    private Label lblResult;
    private Label lblScore;
    private Label lblFinalScore;
    private Button btnRestart;
    private Button btnContinue;
    private Button btnExit;

    private HBox hBoxButtons;

    public ResultView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.lblResult = new Label("You win/lose!");
        this.lblScore = new Label("Final score: ");
        this.lblFinalScore = new Label("0");
        this.btnRestart = new Button();
        this.btnContinue = new Button();
        this.btnExit = new Button();

        addStyles();
    }

    private void layoutNodes() {
        HBox hbox = new HBox(lblScore, lblFinalScore);
        hbox.setAlignment(Pos.CENTER);

        this.hBoxButtons = new HBox(btnRestart, btnExit);
        this.hBoxButtons.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(lblResult, hbox, hBoxButtons);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(TEXT_SPACING);
        this.setCenter(vbox);
    }

    /**
     * Adds custom css selector to each individual node.
     **/
    private void addStyles() {
        lblResult.getStyleClass().add("lblResult");
        lblScore.getStyleClass().add("lblResultScore");
        lblFinalScore.getStyleClass().add("lblResultScore");
        btnRestart.getStyleClass().add("btnRestart");
        btnContinue.getStyleClass().add("btnGoBack");
        btnExit.getStyleClass().add("btnExit");
    }

    /**
     * Adds a continue button to the resultView.
     * This method should only be used if the player has won the game.
     **/
    void addContinueBtn() {
        hBoxButtons.getChildren().add(0,btnContinue);
    }

    Label getLblResult() {
        return lblResult;
    }
    Label getLblFinalScore() {
        return lblFinalScore;
    }
    Button getBtnContinue() {
        return btnContinue;
    }
    Button getBtnRestart() {
        return btnRestart;
    }
    Button getBtnExit() {
        return btnExit;
    }
}
