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
 * @version 1.0 08-02-17 17:03
 */
public class NewPlayerView extends BorderPane {
    private static final double OVERALL_PADDING = 50;
    private static final Paint BG_COLOR = Color.rgb(236, 196, 0);
    private ImageView logo;
    private Label lblNewPlayer;
    private TextField tfNewPlayer;

    public NewPlayerView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //Same as in the StartView class, maybe put inside an interface
        this.logo = new ImageView("be/kdg/thegame_2048/views/views/img/logo.png");

        //NewPlayerView:
        this.lblNewPlayer = new Label("What's your name?");
        lblNewPlayer.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 35));
        lblNewPlayer.setPadding(new Insets(0,0,30,0));
        lblNewPlayer.setTextFill(Color.rgb(0,100,100));

        this.tfNewPlayer = new TextField();
        tfNewPlayer.setPrefHeight(40);
        tfNewPlayer.setMaxWidth(210);
        tfNewPlayer.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 23));
    }

    private void layoutNodes() {
        //Same as in the StartView class, maybe put inside an interface
        BorderPane top = new BorderPane();
        top.setCenter(logo);
        top.setPadding(new Insets(0, 0, 100, 0));
        this.setTop(top);
        this.setPadding(new Insets(OVERALL_PADDING));
        this.setBackground(new Background(new BackgroundFill(BG_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));

        //NewPlayerView:
        VBox middle = new VBox(lblNewPlayer,tfNewPlayer);
        middle.setAlignment(Pos.TOP_CENTER);
        this.setCenter(middle);
    }
}