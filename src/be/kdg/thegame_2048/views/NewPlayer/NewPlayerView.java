package be.kdg.thegame_2048.views.NewPlayer;

import be.kdg.thegame_2048.views.SuperView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

/**
 * @author Bryan de Ridder, Jarne van Aerde
 * @version 1.0 08-02-17 17:03
 */
public final class NewPlayerView extends SuperView {
    private static final double OVERALL_PADDING = 50;
    private Label lblNewPlayer;
    private TextField tfNewPlayer;
    private Label nameExistsError;
    private Button btnGoBack;

    public NewPlayerView() {
        initialiseNodes();
        layoutNodes();
    }

    @Override
    protected void initialiseNodes() {
        super.initialiseNodes();

        //NewPlayerView
        this.lblNewPlayer = new Label("What's your name?");
        lblNewPlayer.setPadding(new Insets(0,0,OVERALL_PADDING/2,0));

        //Textfield is set up
        this.tfNewPlayer = new TextField();
        tfNewPlayer.setMaxSize(210,40);

        //Optional error message, should be invisible by default
        this.nameExistsError = new Label("Sorry, this name already exists :(");
        nameExistsError.setPadding(new Insets(OVERALL_PADDING/10,0,-OVERALL_PADDING/10,0));
        nameExistsError.setVisible(false);

        //back button
        btnGoBack = new Button();
        addStyles();
    }

    @Override
    protected void layoutNodes() {
        super.layoutNodes();
        //NewPlayerView:
        VBox middle = new VBox(lblNewPlayer,tfNewPlayer, nameExistsError, btnGoBack);
        middle.setAlignment(Pos.CENTER);
        this.setCenter(middle);
    }

    private void addStyles() {
        tfNewPlayer.getStyleClass().add("tfPlayer");
        nameExistsError.getStyleClass().add("inputError");
        btnGoBack.getStyleClass().add("btnGoBack");
    }

    Button getBtnGoBack() {
        return btnGoBack;
    }

    TextField getTfNewPlayer() {
        return tfNewPlayer;
    }

    Label getNameExistsError() {
        return nameExistsError;
    }
}