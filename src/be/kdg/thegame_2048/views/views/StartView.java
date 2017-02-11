package be.kdg.thegame_2048.views.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Bryan de Ridder, Jarne Van Aerde
 * @version 1.0 5/02/2017 18:49
 */
public class StartView extends BorderPane {
    private static final int OVERALL_PADDING = 50;
    private ImageView logo;
    private Button btnNewPlayer;
    private Button btnExistingPlayer;

    public StartView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //Creates the logo at the top of the screen:
        this.logo = new ImageView("be/kdg/thegame_2048/views/views/img/logo.png");

        //Creates the New Player button and adds an icon:
        this.btnNewPlayer = new Button("New Player");
        btnNewPlayer.setGraphic(new ImageView("be/kdg/thegame_2048/views/views/img/ufo.png"));
        btnNewPlayer.setGraphicTextGap(10);

        //Creates the Existing Player button and adds an icon:
        this.btnExistingPlayer = new Button("Existing Player");
        btnExistingPlayer.setGraphic(new ImageView("be/kdg/thegame_2048/views/views/img/user.png"));
        btnExistingPlayer.setGraphicTextGap(10);
    }

    private void layoutNodes() {
        //topside of the StartView, contains the 2048 logo:
        BorderPane top = new BorderPane();
        top.setCenter(logo);
        top.setPadding(new Insets(0, 0, 100, 0));
        this.setTop(top);

        //Middle of the StartView, 2 buttons are stacked inside a GridPane:
        GridPane middle = new GridPane();
        middle.setAlignment(Pos.TOP_CENTER);
        middle.add(btnNewPlayer, 0, 0);
        middle.add(btnExistingPlayer, 0, 1);
        middle.setAlignment(Pos.TOP_CENTER);
        this.setCenter(middle);

        //Properties for the entire screen:
        this.setPadding(new Insets(OVERALL_PADDING));
    }
}