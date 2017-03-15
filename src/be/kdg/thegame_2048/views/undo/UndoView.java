package be.kdg.thegame_2048.views.undo;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;


/**
 * Creates a BorderPane that contains an undo warning message for the player.
 *
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 08-03-17 14:39
 */
public class UndoView extends BorderPane {
    private static final Paint RECTANGLE_BG_COLOR = Color.WHITE;
    private static final double RECT_ARC_SIZE = 20;
    private static final double TEXT_SPACING = 25;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final String HEADER = "Warning";
    private static final String MESSAGE = "Are you sure you want to undo\nyour last move? " +
            "Your score will no longer\nbe added to the highscores.";
    private Label lblMessage;
    private Label lblHeader;
    private Rectangle rect;
    private Button btnAccept;
    private Button btnCancel;

    public UndoView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.btnAccept = new Button();
        this.btnCancel = new Button();
        this.lblHeader = new Label(HEADER);

        this.lblMessage = new Label(MESSAGE);
        this.lblMessage.setMaxSize(WIDTH,HEIGHT);
        this.lblMessage.setTextAlignment(TextAlignment.CENTER);
        this.lblMessage.setAlignment(Pos.CENTER);

        this.rect = new Rectangle();
        this.rect.setWidth(WIDTH);
        this.rect.setHeight(HEIGHT);
        this.rect.setFill(RECTANGLE_BG_COLOR);
        this.rect.setArcWidth(RECT_ARC_SIZE);
        this.rect.setArcHeight(RECT_ARC_SIZE);

        addStyles();
    }

    private void layoutNodes() {
        HBox hbox = new HBox(btnAccept, btnCancel);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(lblHeader, lblMessage, hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(TEXT_SPACING);

        StackPane stack = new StackPane(rect, vbox);
        this.setCenter(stack);
    }

    /**
     * Adds custom css selector to each individual node.
     **/
    private void addStyles() {
        btnAccept.getStyleClass().add("btnAccept");
        btnCancel.getStyleClass().add("btnCancel");
        lblMessage.getStyleClass().add("hsColumnFill");
    }

    Button getBtnAccept() {
        return btnAccept;
    }
    Button getBtnCancel() {
        return btnCancel;
    }
}
