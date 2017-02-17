package be.kdg.thegame_2048.views;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * @author Bryan de Ridder
 * @version 1.0 17-02-17 12:11
 */
public class AboutView extends SuperView {
    private Label lblHeader;
    private Label lblText;

    public AboutView() {
        initialiseNodes();
        layoutNodes();
    }

    @Override
    protected void initialiseNodes() {
        super.initialiseNodes();
        this.lblHeader = new Label("How to play");
        this.lblText = new Label("Use your arrow keys to move the tiles. " +
                "When two tiles with the same number touch, they merge into one!");
    }

    @Override
    protected void layoutNodes() {
        super.layoutNodes();
        VBox vbox = new VBox(lblHeader, lblText);
        this.setCenter(vbox);
    }
}