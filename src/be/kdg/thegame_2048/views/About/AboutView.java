package be.kdg.thegame_2048.views.About;

import be.kdg.thegame_2048.views.SuperView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * @author Bryan de Ridder
 * @version 1.0 17-02-17 12:11
 */
public class AboutView extends SuperView {
    private Label lblHeader;
    private Label lblExplanation;
    private Button btnGoBack;

    public AboutView() {
        initialiseNodes();
        layoutNodes();
    }

    @Override
    protected void initialiseNodes() {
        super.initialiseNodes();
        this.lblHeader = new Label("How to play");
        this.lblExplanation = new Label("Use your arrow keys to move the tiles. \n" +
                "When two tiles with the same number touch, \nthey merge into one! " +
                "The goal of the game is to merge \n1024 and 1024 to get the final block: 2048.\n" +
                "Good luck!");
        this.lblExplanation.setAlignment(Pos.CENTER);
        this.btnGoBack = new Button();
        addStyles();
    }

    @Override
    protected void layoutNodes() {
        super.layoutNodes();
        VBox vbox = new VBox(lblHeader, lblExplanation);
        vbox.setAlignment(Pos.CENTER);
        this.setCenter(vbox);
        BorderPane bottom = new BorderPane(btnGoBack);
        this.setBottom(bottom);
    }

    private void addStyles() {
        btnGoBack.getStyleClass().add("btnGoBack");
        lblExplanation.getStyleClass().add("lblExplanation");
    }

    Button getBtnGoBack() {
        return btnGoBack;
    }
}