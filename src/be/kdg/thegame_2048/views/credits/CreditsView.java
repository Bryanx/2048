package be.kdg.thegame_2048.views.credits;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;

/**
 * A BorderPane that contains the credits.
 *
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
        this.lblHeader = new Label("Credits");
        this.lblCredits = new Label(
                        "This project was made by\n" +
                        "Bryan de Ridder and Jarne Van Aerde.\n" +
                        "Karel De Grote Hogeschool Antwerp\n" +
                        "2016-2017\n\n" +
                        "Fun fact:\n" +
                        "The original game was made by Gabriele Cirulli\n" +
                        "in JavaScript in just 1 weekend!\n");
        this.lblCredits.setTextAlignment(TextAlignment.CENTER);
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

    /**
     * Adds custom css selector to each individual node.
     **/
    private void addStyles() {
        btnGoBack.getStyleClass().add("btnGoBack");
        lblHeader.getStyleClass().add("lblHeader");
        lblCredits.getStyleClass().add("hsColumnFill");
    }

    Button getBtnGoBack() {
        return btnGoBack;
    }
}