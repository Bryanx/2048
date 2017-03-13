package be.kdg.thegame_2048.views.existingplayer;

import be.kdg.thegame_2048.views.SuperView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 08-02-17 18:09
 */
public final class ExistingPlayerView extends SuperView {
    private static final double OVERALL_PADDING = 50;
    private TextField tfExistingPlayer;
    private Label lblExistingPlayer;
    private Label nameDoesntExistError;
    private Button btnGoBack;

    public ExistingPlayerView() {
        initialiseNodes();
        layoutNodes();
    }

    @Override
    protected void initialiseNodes() {
        super.initialiseNodes();

        //ExistingPlayerView
        this.lblExistingPlayer = new Label("Enter your existing name");
        lblExistingPlayer.setPadding(new Insets(0,0,OVERALL_PADDING/2,0));

        //Textfield is set up
        this.tfExistingPlayer = new TextField();
        tfExistingPlayer.setMaxSize(210,40);

        //Potential error is set up, setvisible true to turn on
        this.nameDoesntExistError = new Label("Sorry, this name doesn't exist :(");
        nameDoesntExistError.setPadding(new Insets(OVERALL_PADDING/10,0,-OVERALL_PADDING/10,0));
        nameDoesntExistError.setVisible(false);

        //back button
        btnGoBack = new Button();
        addStyles();
    }

    @Override
    protected void layoutNodes() {
        super.layoutNodes();

        //ExistingPlayerView
        VBox middle = new VBox(lblExistingPlayer, tfExistingPlayer, nameDoesntExistError, btnGoBack);
        middle.setAlignment(Pos.CENTER);
        this.setCenter(middle);
    }

    /**
     * Adds custom css selector to each individual node.
     **/
    private void addStyles() {
        tfExistingPlayer.getStyleClass().add("tfPlayer");
        nameDoesntExistError.getStyleClass().add("inputError");
        btnGoBack.getStyleClass().add("btnGoBack");
    }

    /**
     * Returns a TextField
     * The TextField contains the existingplayer input.
     **/
    TextField getTfExistingPlayer() {
        return tfExistingPlayer;
    }

    /**
     * Returns a Label that can be filled with an error.
     * The Label must also be set to visible.
     **/
    Label getNameDoesntExistError() {
        return nameDoesntExistError;
    }

    /**
     * Returns go back button.
     **/
    Button getBtnGoBack() {
        return btnGoBack;
    }
}
