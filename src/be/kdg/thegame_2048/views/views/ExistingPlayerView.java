package be.kdg.thegame_2048.views.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * @author Bryan de Ridder
 * @version 1.0 08-02-17 18:09
 */
public class ExistingPlayerView extends BorderPane {
    private static final double OVERALL_PADDING = 50;
    private ImageView logo;
    private TextField tfExistingPlayer;
    private Label lblExistingPlayer;
    private Label nameDoesntExistError;

    public ExistingPlayerView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //Same as in the StartView class, maybe put inside an interface
        this.logo = new ImageView("be/kdg/thegame_2048/views/views/img/logo.png");

        //ExistingPlayerView
        this.lblExistingPlayer = new Label("Enter your existing name");
        lblExistingPlayer.setPadding(new Insets(0,0,30,0));

        //Textfield is set up
        this.tfExistingPlayer = new TextField();
        tfExistingPlayer.setPrefHeight(40);
        tfExistingPlayer.setMaxWidth(210);
        tfExistingPlayer.getStyleClass().add("tfPlayer");

        //Potential error is set up, setvisible true to turn on
        this.nameDoesntExistError = new Label("Sorry, this name doesn't exist :(");
        nameDoesntExistError.setPadding(new Insets(15,0,0,0));
        nameDoesntExistError.setVisible(false);
        nameDoesntExistError.getStyleClass().add("inputError");
    }

    private void layoutNodes() {
        //Same as in the StartView class, maybe put inside an interface
        BorderPane top = new BorderPane();
        top.setCenter(logo);
        top.setPadding(new Insets(0, 0, 100, 0));
        this.setTop(top);
        this.setPadding(new Insets(OVERALL_PADDING));
        this.setMaxWidth(450);

        //ExistingPlayerView
        VBox middle = new VBox(lblExistingPlayer, tfExistingPlayer, nameDoesntExistError);
        middle.setAlignment(Pos.TOP_CENTER);
        this.setCenter(middle);
    }


}
