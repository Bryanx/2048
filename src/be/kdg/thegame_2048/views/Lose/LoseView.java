package be.kdg.thegame_2048.views.Lose;

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
public final class LoseView extends BorderPane {
    private Label lblLose;
    private Label lblScore;
    private Label lblScoreInput;
    private Button btnRestart;
    private Button btnExit;

    public LoseView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.lblLose = new Label("You lose!");
        this.lblScore = new Label("Score: ");
        this.lblScoreInput = new Label("0");
        this.btnRestart = new Button();
        this.btnExit = new Button();

        addStyles();
    }

    private void addStyles() {
        btnRestart.getStyleClass().add("btnRestart");
        btnExit.getStyleClass().add("btnExit");
    }

    private void layoutNodes() {
        HBox hbox1 = new HBox(lblScore,lblScoreInput);
        HBox hBbuttons = new HBox(btnRestart,btnExit);
        VBox vbox = new VBox(lblLose,hbox1,hBbuttons);
        hbox1.setAlignment(Pos.CENTER);
        hBbuttons.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(25);
        this.setCenter(vbox);
    }

    Button getBtnExit() {
        return btnExit;
    }

    Label getLblScoreInput() {
        return lblScoreInput;
    }

    Button getBtnRestart() {
        return btnRestart;
    }
}