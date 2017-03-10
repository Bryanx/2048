package be.kdg.thegame_2048.views.credits;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

/**
 * @author Jarne van Aerde, Bryan de Ridder
 * @version 1.0 12/02/2017 19:40
 */

public class CreditsView extends BorderPane {
    private static final double OVERALL_PADDING = 50;
    private Button btnGoBack;
    private Label lblHeader;
    private Label lblCredits;

    public CreditsView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //header
        this.lblHeader = new Label("credits");

        this.lblCredits = new Label("Made by Bryan and Jarne");



        this.btnGoBack = new Button();
        addStyles();
    }

    private void layoutNodes() {
        this.setTop(new BorderPane(lblHeader));

        this.setCenter(lblCredits);

        BorderPane bottom = new BorderPane(btnGoBack);
        this.setBottom(bottom);
        this.setPadding(new Insets(OVERALL_PADDING));
    }

    private void addStyles() {
        btnGoBack.getStyleClass().add("btnGoBack");
        lblHeader.getStyleClass().add("lblHeader");
        lblCredits.getStyleClass().add("hsColumnFill");
    }

    Button getBtnGoBack() {
        return btnGoBack;
    }
}