package be.kdg.thegame_2048.views.result;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 14-02-17 11:51
 */
public final class ResultView extends BorderPane {
    private Label lblResult;
    private Label lblScore;
    private Label lblScoreInput;
    private Button btnRestart;
    private Button btnContinue;
    private Button btnExit;

    private HBox hBbuttons;

    public ResultView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.lblResult = new Label("You win/lose!");
        this.lblScore = new Label("Final score: ");
        this.lblScoreInput = new Label("0");
        this.btnRestart = new Button();
        this.btnContinue = new Button();
        this.btnExit = new Button();

        addStyles();
    }
    /**
     * Adds custom css selector to each individual node.
     **/
    private void addStyles() {
        lblResult.getStyleClass().add("lblResult");
        lblScore.getStyleClass().add("lblResultScore");
        lblScoreInput.getStyleClass().add("lblResultScore");
        btnRestart.getStyleClass().add("btnRestart");
        btnContinue.getStyleClass().add("btnGoBack");
        btnExit.getStyleClass().add("btnExit");
    }

    private void layoutNodes() {
        HBox hbox1 = new HBox(lblScore, lblScoreInput);
        this.hBbuttons = new HBox(btnRestart, btnExit);
        VBox vbox = new VBox(lblResult, hbox1, hBbuttons);
        hbox1.setAlignment(Pos.CENTER);
        hBbuttons.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(25);
        this.setCenter(vbox);
    }

    /**
     * Returns a Label for putting in the result.
     **/
    void setLblResult(String result) {
        this.lblResult.setText(result);
    }

    /**
     * Returns a Label for putting in the score.
     **/
    Label getLblScoreInput() {
        return lblScoreInput;
    }

    /**
     * Returns a continue button.
     **/
    Button getBtnContinue() {
        return btnContinue;
    }

    /**
     * Returns a restart button.
     **/
    Button getBtnRestart() {
        return btnRestart;
    }

    /**
     * Returns an exit button.
     **/
    Button getBtnExit() {
        return btnExit;
    }

    /**
     * Adds a continue button to the resultView.
     * This method should only be used if the player has won the game.
     **/
    void addContinueBtn() {
        hBbuttons.getChildren().add(0,btnContinue);
    }
}
