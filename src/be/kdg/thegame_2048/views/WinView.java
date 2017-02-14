package be.kdg.thegame_2048.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * @author Bryan de Ridder
 * @version 1.0 14-02-17 11:51
 */
public class WinView extends BorderPane {
    private Label lblWin;
    private Label lblScore;
    private Label lblScoreInput;
    private Button btnRestart;
    private Button btnContinue;
    private Button btnExit;

    public WinView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.lblWin = new Label("You win!");
        this.lblScore = new Label("Score: ");
        this.lblScoreInput = new Label("0");
        this.btnRestart = new Button();
        this.btnContinue = new Button();
        btnContinue.setRotate(180);
        this.btnExit = new Button();

        addStyles();
    }

    private void addStyles() {
        btnRestart.getStyleClass().add("btnRestart");
        btnContinue.getStyleClass().add("btnGoBack");
    }

    private void layoutNodes() {
        VBox vbox = new VBox(lblWin,lblScore,lblScoreInput,btnRestart,btnContinue);
        vbox.setAlignment(Pos.CENTER);
        this.setCenter(vbox);
    }
}