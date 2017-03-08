package be.kdg.thegame_2048.views.Alert;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;


/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 08-03-17 14:39
 */
public class AlertView extends BorderPane {
    private final int WIDTH = 400;
    private final int HEIGHT = 300;
    private Label lblMessage;
    private Label lblHeader;
    private Rectangle box;
    private Button btnAccept;
    private Button btnCancel;

    public AlertView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.btnAccept = new Button();
        this.btnCancel = new Button();
        this.lblHeader = new Label("Warning");

        this.lblMessage = new Label("");
        this.lblMessage.setMaxSize(WIDTH-20,HEIGHT-20);
        this.lblMessage.setTextAlignment(TextAlignment.CENTER);

        this.box = new Rectangle(WIDTH,HEIGHT, Color.WHITE);
        this.box.setOpacity(50);
        this.box.setArcWidth(20);
        this.box.setArcHeight(20);

        addStyles();
    }

    private void layoutNodes() {
        HBox hbox1 = new HBox(btnAccept, btnCancel);
        VBox vbox = new VBox(lblHeader, lblMessage, hbox1);
        hbox1.setAlignment(Pos.CENTER);
        lblMessage.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(25);
        StackPane stack = new StackPane(box, vbox);
        this.setCenter(stack);
    }

    private void addStyles() {
        btnAccept.getStyleClass().add("btnAccept");
        btnCancel.getStyleClass().add("btnCancel");
        lblMessage.getStyleClass().add("hsColumnFill");
    }

    public Label getLblMessage() {
        return lblMessage;
    }

    Button getBtnAccept() {
        return btnAccept;
    }

    Button getBtnCancel() {
        return btnCancel;
    }
}
