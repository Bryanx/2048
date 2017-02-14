package be.kdg.thegame_2048.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * @author Bryan de Ridder
 * @version 1.0 08-02-17 18:09
 */
public final class ExistingPlayerView extends BorderPane {
    private static final double OVERALL_PADDING = 50;
    private ImageView logo;
    private TextField tfExistingPlayer;
    private Label lblExistingPlayer;
    private Label nameDoesntExistError;
    private Button goBack;

    public ExistingPlayerView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //Same as in the StartView class, maybe put inside an interface
        this.logo = new ImageView("be/kdg/thegame_2048/views/img/logo.png");

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
        goBack = new Button();
        addStyles();
    }

    private void layoutNodes() {
        //Same as in the StartView class, maybe put inside an interface
        BorderPane top = new BorderPane();
        top.setCenter(logo);
        top.setPadding(new Insets(0, 0, -OVERALL_PADDING*2, 0));
        this.setTop(top);
        this.setPadding(new Insets(OVERALL_PADDING));

        //ExistingPlayerView
        VBox middle = new VBox(lblExistingPlayer, tfExistingPlayer, nameDoesntExistError, goBack);
        middle.setAlignment(Pos.CENTER);
        this.setCenter(middle);
    }

    private void addStyles() {
        tfExistingPlayer.getStyleClass().add("tfPlayer");
        nameDoesntExistError.getStyleClass().add("inputError");
        goBack.getStyleClass().add("btnGoBack");
    }


}
