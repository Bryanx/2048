package be.kdg.thegame_2048.views;

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
public final class WinView extends BorderPane {
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
        this.btnExit = new Button();

        addStyles();
    }

    private void addStyles() {
        btnRestart.getStyleClass().add("btnRestart");
        btnContinue.getStyleClass().add("btnGoBack");
        btnExit.getStyleClass().add("btnExit");
    }

    private void layoutNodes() {
        HBox hbox1 = new HBox(lblScore,lblScoreInput);
        HBox hBbuttons = new HBox(btnRestart,btnContinue,btnExit);
        VBox vbox = new VBox(lblWin,hbox1,hBbuttons);
        hbox1.setAlignment(Pos.CENTER);
        hBbuttons.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(25);
        this.setCenter(vbox);
    }
}