package be.kdg.thegame_2048.views.newOrExistingPlayer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * Creates a BorderPane with a textfield for players to enter their name.
 * This class can be used for both existing players and new players.
 * lblMessage needs to be set.
 * There's also an optional error message, it should be made visible if used.
 *
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 08-02-17 17:03
 */
public final class PlayerView extends BorderPane {
    private static final ImageView IMG_LOGO = new ImageView("be/kdg/thegame_2048/views/img/logo.png");
    private static final double OVERALL_PADDING = 50;
    private static final double TEXTFIELD_WIDTH = 210;
    private static final double TEXTFIELD_HEIGHT = 40;
    private Label lblMessage;
    private TextField tfPlayerName;
    private Label lblInputError;
    private Button btnGoBack;

    public PlayerView() {
        initialiseNodes();
        layoutNodes();
    }

    protected void initialiseNodes() {
        this.lblMessage = new Label();
        this.lblMessage.setPadding(new Insets(0,0,OVERALL_PADDING/2,0));

        this.tfPlayerName = new TextField();
        this.tfPlayerName.setMaxWidth(TEXTFIELD_WIDTH);
        this.tfPlayerName.setMaxHeight(TEXTFIELD_HEIGHT);

        //Optional error message, should be made visible if used
        this.lblInputError = new Label();
        this.lblInputError.setPadding(new Insets(OVERALL_PADDING/10,0,-OVERALL_PADDING/10,0));
        this.lblInputError.setVisible(false);

        this.btnGoBack = new Button();
        addStyles();
    }

    protected void layoutNodes() {
        BorderPane top = new BorderPane();
        top.setCenter(IMG_LOGO);
        top.setPadding(new Insets(0, 0, -OVERALL_PADDING * 2, 0));
        this.setTop(top);
        this.setPadding(new Insets(OVERALL_PADDING));

        VBox middle = new VBox(lblMessage, tfPlayerName, lblInputError, btnGoBack);
        middle.setAlignment(Pos.CENTER);
        this.setCenter(middle);
    }

    /**
     * Adds custom css selector to each individual node.
     **/
    private void addStyles() {
        tfPlayerName.getStyleClass().add("tfPlayer");
        lblInputError.getStyleClass().add("inputError");
        btnGoBack.getStyleClass().add("btnGoBack");
    }

    Button getBtnGoBack() {
        return btnGoBack;
    }
    TextField getTfPlayerName() {
        return tfPlayerName;
    }
    Label getLblInputError() {
        return lblInputError;
    }
    Label getLblMessage() {
        return lblMessage;
    }
}