package be.kdg.thegame_2048.views.Settings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Jarne van Aerde, Bryan de Ridder
 * @version 1.0 12/02/2017 19:40
 */

public class SettingsView extends BorderPane {
    private static final double OVERALL_PADDING = 50;
    private Button btnGoBack;
    private Label lblHeader;
    private Slider slBlockValue;

    public SettingsView() {
        initialiseNodes();
        layoutNodes();
    }

    protected void initialiseNodes() {
        //header
        this.lblHeader = new Label("Settings");

        this.slBlockValue = new Slider(1,10,1);



        this.btnGoBack = new Button();
        addStyles();
    }

    protected void layoutNodes() {
        this.setTop(new BorderPane(lblHeader));

        BorderPane bottom = new BorderPane(btnGoBack);
        this.setBottom(bottom);
        this.setPadding(new Insets(OVERALL_PADDING));
    }

    private void addStyles() {
        btnGoBack.getStyleClass().add("btnGoBack");
        lblHeader.getStyleClass().add("lblHeader");
    }

    Button getBtnGoBack() {
        return btnGoBack;
    }
}